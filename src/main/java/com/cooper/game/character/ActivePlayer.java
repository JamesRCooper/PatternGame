package com.cooper.game.character;

import java.util.List;

import com.cooper.creator.model.Carryable;
import com.cooper.creator.model.Character;

public class ActivePlayer implements ActiveCharacter {

    private Character player;

    public ActivePlayer(Character player) {
        this.player = player;
    }

    public String getIdentifier() {
        return player.getIdentifier();
    }

    public String getName() {
        return player.getName();
    }

    @Override
    public InventoryExchanger getInvenoryExchanger() {
        return new InventoryExchanger(
                this::getPlayerMainHand,
                player.getMainHand(),
                this::placeItemsInInventory);
    }

    //TODO: When inventories are implemented this method will remove the object from the main hand
    private <C extends Carryable> C getPlayerMainHand() {
        return null;
    }

    //TODO: When intenories are implemented this method will sort items into an inventory
    private void placeItemsInInventory(List<Carryable> carryables) {

    }
}
