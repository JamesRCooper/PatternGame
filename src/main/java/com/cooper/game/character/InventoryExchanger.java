package com.cooper.game.character;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import com.cooper.creator.enums.CarryableType;
import com.cooper.creator.model.Carryable;

public class InventoryExchanger {

    private Supplier<Carryable> retrieveFromInventory;
    private Carryable carryableReference;
    private Consumer<List<Carryable>> returnToIntentory;

    public InventoryExchanger(
            Supplier<Carryable> retrieveFromInventory,
            Carryable carryableReference,
            Consumer<List<Carryable>> returnToIntentory) {

        this.retrieveFromInventory = retrieveFromInventory;
        this.carryableReference = carryableReference;
        this.returnToIntentory = returnToIntentory;
    }

    public static InventoryExchanger getTickInventory() {

        //This method is for when a tick is being performed on a block and an InventoryExchanger is needed
        Carryable carryable = new Carryable() {
            @Override
            public CarryableType getType() {
                return CarryableType.INTANGIBLE_TICK;
            }

            @Override
            public String getIdentifier() {
                return "tick";
            }
        };

        return new InventoryExchanger(
                () ->  carryable,
                carryable,
                c -> {});
    }

    public Carryable retrieve() {
        return retrieveFromInventory.get();
    }

    public Carryable lookAt() {
        return carryableReference;
    }

    public void placeInInventory(Carryable carryable) {
        returnToIntentory.accept(Collections.singletonList(carryable));
    }

    public void placeInInventory(List<Carryable> carryables) {
        returnToIntentory.accept(carryables);
    }
}
