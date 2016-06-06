/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.game.arena;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.cooper.container.LocalResponse;
import com.cooper.game.player.ActiveCharacter;

public class RoomTest {

    private Room room;

    @Before
    public void setUp() {
        room = new Room("src/test/resources/arena/DUNGEON_TESTING.arena");
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
}
