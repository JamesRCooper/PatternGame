/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.game.interactive;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.cooper.container.LocalResponse;
import com.cooper.enums.LocalErrorType;

public class SignInteractiveTest {

    private SignInteractive sign;

    private String readCmd = "READ_SIGN";
    private String writeCmd = "WRITE_ON_SIGN";

    @Before
    public void setUp() {
        sign = new SignInteractive("Hello");
    }

    @Test
    public void testOptions() {

        LocalResponse response = sign.getOptions();

        Assert.assertEquals(String.format("[%s, %s]", readCmd, writeCmd), response.getMessage());
    }

    @Test
    public void testReadSign() {

        LocalResponse response = sign.performCommand(readCmd, "");

        Assert.assertEquals("Hello", response.getMessage());
    }

    @Test
    public void testWriteSign() {

        String testMsg = "test msg";
        sign.performCommand(writeCmd, testMsg);
        LocalResponse response = sign.performCommand(readCmd, "");

        Assert.assertEquals(testMsg, response.getMessage());
    }

    @Test
    public void testBadCommand() {

        LocalResponse response = sign.performCommand("hogwash", "");

        Assert.assertEquals(LocalErrorType.COMMAND_DOES_NOT_EXIST, response.getErrors().get(0));
    }
}
