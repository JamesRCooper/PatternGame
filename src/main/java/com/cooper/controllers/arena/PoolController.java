package com.cooper.controllers.arena;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cooper.builder.CharacterBuilder;
import com.cooper.container.LocalResponse;
import com.cooper.enums.LocalErrorType;
import com.cooper.game.player.ActiveCharacter;
import com.cooper.game.player.ActivePlayer;
import com.cooper.game.player.ActivePool;
import com.cooper.model.Character;

@RestController
@RequestMapping("/pool")
public class PoolController {

    private CharacterBuilder builder;
    private ActivePool pool;

    public PoolController(
            final CharacterBuilder builder,
            final ActivePool pool) {

        this.builder = builder;
        this.pool = pool;
    }

    @RequestMapping("/rooms")
    public List<String> listRooms() {

        return pool.getListOfRooms();
    }

    @RequestMapping("/players")
    public List<ActiveCharacter> listPlayers() {

        return pool.getListOfPlayers();
    }

    @RequestMapping("/place/{characterName}")
    public LocalResponse placePlayerInPool(@PathVariable final String characterName) {

        Character loadedCharacter = builder.build(characterName);
        if (loadedCharacter == null)
            return new LocalResponse(LocalErrorType.PLAYER_WAS_NOT_BUILT);

        ActiveCharacter player = new ActivePlayer(loadedCharacter);
        return pool.addPlayerToPool(player);
    }

    @RequestMapping(value = "/moveToRoom/{roomName}", params = {"activeId"})
    public LocalResponse movePlayerToRoom(
            @PathVariable String roomName,
            @RequestParam String activeId) {

        return pool.putPlayerInRoom(activeId, roomName);
    }

    @RequestMapping(value = "/map", params = {"activeId"})
    public LocalResponse getOverlaidMap(@RequestParam String activeId) {

        return pool.getPlayerMap(activeId);
    }
}
