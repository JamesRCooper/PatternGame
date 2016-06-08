/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.creator.builder;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.Assert;
import org.junit.Test;

import com.cooper.creator.data.CharacterRepository;
import com.cooper.creator.entities.CharacterEntity;
import com.cooper.creator.entities.sub.ArmorCompositeEntity;
import com.cooper.creator.entities.sub.WeaponCompositeEntity;
import com.cooper.creator.enums.ClassType;
import com.cooper.creator.enums.DieType;
import com.cooper.creator.enums.RaceType;
import com.cooper.creator.model.ArmorBase;
import com.cooper.creator.model.Character;
import com.cooper.creator.model.WeaponBase;

public class CharacterBuilderTest {

    @Test
    public void buildTest() {

        CharacterBuilder builder = new CharacterBuilder(
                getMockBuilderService(),
                getMockCharacterRepository());

        Character character = builder.build("Phillamon");

        Assert.assertEquals(character.getIdentifier(),
                "Phillamon,\n"
                        + "holding a wpn in his right hand\n"
                        + "and a nothing in his left,\n"
                        + "wearing clothes\n",
                character.getIdentifier());
    }

    private CarryableBuilderFactory getMockBuilderService() {

        CarryableBuilderFactory service = mock(CarryableBuilderFactory.class);
        when(service.buildFromCompositeEntity(any(ArmorCompositeEntity.class)))
                .thenReturn(new ArmorBase("clothes", 1));
        when(service.buildFromCompositeEntity(any(WeaponCompositeEntity.class)))
                .thenReturn(new WeaponBase("wpn", DieType.D20, 1, 10, DieType.D6, 2, 1));
        when(service.buildFromCompositeEntity(isNull(WeaponCompositeEntity.class)))
                .thenReturn(null);
        return service;
    }

    private CharacterRepository getMockCharacterRepository() {

        CharacterRepository repo = mock(CharacterRepository.class);
        when(repo.findOne(eq("Phillamon"))).thenReturn(new CharacterEntity(
                "Phillamon",
                RaceType.ELF,
                ClassType.CLERIC,
                7, 8, 9, 11, 12, 13,
                new WeaponCompositeEntity(),
                null,
                new ArmorCompositeEntity(),
                Collections.emptyList()));
        return repo;
    }
}
