package com.cooper.game.player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static com.cooper.enums.LocalErrorType.PLAYER_ALREADY_EXISTS;
import static com.cooper.enums.LocalErrorType.PLAYER_IS_NOT_IN_POOL;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.cooper.container.LocalResponse;
import com.cooper.game.arena.Room;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class ActivePoolTest {

    @Configuration
    public static class ActivePoolTestCongig {

        @Bean
        public Room getTestFarm() {
            return new Room("src/test/resources/arena/FARM_TESTING.arena");
        }

        @Bean
        public Room getTestRoom() {
            return new Room("src/test/resources/arena/DUNGEON_TESTING.arena");
        }

        @Bean
        public ActivePool activePool(Room[] roomCollection) {
            List<Room> rooms = Arrays.asList(roomCollection);
            return new ActivePool(rooms);
        }
    }

    @Autowired
    ActivePool activePool;


    @Test
    public void testRoomPool() {

        List<String> roomNames = activePool.getListOfRooms();
        assertTrue(roomNames.contains("DUNGEON_TESTING"));
        assertTrue(roomNames.contains("FARM_TESTING"));
    }

    @Test
    public void testAddPlayerToPool() {

        ActiveCharacter player = getNewCharacter("Alex");

        LocalResponse response = activePool.addPlayerToPool(player);
        assertTrue(response.getMessage().length() > 1);
        response = activePool.addPlayerToPool(player);
        assertEquals(PLAYER_ALREADY_EXISTS, response.getErrors().get(0));
    }

    @Test
    public void testRemoveCharacter() {

        ActiveCharacter player = getNewCharacter("Barbara");

        LocalResponse response = activePool.removePlayerFromPool("tempId");
        assertEquals(PLAYER_IS_NOT_IN_POOL, response.getErrors().get(0));

        response = activePool.addPlayerToPool(player);
        String playerPoolId = response.getMessage();
        Integer activePlayer = activePool.getListOfPlayers().size();

        response = activePool.removePlayerFromPool(playerPoolId);
        assertTrue(response.isSuccessful());
        assertTrue(activePlayer > activePool.getListOfPlayers().size());
    }

    @Test
    public void testRoomPlacementAndMovement() {

        ActiveCharacter player = getNewCharacter("Carter");
        LocalResponse response = activePool.addPlayerToPool(player);
        String playerId = response.getMessage();

        response = activePool.putPlayerInRoom(playerId, "DUNGEON_TESTING");
        assertTrue(response.isSuccessful());

        response = activePool.movePlayerToRoom(playerId, "FARM_TESTING");
        assertTrue(response.isSuccessful());
    }

    public ActiveCharacter getNewCharacter(final String name) {

        ActiveCharacter character = mock(ActiveCharacter.class);
        when(character.getIdentifier()).thenReturn(name);
        return character;
    }
}
