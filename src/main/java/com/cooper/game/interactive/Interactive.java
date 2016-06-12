package com.cooper.game.interactive;

import com.cooper.dto.InteractiveBlockDTO;

public interface Interactive {

    String getIdentifier();
    Character getIdentifieingToken();
    Boolean isOccupiable();

    InteractiveBlockDTO getOptions();
    InteractiveBlockDTO performCommand(InteractiveBlockDTO blockDTO);

    void beatHeart();
}
