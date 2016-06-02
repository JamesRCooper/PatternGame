/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.utils;

import java.util.Random;

public class DiceUtil {

    private static Random random = new Random();

    public static Integer xd0_plusy(final Integer numberOfDice, final Integer offset) {
        return offset;
    }

    public static Integer xd2_plusy(final Integer numberOfDie, final Integer offset) {
        Integer roll = random.ints(numberOfDie, 1, 3).sum();
        return roll + offset;
    }

    public static Integer xd4_plusy(final Integer numberOfDie, final Integer offset) {
        Integer roll = random.ints(numberOfDie, 1, 5).sum();
        return roll + offset;
    }

    public static Integer xd6_plusy(final Integer numberOfDie, final Integer offset) {
        Integer roll = random.ints(numberOfDie, 1, 7).sum();
        return roll + offset;
    }

    public static Integer xd8_plusy(final Integer numberOfDie, final Integer offset) {
        Integer roll = random.ints(numberOfDie, 1, 9).sum();
        return roll + offset;
    }

    public static Integer xd10_plusy(final Integer numberOfDie, final Integer offset) {
        Integer roll = random.ints(numberOfDie, 1, 11).sum();
        return roll + offset;
    }

    public static Integer xd12_plusy(final Integer numberOfDie, final Integer offset) {
        Integer roll = random.ints(numberOfDie, 1, 13).sum();
        return roll + offset;
    }

    public static Integer xd20_plusy(final Integer numberOfDie, final Integer offset) {
        Integer roll = random.ints(numberOfDie, 1, 21).sum();
        return roll + offset;
    }
}
