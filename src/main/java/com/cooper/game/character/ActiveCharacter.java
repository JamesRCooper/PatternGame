package com.cooper.game.character;

public interface ActiveCharacter {

    String getIdentifier();

    default Boolean equals(ActiveCharacter character) {
        return this.getIdentifier().equals(character.getIdentifier());
    }

    InventoryExchanger getInvenoryExchanger();
}
