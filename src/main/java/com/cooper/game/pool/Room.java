/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.game.pool;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.cooper.container.LocalError;
import com.cooper.container.LocalResponse;
import com.cooper.creator.dto.RequestedResponse;
import com.cooper.dto.InteractiveBlockDTO;
import com.cooper.enums.LocalErrorType;
import com.cooper.game.arena.Direction;
import com.cooper.game.arena.Position;
import com.cooper.game.character.ActiveCharacter;
import com.cooper.game.interactive.Interactive;
import com.cooper.game.interactive.LoadedInteractive;

public class Room extends Thread {

    public static List<Character> OCCUPIABLE_POSITIONS = Arrays.asList('_', 'D', 'O', 'L');

    private final List<List<Character>> map;
    private final String roomName;
    private final Position startingPosition;
    private Map<ActiveCharacter, Position> activeCharacters;
    private List<LoadedInteractive> activeBlocks;

    private Boolean quit = false;

    public Room(
            List<List<Character>> layout,
            String roomName,
            Position startingPosition,
            List<LoadedInteractive> activeBlocks) {

        this.map = layout;
        this.roomName = roomName;
        this.startingPosition = startingPosition;
        this.activeBlocks = activeBlocks;

        activeCharacters = new HashMap<>();
    }

    public String getRoomName() {
        return roomName;
    }

    public LocalResponse addBlock(LoadedInteractive newBlock) {

        if (isPlayerInSpace(newBlock.getPosition()))
            return new LocalResponse(LocalErrorType.ANOTHER_PLAYER_OCCUPIES_SPACE);
        if (!OCCUPIABLE_POSITIONS.contains(map.get(newBlock.getPosition().ROW)
                .get(newBlock.getPosition().COLUMN)))
            return new LocalResponse(LocalErrorType.SPACE_CANNOT_BE_OCCUPIED);
        if (isSpaceBlockedByBlock(newBlock.getPosition())) {
            return new LocalResponse(LocalErrorType.SPACE_IS_OCCUPIED_BY_BLOCK);
        }

        activeBlocks.add(newBlock);
        return new LocalResponse();
    }

    public LocalResponse addPlayer(ActiveCharacter player) {

        if (activeCharacters.containsKey(player))
            return new LocalResponse(LocalErrorType.PLAYER_ALREADY_EXISTS);

        activeCharacters.put(player, startingPosition);
        return new LocalResponse();
    }

    public LocalResponse addPlayer(ActiveCharacter player, Position position) {

        if (activeCharacters.containsKey(player))
            return new LocalResponse(LocalErrorType.PLAYER_ALREADY_EXISTS);
        if (isPlayerInSpace(position))
            return new LocalResponse(LocalErrorType.ANOTHER_PLAYER_OCCUPIES_SPACE);
        if (!OCCUPIABLE_POSITIONS.contains(map.get(position.ROW).get(position.COLUMN)))
            return new LocalResponse(LocalErrorType.SPACE_CANNOT_BE_OCCUPIED);
        if (isSpaceBlockedByBlock(position)) {
            return new LocalResponse(LocalErrorType.SPACE_IS_OCCUPIED_BY_BLOCK);
        }

        activeCharacters.put(player, position);
        return new LocalResponse();
    }

    private Boolean isPlayerInSpace(Position position) {
        return activeCharacters.values()
                .stream()
                .map(position::equals)
                .reduce(false, (bool1, bool2) -> bool1 || bool2);
    }

    private Boolean isSpaceBlockedByBlock(Position position) {

        for (LoadedInteractive block : activeBlocks) {
            if (!block.getBaseInteractive().isOccupiable() && block.getPosition().equals(position)) {
                return true;
            }
        }
        return false;
    }

    //TODO: write test
    public LocalResponse facePlayer(ActiveCharacter player, Direction direction) {

        if (!activeCharacters.containsKey(player))
            return new LocalResponse(LocalErrorType.PLAYER_NOT_IN_ROOM);

        Position playerPosition = activeCharacters.get(player);
        playerPosition.setFacing(direction);
        try {
            Interactive blockInFrontOfPlayer = getBlockPlayerIsFacing(playerPosition);
            return new LocalResponse("Facing " + blockInFrontOfPlayer.getIdentifier());
        } catch (LocalError le) {
            return new LocalResponse();
        }
    }

