package com.cooper.game.arena;

public enum Direction {

    N("north", '^'),
    S("south", '.'),
    E("east", '>'),
    W("west", '<'),
    U("up", '!'),
    D("down", '#');

    private String value;
    private Character symbol;

    Direction(String value, Character synbol) {

        this.value = value;
        this.symbol = synbol;
    }

    public Character getSymbol() {
        return symbol;
    }

    public String getValue() {
        return value;
    }
}
