package com.cooper.game.interactive;

import static org.junit.Assert.assertEquals;
import static com.cooper.Mocks.getMockCarryable;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import org.junit.Test;

import com.cooper.dto.InteractiveBlockDTO;
import com.cooper.game.character.InventoryExchanger;

public class DummyTest {

    @Test
    public void testOptions() {

        Interactive dummy = new PracticeDummyInteractive();

        assertEquals(
                new HashSet<>(Arrays.asList("STRIKE!!!", "Get Stats")),
                new HashSet<>(dummy.getOptions(new InventoryExchanger(
                        () -> getMockCarryable(),
                        getMockCarryable(),
                        c -> {}
                )).getCommands()));
    }

    @Test
    public void testStrikes() {

        InteractiveBlockDTO strikeDto = new InteractiveBlockDTO("STRIKE!!!");
        strikeDto.setArguments(Collections.singletonList("5"));

        InteractiveBlockDTO statCheckDto = new InteractiveBlockDTO("Get Stats");

        Interactive dummy = new PracticeDummyInteractive();

        dummy.performCommand(strikeDto);
        dummy.performCommand(strikeDto);
        dummy.performCommand(strikeDto);

        assertEquals(
                "15 damage dealt across 3 strikes, for an average damage dealt of 5/strike.",
                dummy.performCommand(statCheckDto).getMessage());
    }
}
