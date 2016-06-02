/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.builder;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import com.cooper.data.ArmorDecoratorRepository;
import com.cooper.data.ArmorRepository;
import com.cooper.entities.ArmorDecoratorEntity;
import com.cooper.entities.ArmorEntity;
import com.cooper.entities.sub.ArmorCompositeEntity;
import com.cooper.enums.CarryableType;
import com.cooper.enums.DecoratorPlacement;
import com.cooper.model.Armor;

public class ArmorBuilderTest {

    @Test
    public void testBuild() {

        ArmorBuilder builder = new ArmorBuilder(
                getMockArmorRepo(),
                getMockArmorDecoratorRepo());

        Armor armor = builder.build(getMockArmorComposite());

        Assert.assertEquals("before armor after (+1)2(+3)", armor.getIdentifier());
    }

    private ArmorRepository getMockArmorRepo() {

        ArmorRepository repo = mock(ArmorRepository.class);
        when(repo.findOne(anyString())).thenReturn(new ArmorEntity(
                "armor id",
                CarryableType.ARMOR,
                "armor",
                2));
        return repo;
    }

    private ArmorDecoratorRepository getMockArmorDecoratorRepo() {

        ArmorDecoratorRepository repo = mock(ArmorDecoratorRepository.class);
        when(repo.findOne(eq("decor1"))).thenReturn(new ArmorDecoratorEntity(
                "decor1id",
                CarryableType.ARMOR_DECORATOR,
                "before",
                DecoratorPlacement.PREFIX,
                1));
        when(repo.findOne(eq("decor2"))).thenReturn(new ArmorDecoratorEntity(
                "decor2id",
                CarryableType.ARMOR_DECORATOR,
                "after",
                DecoratorPlacement.POSTFIX,
                3));
        return repo;
    }

    private ArmorCompositeEntity getMockArmorComposite() {

        ArmorCompositeEntity entity = new ArmorCompositeEntity();
        entity.setBaseIdentifier("armor id");
        entity.setDecorators(Arrays.asList("decor1", "decor2"));
        return entity;
    }
}
