package com.cooper.game.interactive;

import com.cooper.container.LocalResponse;

public interface Interactive {

    String getIdentifier();
    Character getIdentifieingMark();
    Boolean isOccupiable();

    LocalResponse getOptions();
    LocalResponse performCommand(String cmd, String args);

    void beatHeart();
}
