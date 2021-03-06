/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.game.pool;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.cooper.container.LocalResponse;
import com.cooper.creator.enums.CarryableType;
import com.cooper.creator.model.Carryable;
import com.cooper.creator.model.WeaponBase;
import com.cooper.dto.InteractiveBlockDTO;
import com.cooper.enums.LocalErrorType;
import com.cooper.game.arena.Direction;
import com.cooper.game.arena.Position;
import com.cooper.game.character.ActiveCharacter;
import com.cooper.game.character.InventoryExchanger;
import com.cooper.game.interactive.Interactive;
import com.cooper.game.interactive.InteractiveFactory;
import com.cooper.game.interactive.LoadedInteractive;
import com.cooper.game.interactive.SignInteractive;

public class RoomTest {

    private Room room;

    @Before
    public void setUp() {

        RoomBuilder builder = new RoomBuilder(new InteractiveFactory());
        room = builder.buildRoomFromSource("src/test/resources/arena/DUNGEON_TESTING.arena");

        LoadedInteractive block = new LoadedInteractive(
                new SignInteractive("Test Sign"),
                new Position(6, 6));
        room.addBlock(block);
    }

    @Test
    public void testMultiCharacter() {

        Position sharedPosition = new Position(1, 1);
        Position uniquePosition = new Position(2, 2);
        ActiveCharacter character1 = getNewCharacter("Steve");
        ActiveCharacter character2 = getNewCharacter("Tony");

        room.addPlayer(character1, sharedPosition);

        assertFalse(room.addPlayer(character1, uniquePosition).isSuccessful());
        assertFalse(room.addPlayer(character2, sharedPosition).isSuccessful());

        assertFalse(room.addPlayer(character1, uniquePosition).isSuccessful());

        assertTrue(room.removePlayer(character1).isSuccessful());
        assertFalse(room.removePlayer(character1).isSuccessful());
    }

    @Test
    public void testPositionTypes() {

        List<Direction> goodPostions = Arrays.asList(
                Direction.E,
                Direction.S,
                Direction.W,
                Direction.N);
        List<Direction> badPositions = Arrays.asList(
                Direction.N,
                Direction.D,
                Direction.U);

        ActiveCharacter character = getNewCharacter("name");

        assertTrue(room.addPlayer(character, new Position(1, 1)).isSuccessful());
        goodPostions.forEach(d -> {
            LocalResponse response = room.movePlayer(character, d);
            assertTrue(d.toString() + ": " + response.getErrors().toString(), response.isSuccessful());
        });
        badPositions.forEach(d -> {
            LocalResponse response = room.movePlayer(character, d);
            assertFalse(d.toString() + ": " + response.getErrors().toString(), response.isSuccessful());
        });
    }

    @Test
    public void testBlockPlacement() {

        LoadedInteractive block = new LoadedInteractive(
                new SignInteractive("Test Sign"),
                new Position(6, 6));
        LocalResponse response = room.addBlock(block);
        assertEquals(LocalErrorType.SPACE_IS_OCCUPIED_BY_BLOCK, response.getErrors().get(0));
    }

    @Test
    public void testCharacterOverlay() {

        ActiveCharacter character1 = getNewCharacter("Martin");
        ActiveCharacter character2 = getNewCharacter("Jake");

        room.addPlayer(character1, new Position(1, 1));
        room.addPlayer(character2, new Position(9, 3));

        room.getMap().getObjects().forEach(
                System.out::println
        );
    }

    public ActiveCharacter getNewCharacter(final String name) {

        ActiveCharacter character = mock(ActiveCharacter.class);
        when(character.getIdentifier()).thenReturn(name);
        return character;
    }

    @Test
    public void testHeartbeat() throws Exception {

        List<String> testObserver = new ArrayList<>();

        MockBlock mockBlock = new MockBlock(testObserver);
        LoadedInteractive loadedBlock = new LoadedInteractive(
                mockBlock,
                new Position(5,5));

        room.addBlock(loadedBlock);
        room.start();
        Thread.sleep(3000L);
        room.quit();

        assertTrue(testObserver.size() > 0);
    }

    @Test
    public void testInventoryExchangerPass() {

        ActiveCharacter player = getMockActiveCharacterWithInventory();
        room.addPlayer(player, new Position(8, 3));
        Interactive block = getMockBlock();
        room.addBlock(new LoadedInteractive(block, new Position(8, 2)));

        InteractiveBlockDTO dto =
                room.getBlockCommands(player);
        assertEquals("example carryable", dto.getCommands().get(0));
    }

    public ActiveCharacter getMockActiveCharacterWithInventory() {

        ActiveCharacter character = mock(ActiveCharacter.class);
        InventoryExchanger mockExchanger = getMockInventoryExchanger();
        when(character.getInvenoryExchanger()).thenReturn(mockExchanger);
        when(character.getIdentifier()).thenReturn("example character");
        return character;
    }

    public InventoryExchanger getMockInventoryExchanger() {

        Carryable mockCarryable = getMockCarryable();
        return new InventoryExchanger(
                () -> mockCarryable,
                mockCarryable,
                p -> {});
    }

    public Carryable getMockCarryable() {

        Carryable carryable = mock(Carryable.class);
        when(carryable.getType()).thenReturn(CarryableType.WEAPON);
        when(carryable.getIdentifier()).thenReturn("example carryable");
        return carryable;
    }

    public Interactive getMockBlock() {

        Interactive block = mock(Interactive.class);
        when(block.getOptions(any(InventoryExchanger.class))).thenAnswer(p -> {
            InventoryExchanger exchanger = p.getArgumentAt(0, InventoryExchanger.class);
            assertNotNull(exchanger);
            return new InteractiveBlockDTO(exchanger.lookAt().getIdentifier());
        });
        return block;
    }
}
