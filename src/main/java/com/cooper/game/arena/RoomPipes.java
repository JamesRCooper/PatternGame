package com.cooper.game.arena;

import java.util.ArrayDeque;
import java.util.Queue;

public class RoomPipes {

    private Queue<String> toRoom = new ArrayDeque<>(5);
    private Queue<String> fromRoom = new ArrayDeque<>(5);

    public RoomPipes() {
    }

    public void writeToRoom(String value) {
        toRoom.add(value);
    }

    public String readFromRoom() {

        if (fromRoom.isEmpty()) {
            return "";
        }
        return fromRoom.remove();
    }

    public void writeToMain(String value) {

        fromRoom.add(value);
    }

    public String readFromMain() {

        if (toRoom.isEmpty()) {
            return "";
        }
        return toRoom.remove();
    }
}
