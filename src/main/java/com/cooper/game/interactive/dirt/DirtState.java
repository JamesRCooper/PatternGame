package com.cooper.game.interactive.dirt;

import com.cooper.dto.InteractiveBlockDTO;
import com.cooper.game.character.InventoryExchanger;

public interface DirtState {

    String getIdentifier();
    Character getIdentifieingMark();
    Boolean isOccupiable();

    InteractiveBlockDTO getOptions();
    DirtState performCommandForNewState(InteractiveBlockDTO blockDTO, InventoryExchanger exchanger);
}
