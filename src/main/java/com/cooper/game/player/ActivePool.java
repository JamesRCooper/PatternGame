/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.game.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.cooper.container.LocalResponse;
import com.cooper.enums.LocalErrorType;
import com.cooper.game.arena.ActiveCharacter;
import com.cooper.game.arena.Room;

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
        playerRoom.put(playerTempId, "");
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
        roomPool.get(roomId).addPlayer(player);
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

    public List<String> getListOfRooms() {
        return new ArrayList<>(roomPool.keySet());
    }

    public List<ActiveCharacter> getListOfPlayers() {
        return new ArrayList<>(playerPool.values());
    }

    public void killThread() {
        roomPool.forEach((name, room) -> room.quit());
    }
}
