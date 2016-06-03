package com.cooper.game.room;

import java.util.Arrays;
import java.util.List;

import com.cooper.model.Character;

public class Tavern implements GameRoom {

    private List<Character> characters;
    private List<String> npcs = Arrays.asList("Helga", "Gandor");

    public Tavern(List<Character> characters, List<String> npcs) {
        this.characters = characters;
        this.npcs = npcs;
    }



    @Override
    public Boolean hasActivePlayer() {
        return false;
    }
}