    public LocalResponse movePlayer(ActiveCharacter player, Direction direction) {

        if (!activeCharacters.containsKey(player))
            return new LocalResponse(LocalErrorType.PLAYER_NOT_IN_ROOM);

        Position playerPosition = activeCharacters.get(player);
        Integer column = playerPosition.getCOLUMN();
        Integer row = playerPosition.getROW();

        switch (direction) {
        case U:
        case D:
            return new LocalResponse(LocalErrorType.CANNOT_MOVE_UP_OR_DOWN);
        case N: row -= 1;
            break;
        case W: column -= 1;
            break;
        case S: row += 1;
            break;
        case E: column += 1;
            break;
        }
        if (!isPositionOccupiable(column, row))
            return new LocalResponse(LocalErrorType.SPACE_CANNOT_BE_OCCUPIED);

        Position newPosition = new Position(column, row, playerPosition.getFacing());
        activeCharacters.replace(player, newPosition);
        try {
            Interactive blockInFrontOfPlayer = getBlockPlayerIsFacing(newPosition);
            return new LocalResponse("Facing " + blockInFrontOfPlayer.getIdentifier());
        } catch (LocalError le) {
            return new LocalResponse();
        }
    }

    private boolean isPositionOccupiable(Integer column, Integer row) {
        return OCCUPIABLE_POSITIONS.contains(map.get(row).get(column));
    }

    public LocalResponse removePlayer(ActiveCharacter player) {

        if (activeCharacters.containsKey(player)) {
            activeCharacters.remove(player);
            return new LocalResponse();
        }
        return new LocalResponse(LocalErrorType.PLAYER_NOT_IN_ROOM);
    }

    public RequestedResponse<String> getMap() {

        List<List<Character>> overlaidMap = getCopyOfMap();
        insertBlockTokensIntoMap(overlaidMap);
        insertCharacterTokensIntoMap(overlaidMap);
        return new RequestedResponse<>(convertMapToString(overlaidMap));
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

        activeCharacters.forEach((c, p) ->
                overlaidMap.get(p.ROW).set(p.COLUMN, p.getFacing().getSymbol()));
    }

    private void insertBlockTokensIntoMap(List<List<Character>> overlaidMap) {

        activeBlocks.forEach( b ->
                overlaidMap.get(b.getPosition().ROW)
                        .set(b.getPosition().COLUMN, b.getBaseInteractive().getIdentifieingToken()));
    }

    private List<String> convertMapToString(List<List<Character>> overlayArray) {

        List<String> newMpa = new ArrayList<>();
        overlayArray.forEach(row -> {
            String tmp = row.stream().map(e -> e.toString()).collect(Collectors.joining());
            newMpa.add(tmp);
        });
        return newMpa;
    }

    public InteractiveBlockDTO getBlockCommands(final ActiveCharacter player) {

        try {
            Position playerPos = activeCharacters.get(player);
            Interactive block = getBlockPlayerIsFacing(playerPos);
            return block.getOptions();
        } catch (LocalError rtEx) {
            return new InteractiveBlockDTO(rtEx.getErrorType());
        }
    }

    public InteractiveBlockDTO runBlockCommand(
            final ActiveCharacter player, final InteractiveBlockDTO blockDTO) {

        try {
            Position playerPos = activeCharacters.get(player);
            Interactive block = getBlockPlayerIsFacing(playerPos);
            return block.performCommand(blockDTO);
        } catch (LocalError rtEx) {
            return new InteractiveBlockDTO(rtEx.getErrorType());
        }
    }

    public Interactive getBlockPlayerIsFacing(final Position playerPosition) {

        Position facingSpot = new Position(playerPosition.COLUMN, playerPosition.ROW);
        switch (playerPosition.getFacing()) {
        case N:
            facingSpot.setROW(facingSpot.ROW - 1);
            break;
        case E:
            facingSpot.setCOLUMN(facingSpot.COLUMN + 1);
            break;
        case S:
            facingSpot.setROW(facingSpot.ROW + 1);
            break;
        case W:
            facingSpot.setCOLUMN(facingSpot.COLUMN - 1);
            break;
        }

        List<LoadedInteractive> blocks = activeBlocks
                .stream()
                .filter(b -> b.getPosition().equals(facingSpot))
                .collect(Collectors.toList());

        if (blocks.size() == 0) {
            throw new LocalError("Not facing a block", LocalErrorType.NO_INTERACTIVE_BLOCKS_IN_SPACE);
        }

        return blocks.get(0).getBaseInteractive();
    }

    @Override
    public void run() {

        try {
            while (!quit) {
                heartbeat();
                Thread.sleep(2000);
            }
        } catch (InterruptedException iEx) {
            System.out.println("intterupt occured");
        }
    }

    public void quit() {
        quit = true;
    }

    private void heartbeat() {
        activeBlocks.forEach(li -> li.getBaseInteractive().beatHeart());
    }
}
