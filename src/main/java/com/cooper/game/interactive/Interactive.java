package com.cooper.game.interactive;

import com.cooper.dto.InteractiveBlockDTO;

public interface Interactive {

    String getIdentifier();
    Character getIdentifieingMark();
    Boolean isOccupiable();

    InteractiveBlockDTO getOptions();
    InteractiveBlockDTO performCommand(InteractiveBlockDTO blockDTO);

    void beatHeart();
}
