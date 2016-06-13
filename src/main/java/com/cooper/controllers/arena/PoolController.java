package com.cooper.controllers.arena;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cooper.creator.builder.CharacterBuilder;
import com.cooper.container.LocalResponse;
import com.cooper.creator.dto.RequestedResponse;
import com.cooper.enums.LocalErrorType;
import com.cooper.game.character.ActiveCharacter;
import com.cooper.game.character.ActivePlayer;
import com.cooper.game.pool.ActivePool;
import com.cooper.creator.model.Character;

@CrossOrigin(origins = "*")
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

    @RequestMapping("/players")
    public List<ActiveCharacter> listPlayers() {

        return pool.getListOfPlayers();
    }

    @RequestMapping(value = "/place", params = {"characterName"})
    public LocalResponse placePlayerInPool(@RequestParam final String characterName) {

        Character loadedCharacter = builder.build(characterName);
        if (loadedCharacter == null)
            return new LocalResponse(LocalErrorType.PLAYER_WAS_NOT_BUILT);

        ActiveCharacter player = new ActivePlayer(loadedCharacter);
        return pool.addPlayerToPool(player);
    }

    @RequestMapping(value = "/moveToRoom", params = {"activeId", "roomName"})
    public LocalResponse movePlayerToRoom(
            @RequestParam String roomName,
            @RequestParam String activeId) {

        return pool.putPlayerInRoom(activeId, roomName);
    }

    @RequestMapping(value = "/map", params = {"activeId"})
    public RequestedResponse<String> getOverlaidMap(@RequestParam String activeId) {

        return pool.getPlayerMap(activeId);
    }

    @RequestMapping(value = "/rooms")
    public RequestedResponse<String> getListOfActiveRooms() {

        return pool.getListOfRooms();
    }

    @RequestMapping(value = "/map/key")
    public String getKeyList() {

        return new StringBuilder()
                .append("a - \n")
                .append("b - \n")
                .append("c - \n")
                .append("d - \n")
                .append("e - \n")
                .append("f - \n")
                .append("g - \n")
                .append("h - \n")
                .append("i - \n")
                .append("j - \n")
                .append("k - \n")
                .append("l - \n")
                .append("m - \n")
                .append("n - \n")
                .append("o - \n")
                .append("p - practive dummy\n")
                .append("q - \n")
                .append("r - \n")
                .append("s - soil with crop sprouts\n")
                .append("t - tilled soil\n")
                .append("u - \n")
                .append("v - \n")
                .append("w - \n")
                .append("x - \n")
                .append("y - \n")
                .append("z - \n")
                .append("A - \n")
                .append("B - \n")
                .append("C - \n")
                .append("D - \n")
                .append("E - \n")
                .append("F - a fense\n")
                .append("G - \n")
                .append("H - \n")
                .append("I - \n")
                .append("J - \n")
                .append("K - \n")
                .append("L - \n")
                .append("M - \n")
                .append("N - \n")
                .append("O - \n")
                .append("P - a sign post\n")
                .append("Q - \n")
                .append("S - soil with crop stalk\n")
                .append("T - \n")
                .append("U - \n")
                .append("V - \n")
                .append("W - a wall\n")
                .append("X - \n")
                .append("Y - \n")
                .append("Z - \n")
                .append("! - \n")
                .append("@ - \n")
                .append("# - \n")
                .append("$ - \n")
                .append("% - \n")
                .append("& - \n")
                .append("* - \n")
                .append("_ - an open space\n")
                .append("^ - character looking up\n")
                .append("> - character looking right\n")
                .append("< - character looking left\n")
                .append(". - character looking down\n")
                .toString();
    }
}
