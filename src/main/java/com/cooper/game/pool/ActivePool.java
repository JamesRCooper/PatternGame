/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.game.pool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.util.StringUtils;

import com.cooper.container.LocalResponse;
import com.cooper.creator.dto.RequestedResponse;
import com.cooper.dto.InteractiveBlockDTO;
import com.cooper.enums.LocalErrorType;
import com.cooper.game.arena.Direction;
import com.cooper.game.character.ActiveCharacter;

public class ActivePool {

    //TODO: Investigate HashTable when multithreading becomes a thing
    private Map<String, Room> roomPool = new HashMap<>();
    private Map<String, ActiveCharacter> playerPool = new HashMap<>();
    private Map<String, String> playerRoom = new HashMap<>();

    public ActivePool(List<Room> rooms) {

        rooms.forEach(rm -> {
            roomPool.put(rm.getRoomName(), rm);
            rm.start();
        });
    }

    public LocalResponse addPlayerToPool(ActiveCharacter player) {

        if (playerPool.containsValue(player))
            return new LocalResponse(LocalErrorType.PLAYER_ALREADY_EXISTS);
        String playerTempId = UUID.randomUUID().toString();
        playerPool.put(playerTempId, player);
        return new LocalResponse(playerTempId);
    }

    public LocalResponse removePlayerFromPool(String playerTempId) {

        if (!playerPool.containsKey(playerTempId))
            return new LocalResponse(LocalErrorType.PLAYER_IS_NOT_IN_POOL);

        if (playerRoom.containsKey(playerTempId)) {
            String roomId = playerRoom.get(playerTempId);
            if (!"".equals(roomId)) {
                ActiveCharacter player = playerPool.get(playerTempId);
                roomPool.get(roomId).removePlayer(player);
                playerRoom.remove(playerTempId);
            }
        }

        playerPool.remove(playerTempId);
        return new LocalResponse();
    }

    public LocalResponse putPlayerInRoom(String playerTempId, String roomId) {

        if(!playerPool.containsKey(playerTempId))
            return new LocalResponse(LocalErrorType.PLAYER_IS_NOT_IN_POOL);
        if(!roomPool.containsKey(roomId))
            return new LocalResponse(LocalErrorType.ROOM_IS_NOT_IN_POOL);

        ActiveCharacter player = playerPool.get(playerTempId);
        LocalResponse response = roomPool.get(roomId).addPlayer(player);
        if (!response.isSuccessful())
            return response;

        playerRoom.put(playerTempId, roomId);
        return new LocalResponse(roomId);
    }

    public LocalResponse movePlayerToRoom(String playerTempId, String roomId) {

        if(!playerPool.containsKey(playerTempId))
            return new LocalResponse(LocalErrorType.PLAYER_IS_NOT_IN_POOL);
        if(!roomPool.containsKey(roomId))
            return new LocalResponse(LocalErrorType.ROOM_IS_NOT_IN_POOL);
        if(!playerRoom.containsKey(playerTempId))
            return new LocalResponse(LocalErrorType.PLAYER_NOT_PLACED_YET);

        ActiveCharacter player = playerPool.get(playerTempId);
        String currentRoon = playerRoom.get(playerTempId);
        if (roomPool.get(currentRoon).removePlayer(player).isSuccessful()) {
            if (roomPool.get(roomId).addPlayer(player).isSuccessful()) {
                playerRoom.replace(playerTempId, currentRoon, roomId);
                return new LocalResponse();
            }
        }
        return new LocalResponse();
    }

    public RequestedResponse<String> getListOfRooms() {

        List<String> rooms = new ArrayList<>(roomPool.keySet());
        return new RequestedResponse<>(rooms);
    }

    public List<ActiveCharacter> getListOfPlayers() {
        return new ArrayList<>(playerPool.values());
    }

    //TODO: add tests
    public RequestedResponse<String> getPlayerMap(final String playerTempId) {

        if(!playerPool.containsKey(playerTempId))
            return new RequestedResponse<>(
                    new LocalResponse(LocalErrorType.PLAYER_IS_NOT_IN_POOL).toString());
        if(!playerRoom.containsKey(playerTempId) || StringUtils.isEmpty(playerRoom.get(playerTempId)))
            return new RequestedResponse<>(
                    new LocalResponse(LocalErrorType.PLAYER_NOT_IN_ROOM).toString());

        String roomId = playerRoom.get(playerTempId);
        Room room = roomPool.get(roomId);
        return room.getMap();
    }

    //TODO: add test
    public LocalResponse movePlayer(final String playerTempId, final Direction direction) {

        if(!playerPool.containsKey(playerTempId))
            return new LocalResponse(LocalErrorType.PLAYER_IS_NOT_IN_POOL);
        if(!playerRoom.containsKey(playerTempId) || StringUtils.isEmpty(playerRoom.get(playerTempId)))
            return new LocalResponse(LocalErrorType.PLAYER_NOT_IN_ROOM);

        String roomId = playerRoom.get(playerTempId);
        Room room = roomPool.get(roomId);
        ActiveCharacter player = playerPool.get(playerTempId);

        return room.movePlayer(player, direction);
    }

    //TODO: add test
    public LocalResponse facePlayer(final String playerTempId, final Direction direction) {

        if(!playerPool.containsKey(playerTempId))
            return new LocalResponse(LocalErrorType.PLAYER_IS_NOT_IN_POOL);
        if(!playerRoom.containsKey(playerTempId) || StringUtils.isEmpty(playerRoom.get(playerTempId)))
            return new LocalResponse(LocalErrorType.PLAYER_NOT_IN_ROOM);

        String roomId = playerRoom.get(playerTempId);
        Room room = roomPool.get(roomId);
        ActiveCharacter player = playerPool.get(playerTempId);

        return room.facePlayer(player, direction);
    }

    public InteractiveBlockDTO getBlockCommands(final String playerTempId) {

        if(!playerPool.containsKey(playerTempId))
            return new InteractiveBlockDTO(LocalErrorType.PLAYER_IS_NOT_IN_POOL);
        if(!playerRoom.containsKey(playerTempId) || StringUtils.isEmpty(playerRoom.get(playerTempId)))
            return new InteractiveBlockDTO(LocalErrorType.PLAYER_NOT_IN_ROOM);

        String roomId = playerRoom.get(playerTempId);
        Room room = roomPool.get(roomId);
        ActiveCharacter player = playerPool.get(playerTempId);

        return room.getBlockCommands(player);
    }

    public InteractiveBlockDTO runBlockCommand(String playerTempId, InteractiveBlockDTO blockDTO) {

        if(!playerPool.containsKey(playerTempId))
            return new InteractiveBlockDTO(LocalErrorType.PLAYER_IS_NOT_IN_POOL);
        if(!playerRoom.containsKey(playerTempId) || StringUtils.isEmpty(playerRoom.get(playerTempId)))
            return new InteractiveBlockDTO(LocalErrorType.PLAYER_NOT_IN_ROOM);

        String roomId = playerRoom.get(playerTempId);
        Room room = roomPool.get(roomId);
        ActiveCharacter player = playerPool.get(playerTempId);

        return room.runBlockCommand(player, blockDTO);
    }

    public void killThread() {
        roomPool.forEach((name, room) -> room.quit());
    }
}
