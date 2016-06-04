/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.game.arena;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Room {

    public static List<Character> OCCUPIABLE_POSITIONS = Arrays.asList('_', 'D', 'O', 'L');

    private Map<ActiveCharacter, Position> activeCharacters;
    private final List<List<Character>> map;

    public Room(
            String fileDirectory) {

        this.activeCharacters = new HashMap<>();
        this.map = loadMap(fileDirectory);
    }

    private List<List<Character>> loadMap(String fileDirectory) {

        try {
            List<String> rows = Files.readAllLines(new File(fileDirectory).toPath());
            return rows
                    .stream()
                    .map(row -> {
                        List<Character> charRow = new ArrayList<>();
                        for (char c : row.toCharArray())
                            charRow.add(c);
                        return charRow;
                    })
                    .collect(Collectors.toList());
        } catch (IOException ioEx) {
            throw new RuntimeException("Arena didn't load", ioEx);
        }
    }

    public Boolean addCharacter(ActiveCharacter character, Position position) {

        if (!activeCharacters.containsKey(character) &&
                OCCUPIABLE_POSITIONS.contains(map.get(position.Y).get(position.X))) {

            activeCharacters.put(character, position);
            return true;
        }
        return false;
    }

    public Boolean moveCharacter(ActiveCharacter character, Position position) {

        if (activeCharacters.containsKey(character) &&
                OCCUPIABLE_POSITIONS.contains(map.get(position.Y).get(position.X))) {

            activeCharacters.replace(character, position);
            return true;
        }
        return false;
    }

    public Boolean removeCharacter(ActiveCharacter character) {

        if (activeCharacters.containsKey(character)) {
            activeCharacters.remove(character);
            return true;
        }
        return false;
    }

    public String getMap() {

        List<List<Character>> overlaidMap = getCopyOfMap();
        insertCharacterTokensIntoMap(overlaidMap);
        return convertMapToString(overlaidMap);
    }

    private List<List<Character>> getCopyOfMap() {

        List<List<Character>> mapCopy = new ArrayList<>();
        map.forEach(row -> {
            List<Character> rowCopy = new ArrayList<>();
            row.forEach(rowCopy::add);
            mapCopy.add(rowCopy);
        });
        return mapCopy;
    }

    private void insertCharacterTokensIntoMap(List<List<Character>> overlaidMap) {

        activeCharacters.forEach((c, p) -> overlaidMap.get(p.Y).set(p.X, 'U'));
    }

    private String convertMapToString(List<List<Character>> overlayArray) {

        String fullMap = overlayArray
                .stream()
                .map(String::valueOf)
                .reduce("", (full, row) -> full + row + "\n");
        return fullMap;
    }
}
