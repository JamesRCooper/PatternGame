/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.game.arena;

public class Position {

    Integer COLUMN;
    Integer ROW;
    private Direction facing;

    public Position() {
    }

    public Position(Integer COLUMN, Integer ROW) {
        this.COLUMN = COLUMN;
        this.ROW = ROW;
        this.facing = Direction.N;
    }

    public Position(Integer COLUMN, Integer ROW, Direction facing) {
        this.COLUMN = COLUMN;
        this.ROW = ROW;
        this.facing = facing;
    }

    public void setFacing(Direction facing) {
        this.facing = facing;
    }

    public Direction getFacing() {
        return facing;
    }

    public boolean equals(Position obj) {
        return this.COLUMN.equals(obj.COLUMN) && this.ROW.equals(obj.ROW);
    }
}
