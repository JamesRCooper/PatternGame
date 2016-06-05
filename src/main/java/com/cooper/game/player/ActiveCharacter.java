package com.cooper.game.player;

public interface ActiveCharacter {

    String getIdentifier();

    default Boolean equals(ActiveCharacter character) {
        return this.getIdentifier() == character.getIdentifier();
    }
}
