package com.cooper.game.interactive.dirt;

import com.cooper.dto.InteractiveBlockDTO;

public interface DirtState {

    String getIdentifier();
    Character getIdentifieingMark();
    Boolean isOccupiable();

    InteractiveBlockDTO getOptions();
    DirtState performCommandForNewState(InteractiveBlockDTO blockDTO);
}
