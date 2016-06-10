package com.cooper.game.interactive.dirt;

import java.util.Arrays;
import java.util.List;

import com.cooper.container.LocalError;
import com.cooper.dto.InteractiveBlockDTO;
import com.cooper.enums.LocalErrorType;

public class Ripe implements DirtState {

    private List<String> commands = Arrays.asList("HARVEST", "DIG_UP_HARVESTABLE");

    @Override
    public String getIdentifier() {
        return "Ready for Harvest";
    }

    @Override
    public Character getIdentifieingMark() {
        return 'R'; //for ripe
    }

    @Override
    public Boolean isOccupiable() {
        return false;
    }

    @Override
    public InteractiveBlockDTO getOptions() {
        return new InteractiveBlockDTO(commands);
    }

    @Override
    public DirtState performCommandForNewState(InteractiveBlockDTO blockDTO) {

        switch(blockDTO.getCommands().get(0)) {
        case ("TICK"):
            return this;
        case ("DIG_UP_HARVESTABLE"):
            return digUp(blockDTO.getArguments());
        case ("HARVEST"):
            return harvest(blockDTO.getArguments());
        }

        throw new LocalError(blockDTO.getCommands().get(0), LocalErrorType.COMMAND_DOES_NOT_EXIST);
    }

    private DirtState digUp(List<String> args) {
        return new Tilled();
    }

    private DirtState harvest(List<String> args) {

        //TODO: figure out way to return object(seeds and fruit) to player
        return new Tilled();
    }
}
