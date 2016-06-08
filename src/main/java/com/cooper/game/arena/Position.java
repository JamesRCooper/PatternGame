/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.game.arena;

public class Position {

    public Integer COLUMN;
    public Integer ROW;
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

    public Integer getCOLUMN() {
        return COLUMN;
    }

    public void setCOLUMN(Integer COLUMN) {
        this.COLUMN = COLUMN;
    }

    public Integer getROW() {
        return ROW;
    }

    public void setROW(Integer ROW) {
        this.ROW = ROW;
    }

    public boolean equals(Position obj) {
        return this.COLUMN.equals(obj.COLUMN) && this.ROW.equals(obj.ROW);
    }
}
