package com.cooper.game.interactive;

import com.cooper.dto.InteractiveBlockDTO;
import com.cooper.game.character.InventoryExchanger;

public interface Interactive {

    String getIdentifier();
    Character getIdentifieingToken();
    Boolean isOccupiable();

    InteractiveBlockDTO getOptions(InventoryExchanger exchanger);
    InteractiveBlockDTO performCommand(InteractiveBlockDTO blockDTO);

    void beatHeart();
}
