/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.game.interactive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import com.cooper.container.LocalResponse;
import com.cooper.dto.InteractiveBlockDTO;
import com.cooper.enums.LocalErrorType;
import com.cooper.game.character.InventoryExchanger;

public class SignInteractive implements Interactive {

    private Map<String, Function<List<String>, InteractiveBlockDTO>> commandMethods;

    private String signMsg;

    public SignInteractive(String signMsg) {

        this.commandMethods = new HashMap<>();
        this.commandMethods.put("READ_SIGN", this::readSign);
        this.commandMethods.put("WRITE_ON_SIGN", this::writeOnSign);
        this.signMsg = signMsg;
    }

    @Override
    public String getIdentifier() {
        return "sign post";
    }

    @Override
    public Character getIdentifieingToken() {
        return 'P';
    }

    @Override
    public Boolean isOccupiable() {
        return false;
    }

    @Override
    public InteractiveBlockDTO getOptions(final InventoryExchanger exchanger) {

        List<String> commands = new ArrayList<>(commandMethods.keySet());
        return new InteractiveBlockDTO(commands);
    }

    @Override
    public InteractiveBlockDTO performCommand(
            InteractiveBlockDTO blockDTO,
            InventoryExchanger exchanger) {

        if(blockDTO.getCommands() == null || blockDTO.getCommands().size() == 0)
            return new InteractiveBlockDTO(LocalErrorType.NO_COMMAND_GIVEN);
        if(!commandMethods.containsKey(blockDTO.getCommands().get(0))) {
            return new InteractiveBlockDTO(LocalErrorType.COMMAND_DOES_NOT_EXIST);
        }

        return commandMethods
                .get(blockDTO.getCommands().get(0))
                .apply(blockDTO.getArguments());
    }

    //Commands
    private InteractiveBlockDTO readSign(List<String> args) {

        InteractiveBlockDTO dto = new InteractiveBlockDTO();
        dto.setMessage(signMsg);
        return dto;
    }

    private InteractiveBlockDTO writeOnSign(List<String> args) {

        if(args == null || args.size() == 0)
            return new InteractiveBlockDTO(LocalErrorType.NO_ARGUMENTS_SUPPLIED);
        this.signMsg = args.get(0);
        InteractiveBlockDTO dto = new InteractiveBlockDTO();
        dto.setMessage(signMsg);
        return dto;
    }

    //heartbeat
    @Override
    public void beatHeart() {


    }
}
