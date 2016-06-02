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

import com.cooper.data.WeaponDecoratorRepository;
import com.cooper.data.WeaponRepository;
import com.cooper.entities.WeaponDecoratorEntity;
import com.cooper.entities.WeaponEntity;
import com.cooper.entities.sub.Dice;
import com.cooper.entities.sub.WeaponCompositeEntity;
import com.cooper.enums.CarryableType;
import com.cooper.enums.DecoratorPlacement;
import com.cooper.enums.DieType;
import com.cooper.model.Weapon;

public class WeaponBuilderTest {

    @Test
    public void testBuild() {

        WeaponBuilder builder = new WeaponBuilder(
                getMockWeaponRepo(),
                getMockWeaponDecoratorRepo());

        Weapon weapon = builder.build(getMockWeaponComposite());

        Assert.assertEquals(
                "before name after\n"
                        + "hit: (4D6+4) 5D8+5 (6D10+6)\n"
                        + "dmg: (1D0+1) 2D2+2 (3D4+3)",
                weapon.getIdentifier());
    }

    private WeaponRepository getMockWeaponRepo() {

        WeaponRepository repo = mock(WeaponRepository.class);
        when(repo.findOne(anyString())).thenReturn(new WeaponEntity(
                "identity",
                CarryableType.WEAPON,
                "name",
                new Dice(2, 2, DieType.D2),
                new Dice(5, 5, DieType.D8)
        ));
        return repo;
    }

    private WeaponDecoratorRepository getMockWeaponDecoratorRepo() {

        WeaponDecoratorRepository repo = mock(WeaponDecoratorRepository.class);
        when(repo.findOne(eq("decor1"))).thenReturn(new WeaponDecoratorEntity(
                "decor identity",
                CarryableType.WEAPON_DECORATOR,
                "before",
                DecoratorPlacement.PREFIX,
                new Dice(1, 1, DieType.D0),
                new Dice(4, 4, DieType.D6)));
        when(repo.findOne(eq("decor2"))).thenReturn(new WeaponDecoratorEntity(
                "decor identity",
                CarryableType.WEAPON_DECORATOR,
                "after",
                DecoratorPlacement.POSTFIX,
                new Dice(3, 3, DieType.D4),
                new Dice(6, 6, DieType.D10)));
        return repo;
    }

    private WeaponCompositeEntity getMockWeaponComposite() {

        WeaponCompositeEntity entity = new WeaponCompositeEntity();
        entity.setBaseIdentifier("identity");
        entity.setDecorators(Arrays.asList("decor1", "decor2"));
        return entity;
    }
}
