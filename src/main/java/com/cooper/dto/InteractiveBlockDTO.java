/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.cooper.enums.LocalErrorType;

public class InteractiveBlockDTO {

    private String message;

    private List<String> commands = new ArrayList<>();
    private List<String> arguments = new ArrayList<>();

    private List<LocalErrorType> errors = new ArrayList<>();

    public InteractiveBlockDTO() {
    }

    public InteractiveBlockDTO(List<String> commands) {
        this.commands = commands;
    }

    public InteractiveBlockDTO(String command) {
        this.commands = Collections.singletonList(command);
    }

    public InteractiveBlockDTO(LocalErrorType error) {
        errors.add(error);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getCommands() {
        return commands;
    }

    public void setCommands(List<String> commands) {
        this.commands = commands;
    }

    public void setCommand(String command) {
        commands.add(command);
    }

    public List<String> getArguments() {
        return arguments;
    }

    public void setArguments(List<String> arguments) {
        this.arguments = arguments;
    }

    public List<LocalErrorType> getErrors() {
        return errors;
    }

    public void setErrors(List<LocalErrorType> errors) {
        this.errors = errors;
    }

    public void setError(LocalErrorType error) {
        errors.add(error);
    }

    public Boolean isSuccessful() {
        return errors.size() == 0;
    }
}
