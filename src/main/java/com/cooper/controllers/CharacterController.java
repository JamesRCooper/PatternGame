/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.controllers;

import java.util.List;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cooper.data.CharacterRepository;
import com.cooper.entities.CharacterEntity;

@RestController
@RequestMapping("/entity/character")
public class CharacterController {

    private CharacterRepository characterRepository;

    public CharacterController(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @RequestMapping("/")
    public List<CharacterEntity> getAll() {

        return characterRepository.findAll();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public CharacterEntity postEntity(@RequestBody CharacterEntity newEntity) {

        String id = newEntity.getName();
        if (StringUtils.isEmpty(id)) {
            return new CharacterEntity();
        } else if (characterRepository.findOne(id) != null) {
            return new CharacterEntity();
        }

        return characterRepository.save(newEntity);
    }

    @RequestMapping(value = "/{identifier}", method = RequestMethod.PUT)
    @ResponseBody
    public CharacterEntity putEntity(@PathVariable String identifier, @RequestBody CharacterEntity updatedEntity) {

        if (StringUtils.isEmpty(identifier) ||
                StringUtils.isEmpty(updatedEntity.getName()) ||
                !identifier.equals(updatedEntity.getName())) {
            return new CharacterEntity();
        } else if (characterRepository.findOne(identifier) == null) {
            return new CharacterEntity();
        }

        return characterRepository.save(updatedEntity);
    }

    @RequestMapping(value = "/{identifier}", method = RequestMethod.DELETE)
    @ResponseBody
    public Boolean deleteEntity(@PathVariable String identifier) {

        characterRepository.delete(identifier);
        return true;
    }
}
