package com.cooper.game.interactive;

import com.cooper.game.arena.Position;

public class LoadedInteractive {

    private Interactive baseInteractive;
    private Position position;

    public LoadedInteractive(Interactive baseInteractive, Position position) {
        this.baseInteractive = baseInteractive;
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Interactive getBaseInteractive() {
        return baseInteractive;
    }
}
