package com.cooper.game.arena;

public enum Direction {

    N("north"),
    S("south"),
    E("east"),
    W("west"),
    U("up"),
    D("down");

    private String value;

    Direction(String value) {
        this.value = value;
    }
}
