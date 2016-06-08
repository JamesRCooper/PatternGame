/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.creator.builder;

import java.util.Collections;
import java.util.List;

import com.cooper.creator.data.CharacterRepository;
import com.cooper.creator.entities.CharacterEntity;
import com.cooper.creator.model.Armor;
import com.cooper.creator.model.Carryable;
import com.cooper.creator.model.Character;
import com.cooper.creator.model.CharacterBase;
import com.cooper.creator.model.Weapon;

public class CharacterBuilder {

    private CarryableBuilderFactory builderService;
    private CharacterRepository characterRepository;

    public CharacterBuilder(
            CarryableBuilderFactory builderService,
            CharacterRepository characterRepository) {

        this.builderService = builderService;
        this.characterRepository = characterRepository;
    }

    public Character build(String name) {

        CharacterEntity savedCharacter = characterRepository.findOne(name);

        if (savedCharacter == null) {
            return null;
        }

        Weapon mainHand = builderService
                .buildFromCompositeEntity(savedCharacter.getMainHand());
        Weapon offHand = builderService
                .buildFromCompositeEntity(savedCharacter.getOffHand());
        Armor armor = builderService
                .buildFromCompositeEntity(savedCharacter.getArmor());
        List<Carryable> bag = populateBag();

        return new CharacterBase(
                name,
                savedCharacter.getRaceType(),
                savedCharacter.getClassType(),
                savedCharacter.getStrength(),
                savedCharacter.getConstitution(),
                savedCharacter.getDexterity(),
                savedCharacter.getIntelligence(),
                savedCharacter.getCharisma(),
                savedCharacter.getWisdom(),
                mainHand,
                offHand,
                armor,
                bag);
    }

    private List<Carryable> populateBag() {
        return Collections.emptyList();
    }
}