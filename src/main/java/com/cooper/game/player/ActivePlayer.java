package com.cooper.game.player;

import com.cooper.model.Character;

public class ActivePlayer implements ActiveCharacter {

    private Character player;

    public ActivePlayer(Character player) {
        this.player = player;
    }

    public String getIdentifier() {
        return player.getIdentifier();
    }

    public String getName() {
        return player.getName();
    }
}
