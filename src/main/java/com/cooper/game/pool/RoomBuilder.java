package com.cooper.game.pool;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.cooper.game.arena.Position;
import com.cooper.game.interactive.Interactive;
import com.cooper.game.interactive.InteractiveFactory;
import com.cooper.game.interactive.LoadedInteractive;

public class RoomBuilder {

    private InteractiveFactory blockFactory;

    //p-SIGN(This sign is built using the SIGN() method)
    private Pattern interactveMetaParser = Pattern.compile("-(.*?)\\((.*)\\)");

    private List<List<Character>> layout;
    private String roomName;
    private Position startingPosition;
    private Map<Character, Supplier<Interactive>> interactiveLookup;
    private List<LoadedInteractive> placedInteractives = new ArrayList<>();

    public RoomBuilder(InteractiveFactory blockFactory) {
        this.blockFactory = blockFactory;
    }

    public Room buildRoomFromSource(final String filePath) {

        List<String> roomInfo = getRoomInfoFromFilePath(filePath);
        parseDataFromRoomInfo(roomInfo);
        populatePlacedInteractives();
        return new Room(layout, roomName, startingPosition, placedInteractives);
    }

    private List<String> getRoomInfoFromFilePath(String filePath) {

        try {
            File roomFile = new File(filePath);
            Path roomPath = roomFile.toPath();
            return Files.readAllLines(roomPath);
        } catch (IOException ioEx) {
            throw new RuntimeException("Arena didn't Load");
        }
    }

    private void parseDataFromRoomInfo(List<String> roomInfow) {

        Integer metaRow = roomInfow.indexOf("META-INFO");
        setLayoutFromLayoutRows(roomInfow.subList(0, metaRow));
        setIdAndStartingPosition(roomInfow.get(metaRow + 1));
        setInteractiveLookup(roomInfow.subList(metaRow + 2, roomInfow.size()));
    }

    private void setLayoutFromLayoutRows(List<String> layoutRows) {

        this.layout = layoutRows.stream().map(row -> {
            List<Character> charRow = new ArrayList<>();
            for (char c : row.toCharArray())
                charRow.add(c);
            return charRow;
        }).collect(Collectors.toList());
    }

    private void setIdAndStartingPosition(String firstMetaRow) {

        String[] metaHeaders = firstMetaRow.split(",");
        roomName = metaHeaders[0];
        startingPosition = new Position(new Integer(metaHeaders[2]), new Integer(metaHeaders[1]));
    }

    private void setInteractiveLookup(List<String> interactiveMapRows) {

        interactiveLookup = new HashMap<>();
        interactiveMapRows.forEach(this::setEntryInLookUpForMetaInfoRow);
    }

    private void setEntryInLookUpForMetaInfoRow(final String row) {

        Character key = row.charAt(0);
        Matcher parsedRow = Pattern.compile(".-(.*?)\\((.*)\\)").matcher(row);
        if (parsedRow.matches()) {
            Supplier<Interactive> blockSupplier = blockFactory.getInteractiveFromMetaRow(
                    parsedRow.group(1), parsedRow.group(2));
            interactiveLookup.put(key, blockSupplier);
        }
    }

    public void populatePlacedInteractives() {

        for (int i = 0; i < layout.size(); i++) {
           for (int j = 0; j < layout.get(0).size(); j++) {
               List<Character> currentRow = layout.get(i);
               if (interactiveLookup.containsKey(layout.get(i).get(j))) {
                   Position blockPos = new Position(j, i);
                   Interactive block = interactiveLookup.get(currentRow.get(j)).get();
                   placedInteractives.add(new LoadedInteractive(block, blockPos));
                   currentRow.set(j, '_');
               }
           }
        }
    }
}
