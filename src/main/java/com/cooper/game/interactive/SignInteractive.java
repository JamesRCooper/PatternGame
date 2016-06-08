/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.game.interactive;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.cooper.container.LocalResponse;
import com.cooper.enums.LocalErrorType;

public class SignInteractive implements Interactive {

    private Map<String, Function<String, LocalResponse>> commandMethods;

    String signMsg;

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
    public Character getIdentifieingMark() {
        return 'S';
    }

    @Override
    public Boolean isOccupiable() {
        return false;
    }

    @Override
    public LocalResponse getOptions() {

        //TODO: Find a way to return an actual list
        String commands = commandMethods.keySet().toString();
        return new LocalResponse(commands);
    }

    @Override
    public LocalResponse performCommand(String cmd, String args) {

        if(!commandMethods.containsKey(cmd)) {
            return new LocalResponse(LocalErrorType.COMMAND_DOES_NOT_EXIST);
        }

        return commandMethods
                .get(cmd)
                .apply(args);
    }

    //Commands
    private LocalResponse readSign(String args) {

        String msg = signMsg;
        return new LocalResponse(msg);
    }

    private LocalResponse writeOnSign(String args) {

        this.signMsg = args;
        return new LocalResponse(args);
    }

    //heartbeat
    @Override
    public void beatHeart() {


    }
}
