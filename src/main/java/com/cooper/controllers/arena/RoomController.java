package com.cooper.controllers.arena;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cooper.container.LocalResponse;
import com.cooper.game.arena.Direction;
import com.cooper.game.player.ActivePool;

@RestController
@RequestMapping("/pool/character")
public class RoomController {

    private ActivePool pool;

    public RoomController(ActivePool pool) {
        this.pool = pool;
    }

    @RequestMapping(value = "/move/{direction}", params = {"activeId"})
    public LocalResponse moveCharacter(
            @PathVariable Direction direction,
            @RequestParam String activeId) {

        return pool.movePlayer(activeId, direction);
    }
}
