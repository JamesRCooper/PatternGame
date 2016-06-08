/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.creator.utils;

import java.util.function.BiFunction;

import org.junit.Test;

public class DiceUtilTest {

    public void testD2() {
        testSpecificDie(DiceUtil::xd2_plusy, 1500);
    }

    @Test
    public void testD4() {
        testSpecificDie(DiceUtil::xd4_plusy, 2500);
    }

    @Test
    public void testD6() {
        testSpecificDie(DiceUtil::xd6_plusy, 3500);
    }

    @Test
    public void testD8() {
        testSpecificDie(DiceUtil::xd8_plusy, 4500);
    }

    @Test
    public void testD10() {
        testSpecificDie(DiceUtil::xd10_plusy, 5500);
    }

    @Test
    public void testD12() {
        testSpecificDie(DiceUtil::xd12_plusy, 6500);
    }

    @Test
    public void testD20() {
        testSpecificDie(DiceUtil::xd20_plusy, 10500);
    }

    @Test
    public void testOffset() {
        Integer total = DiceUtil.xd2_plusy(1, 100);
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
