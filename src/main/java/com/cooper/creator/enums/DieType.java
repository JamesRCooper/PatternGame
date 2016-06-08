package com.cooper.creator.enums;

import java.util.function.BiFunction;

import com.cooper.creator.utils.DiceUtil;

public enum DieType {

    D20("d20", DiceUtil::xd20_plusy),
    D12("d12", DiceUtil::xd12_plusy),
    D10("d10", DiceUtil::xd10_plusy),
    D8("d8", DiceUtil::xd8_plusy),
    D6("d6", DiceUtil::xd6_plusy),
    D4("d4", DiceUtil::xd4_plusy),
    D2("d2", DiceUtil::xd2_plusy),
    D0("d0", DiceUtil::xd0_plusy);

    private String value;
    private BiFunction<Integer, Integer, Integer> rollNWithModifier;

    DieType(
            String value,
            BiFunction<Integer, Integer, Integer> rollNWithModifier) {

        this.value = value;
        this.rollNWithModifier = rollNWithModifier;
    }

    @Override
    public String toString() {
        return this.value;
    }

    public Integer roll(Integer numOfDice, Integer Modifier) {
        return this.rollNWithModifier.apply(numOfDice, Modifier);
    }

    public Integer roll() {
        return this.rollNWithModifier.apply(1, 0);
    }
}
