package com.cooper.game.interactive.dirt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

import com.cooper.container.LocalError;
import com.cooper.dto.InteractiveBlockDTO;
import com.cooper.enums.LocalErrorType;
import com.cooper.game.character.InventoryExchanger;

public class TestTheStatesOfDirt {

    private DirtState tilled;
    private DirtState sprouts;
    private DirtState stalk;
    private DirtState ripe;

    private InventoryExchanger exchanger = InventoryExchanger.getTickInventory();

    @Before
    public void setUp() {

        tilled = new Tilled();
        sprouts = new Sprouts();
        stalk = new Stalk(2);
        ripe = new Ripe();
    }

    @Test
    public void testTilled() {

        assertEquals(Collections.singletonList("PLANT_SEEDS"), tilled.getOptions().getCommands());

        assertTrue(tilled.performCommandForNewState(
                new InteractiveBlockDTO("TICK"), exchanger).getClass()
                .isInstance(tilled));
        assertTrue(tilled.performCommandForNewState(
                new InteractiveBlockDTO("PLANT_SEEDS"), exchanger).getClass()
                .isInstance(sprouts));
    }

    @Test
    public void testSprouts() {

        assertEquals(Arrays.asList("DIG_UP_SPROUT", "TEND"), sprouts.getOptions().getCommands());

        assertEquals(
                "Tilled Soil",
                sprouts.performCommandForNewState(new InteractiveBlockDTO("DIG_UP_SPROUT"), exchanger)
                        .getIdentifier());
    }

    @Test
    public void testSproutTending() {

        InteractiveBlockDTO blockDTO = new InteractiveBlockDTO("TICK");
        blockDTO.setArguments(Collections.singletonList("18"));
        assertEquals("Matured Stalk", sprouts.performCommandForNewState(blockDTO, exchanger)
                .getIdentifier());

        blockDTO.setArguments(Collections.singletonList("17"));
        assertEquals("Sprouting Saps", sprouts.performCommandForNewState(blockDTO, exchanger)
                .getIdentifier());

        sprouts = sprouts.performCommandForNewState(new InteractiveBlockDTO("TEND"), exchanger);
        sprouts = sprouts.performCommandForNewState(new InteractiveBlockDTO("TEND"), exchanger);
        sprouts = sprouts.performCommandForNewState(new InteractiveBlockDTO("TEND"), exchanger);

        blockDTO = new InteractiveBlockDTO("TICK");
        blockDTO.setArguments(Collections.singletonList("15"));
        assertEquals("Matured Stalk", sprouts.performCommandForNewState(blockDTO, exchanger)
                .getIdentifier());
    }

    @Test
    public void testStalks() {

        assertEquals(Arrays.asList("DIG_UP_SPROUT", "TEND"), sprouts.getOptions().getCommands());

        assertEquals(
                "Tilled Soil",
                sprouts.performCommandForNewState(new InteractiveBlockDTO("DIG_UP_SPROUT"), exchanger)
                        .getIdentifier());
    }

    @Test
    public void testStalkTending() {

        InteractiveBlockDTO blockDTO = new InteractiveBlockDTO("TICK");

        blockDTO.setArguments(Collections.singletonList("16"));
        assertEquals("Ready For Harvest", stalk.performCommandForNewState(blockDTO, exchanger)
                .getIdentifier());

        blockDTO.setArguments(Collections.singletonList("15"));
        assertEquals("Matured Stalk", stalk.performCommandForNewState(blockDTO, exchanger)
                .getIdentifier());

        stalk = stalk.performCommandForNewState(new InteractiveBlockDTO("TEND"), exchanger);

        blockDTO.setArguments(Collections.singletonList("14"));
        assertEquals("Matured Stalk", stalk.performCommandForNewState(blockDTO, exchanger)
                .getIdentifier());

        blockDTO.setArguments(Collections.singletonList("17"));
        assertEquals("Ready For Harvest", stalk.performCommandForNewState(blockDTO, exchanger)
                .getIdentifier());
    }

    @Test
    public void testRipeCommandRetrieval() {

        assertEquals(Arrays.asList("HARVEST", "DIG_UP_HARVESTABLE"), ripe.getOptions().getCommands());
    }

    @Test
    public void testRipeBadCommand() {

        try {
            ripe.performCommandForNewState(new InteractiveBlockDTO("Hogwash command"), exchanger);
            fail("Exception not caught");
        } catch (LocalError le) {
            assertEquals(LocalErrorType.COMMAND_DOES_NOT_EXIST, le.getErrorType());
        }
    }

    @Test
    public void testRipeTickCommand() {

        assertEquals(
                "Ready For Harvest",
                ripe.performCommandForNewState(new InteractiveBlockDTO("TICK"), exchanger).getIdentifier());
    }

    @Test
    public void testRipeDigUpCommand() {

        assertEquals(
                "Tilled Soil",
                ripe.performCommandForNewState(new InteractiveBlockDTO("DIG_UP_HARVESTABLE"), exchanger)
                        .getIdentifier());
    }

    @Test
    public void testRipeHarvestCommand() {

        assertEquals(
                "Tilled Soil",
                ripe.performCommandForNewState(new InteractiveBlockDTO("HARVEST"), exchanger)
                        .getIdentifier());
    }
}
