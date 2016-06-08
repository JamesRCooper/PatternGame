/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.creator.builder;

import com.cooper.creator.data.ArmorDecoratorRepository;
import com.cooper.creator.data.ArmorRepository;
import com.cooper.creator.data.WeaponDecoratorRepository;
import com.cooper.creator.data.WeaponRepository;
import com.cooper.creator.entities.sub.ArmorCompositeEntity;
import com.cooper.creator.entities.sub.WeaponCompositeEntity;
import com.cooper.creator.model.Armor;
import com.cooper.creator.model.Weapon;

public class CarryableBuilderFactory {

    private ArmorRepository armorRepository;
    private ArmorDecoratorRepository armorDecoratorRepository;
    private WeaponRepository weaponRepository;
    private WeaponDecoratorRepository weaponDecoratorRepository;

    public CarryableBuilderFactory(
            ArmorRepository armorRepository,
            ArmorDecoratorRepository armorDecoratorRepository,
            WeaponRepository weaponRepository,
            WeaponDecoratorRepository weaponDecoratorRepository) {

        this.armorRepository = armorRepository;
        this.armorDecoratorRepository = armorDecoratorRepository;
        this.weaponRepository = weaponRepository;
        this.weaponDecoratorRepository = weaponDecoratorRepository;
    }

    public Armor buildFromCompositeEntity(ArmorCompositeEntity composite) {

        ArmorBuilder builder = new ArmorBuilder(armorRepository, armorDecoratorRepository);
        return builder.build(composite);
    }

    public Weapon buildFromCompositeEntity(WeaponCompositeEntity composite) {

        WeaponBuilder builder = new WeaponBuilder(weaponRepository, weaponDecoratorRepository);
        return builder.build(composite);
    }
}
