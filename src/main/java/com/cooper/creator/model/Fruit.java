package com.cooper.creator.model;

import com.cooper.creator.enums.CarryableType;

public class Fruit implements Carryable {

    private String name;

    public Fruit(String name) {
        this.name = name;
    }

    @Override
    public CarryableType getType() {
        return CarryableType.FRUIT;
    }

    @Override
    public String getIdentifier() {
        return "Fruit: " + name;
    }
}
