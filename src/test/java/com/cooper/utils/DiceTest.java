/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.utils;

import static com.cooper.utils.Dice.xd2_plusy;
import static com.cooper.utils.Dice.xd4_plusy;
import static com.cooper.utils.Dice.xd6_plusy;
import static com.cooper.utils.Dice.xd8_plusy;
import static com.cooper.utils.Dice.xd10_plusy;
import static com.cooper.utils.Dice.xd12_plusy;
import static com.cooper.utils.Dice.xd20_plusy;

import java.util.function.BiFunction;

import org.junit.Test;

public class DiceTest {

    @Test
    public void testD2() {
        testSpecificDie(Dice::xd2_plusy, 1500);
    }

    @Test
    public void testD4() {
        testSpecificDie(Dice::xd4_plusy, 2500);
    }

    @Test
    public void testD6() {
        testSpecificDie(Dice::xd6_plusy, 3500);
    }

    @Test
    public void testD8() {
        testSpecificDie(Dice::xd8_plusy, 4500);
    }

    @Test
    public void testD10() {
        testSpecificDie(Dice::xd10_plusy, 5500);
    }

    @Test
    public void testD12() {
        testSpecificDie(Dice::xd12_plusy, 6500);
    }

    @Test
    public void testD20() {
        testSpecificDie(Dice::xd20_plusy, 10500);
    }

    @Test
    public void testOffset() {
        Integer total = Dice.xd2_plusy(1, 100);
        assert total > 100;
        assert total < 102;
    }

    private void testSpecificDie(BiFunction<Integer, Integer, Integer> diceFunction, Integer bestGuess) {
        Integer total = diceFunction.apply(1000, 0);
        System.out.println(total);
        assert total < bestGuess + 500;
        assert total > bestGuess - 500;
    }
}
