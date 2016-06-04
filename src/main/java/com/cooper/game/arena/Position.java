/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.game.arena;

public class Position {

    Integer X;
    Integer Y;
    private Direction facing;

    public Position() {
    }

    public Position(Integer x, Integer y) {
        X = x;
        Y = y;
        this.facing = Direction.N;
    }

    public Position(Integer x, Integer y, Direction facing) {
        X = x;
        Y = y;
        this.facing = facing;
    }

    public void setFacing(Direction facing) {
        this.facing = facing;
    }

    public Direction getFacing() {
        return facing;
    }

    public boolean equals(Position obj) {
        return this.X.equals(obj.X) && this.Y.equals(obj.Y);
    }
}
