package com.cooper.enums;

public enum LocalErrorType {

    //Active pool
    PLAYER_ALREADY_EXISTS,
    PLAYER_IS_NOT_IN_POOL,
    ROOM_IS_NOT_IN_POOL,
    PLAYER_NOT_PLACED_YET,

    //Room
    PLAYER_NOT_IN_ROOM,
    PLAYER_CANNOT_OCCUPY_SPACE,
    ANOTHER_PLAYER_OCCUPIES_SPACE
}
