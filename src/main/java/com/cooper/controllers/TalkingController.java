/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooper.ActiveCharacterSingleton;
import com.cooper.dtos.CharacterDTO;

@RequestMapping("/talk")
@RestController
public class TalkingController {

    public TalkingController() {
    }

    @RequestMapping("/")
    public List<CharacterDTO> getListOfNearbyCharacters() {
        return ActiveCharacterSingleton.getInstance()
                .getCharacterList()
                .stream()
                .map(CharacterDTO::new)
                .collect(Collectors.toList());
    }

    @RequestMapping("/{characterName}")
    public String talkToIndividual(@PathVariable String characterName) {
        return ActiveCharacterSingleton.getInstance()
                .getCharacterByName(characterName)
                .getResponse();
    }
}
