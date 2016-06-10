package com.cooper.game.interactive.dirt;

import java.util.Collections;
import java.util.List;

import com.cooper.container.LocalError;
import com.cooper.dto.InteractiveBlockDTO;
import com.cooper.enums.LocalErrorType;

public class Tilled implements DirtState {

    private List<String> commands = Collections.singletonList("PLANT_SEEDS");

    @Override
    public String getIdentifier() {
        return "Tilled";
    }

    @Override
    public Character getIdentifieingMark() {
        return 't'; //for tilled
    }

    @Override
    public Boolean isOccupiable() {
        return true;
    }

    @Override
    public InteractiveBlockDTO getOptions() {
        return new InteractiveBlockDTO(commands);
    }

    @Override
    public DirtState performCommandForNewState(InteractiveBlockDTO blockDTO) {

        switch(blockDTO.getCommands().get(0)) {
        case ("TICK"):
            return tick(blockDTO.getArguments());
        case ("PLANT_SEEDS"):
            return plantSeeds(blockDTO.getArguments());
        }

        throw new LocalError(blockDTO.getCommands().get(0), LocalErrorType.COMMAND_DOES_NOT_EXIST);
    }

    private DirtState plantSeeds(List<String> args) {
        return new Sprouts();
    }

    private DirtState tick(List<String> args) {
        return this;
    }
}
