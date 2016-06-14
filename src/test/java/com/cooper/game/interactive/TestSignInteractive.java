/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.game.interactive;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.cooper.container.LocalResponse;
import com.cooper.dto.InteractiveBlockDTO;
import com.cooper.enums.LocalErrorType;
import com.cooper.game.character.InventoryExchanger;

public class TestSignInteractive {

    private SignInteractive sign;

    private String readCmd = "READ_SIGN";
    private String writeCmd = "WRITE_ON_SIGN";

    private InventoryExchanger exchanger = InventoryExchanger.getTickInventory();

    @Before
    public void setUp() {
        sign = new SignInteractive("Hello");
    }

    @Test
    public void testOptions() {

        InteractiveBlockDTO response = sign.getOptions(null);

        Assert.assertEquals(Arrays.asList(readCmd, writeCmd), response.getCommands());
    }

    @Test
    public void testReadSign() {

        InteractiveBlockDTO blockDTO = new InteractiveBlockDTO(Collections.singletonList(readCmd));
        InteractiveBlockDTO response = sign.performCommand(blockDTO, exchanger);

        Assert.assertEquals("Hello", response.getMessage());
    }

    @Test
    public void testWriteSign() {

        String testMsg = "test msg";
        InteractiveBlockDTO blockDTO = new InteractiveBlockDTO(Collections.singletonList(writeCmd));
        blockDTO.setArguments(Collections.singletonList(testMsg));

        sign.performCommand(blockDTO, exchanger);
        blockDTO = new InteractiveBlockDTO(Collections.singletonList(readCmd));
        InteractiveBlockDTO response = sign.performCommand(blockDTO, exchanger);

        Assert.assertEquals(testMsg, response.getMessage());
    }

    @Test
    public void testBadCommand() {


        InteractiveBlockDTO response = sign.performCommand(
                new InteractiveBlockDTO(Collections.singletonList("Hogwash command")), exchanger);

        Assert.assertEquals(LocalErrorType.COMMAND_DOES_NOT_EXIST, response.getErrors().get(0));
    }
}
