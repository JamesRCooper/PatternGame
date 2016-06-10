package com.cooper.game.interactive;

import java.util.Collections;
import java.util.Random;

import com.cooper.container.LocalError;
import com.cooper.dto.InteractiveBlockDTO;
import com.cooper.enums.LocalErrorType;
import com.cooper.game.interactive.dirt.DirtState;
import com.cooper.game.interactive.dirt.Tilled;

public class DirtInteractive implements Interactive {

    private DirtState currentState;

    private Random rndNumGen;

    public DirtInteractive() {

        this.rndNumGen = new Random();
        this.currentState = new Tilled();
    }

    public DirtInteractive(DirtState currentState) {

        this.rndNumGen = new Random();
        this.currentState = currentState;
    }

    @Override
    public String getIdentifier() {
        return "Dirt: " + currentState.getIdentifier();
    }

    @Override
    public Character getIdentifieingMark() {
        return currentState.getIdentifieingMark();
    }

    @Override
    public Boolean isOccupiable() {
        return currentState.isOccupiable();
    }

    @Override
    public InteractiveBlockDTO getOptions() {
        return currentState.getOptions();
    }

    @Override
    public InteractiveBlockDTO performCommand(InteractiveBlockDTO blockDTO) {

        if (blockDTO.getCommands() == null || blockDTO.getCommands().size() == 0) {
            return new InteractiveBlockDTO(LocalErrorType.NO_COMMAND_GIVEN);
        }

        if (blockDTO.getCommands().get(0).equals("TICK")) {
            return new InteractiveBlockDTO(LocalErrorType.COMMAND_FOR_INTERNAL_USE_ONLY);
        }

        try {
            currentState = currentState.performCommandForNewState(blockDTO);
            return new InteractiveBlockDTO(blockDTO.getCommands());
        } catch (LocalError le) {
            InteractiveBlockDTO dto = new InteractiveBlockDTO(le.getErrorType());
            dto.setMessage(le.getMessage());
            return dto;
        }
    }

    @Override
    public void beatHeart() {

        Integer rndNum = rndNumGen.nextInt(20);
        InteractiveBlockDTO dto = new InteractiveBlockDTO(Collections.singletonList("TICK"));
        dto.setArguments(Collections.singletonList(rndNum.toString()));
        currentState = currentState.performCommandForNewState(dto);
    }
}
