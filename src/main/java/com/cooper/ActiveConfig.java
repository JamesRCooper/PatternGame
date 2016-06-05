package com.cooper;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cooper.game.arena.Room;
import com.cooper.game.player.ActivePool;

@Configuration
public class ActiveConfig {

    @Bean
    public Room getDUNGEON_1() {
        return new Room("src/main/resources/arena/DUNGEON_1.arena");
    }

    @Bean
    public Room getFARM_1() {
        return new Room("src/main/resources/arena/FARM_1.arena");
    }

    @Bean
    public ActivePool getActivePlayerPool(Room[] roomCollection) {
        List<Room> rooms = Arrays.asList(roomCollection);
        return new ActivePool(rooms);
    }
}
