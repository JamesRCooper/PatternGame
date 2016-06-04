package com.cooper.game.arena;

public interface ActiveCharacter {

    String getIdentifier();

    default Boolean equals(ActiveCharacter character) {
        return this.getIdentifier() == character.getIdentifier();
    }
}
