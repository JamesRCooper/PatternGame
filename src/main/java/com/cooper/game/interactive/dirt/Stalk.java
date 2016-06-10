package com.cooper.game.interactive.dirt;

import java.util.Arrays;
import java.util.List;

import com.cooper.container.LocalError;
import com.cooper.dto.InteractiveBlockDTO;
import com.cooper.enums.LocalErrorType;

public class Stalk implements DirtState {

    public List<String> commands = Arrays.asList("DIG_UP_STALK", "TEND");
    private Integer tendAugment = 0;

    public Stalk(Integer tendAugment) {
        this.tendAugment = tendAugment;
    }

    @Override
    public String getIdentifier() {
        return "matured";
    }

    @Override
    public Character getIdentifieingMark() {
        return 'S'; //for Stalk
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
        case ("DIG_UP_STALK"):
            return digUp(blockDTO.getArguments());
        case ("TEND"):
            return tend(blockDTO.getArguments());
        }

        throw new LocalError(blockDTO.getCommands().get(0), LocalErrorType.COMMAND_DOES_NOT_EXIST);
    }

    private DirtState digUp(List<String> args) {
        return new Tilled();
    }

    private DirtState tick(List<String> args) {

        if (new Integer(args.get(0)) + tendAugment >= 18) {
            return new Ripe();
        }
        tendAugment -= 1;
        return this;
    }

    private DirtState tend(List<String> args) {

        if(tendAugment < 3) {
            tendAugment += 1;
        }
        return this;
    }
}
