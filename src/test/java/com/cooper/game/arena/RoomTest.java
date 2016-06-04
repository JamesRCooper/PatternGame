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

public class RoomTest {

    private Room room;

    @Before
    public void setUp() {
        room = new Room("src/test/resources/arena/TESTING_ROOM.arena");
    }

    @Test
    public void testMultiCharacter() {

        Position sharedPosition = new Position(1, 1);
        Position uniquePosition = new Position(2, 2);
        ActiveCharacter character1 = getNewCharacter("Steve");
        ActiveCharacter character2 = getNewCharacter("Tony");

        room.addCharacter(character1, sharedPosition);

        assertFalse(room.addCharacter(character1, uniquePosition));
        assertFalse(room.addCharacter(character2, sharedPosition));

        assertTrue(room.removeCharacter(character1));
        assertFalse(room.removeCharacter(character1));
    }

    @Test
    public void testPositionTypes() {

        List<Position> badPositions = Arrays.asList(
                new Position(0, 0));
        List<Position> goodPostions = Arrays.asList(
                new Position(8, 1),
                new Position(9, 3),
                new Position(17, 6));

        ActiveCharacter character = getNewCharacter("name");

        badPositions.forEach(
                p -> assertFalse(room.addCharacter(character, p)));

        assertTrue(room.addCharacter(character, new Position(1, 1)));
        goodPostions.forEach(
                p -> assertTrue(room.moveCharacter(character, p)));
    }

    @Test
    public void testCharacterOverlay() {

        ActiveCharacter character1 = getNewCharacter("Martin");
        ActiveCharacter character2 = getNewCharacter("Jake");

        room.addCharacter(character1, new Position(1, 1));
        room.addCharacter(character2, new Position(9, 3));

        String asdf = room.getMap();
        System.out.println(asdf);
    }

    public ActiveCharacter getNewCharacter(final String name) {

        ActiveCharacter character = mock(ActiveCharacter.class);
        when(character.getIdentifier()).thenReturn(name);
        return character;
    }
}
