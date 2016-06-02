/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.builder;

import com.cooper.data.ArmorDecoratorRepository;
import com.cooper.data.ArmorRepository;
import com.cooper.data.WeaponDecoratorRepository;
import com.cooper.data.WeaponRepository;
import com.cooper.entities.sub.ArmorCompositeEntity;
import com.cooper.entities.sub.WeaponCompositeEntity;
import com.cooper.model.Armor;
import com.cooper.model.Weapon;

public class CarryableBuilderService {

    private ArmorRepository armorRepository;
    private ArmorDecoratorRepository armorDecoratorRepository;
    private WeaponRepository weaponRepository;
    private WeaponDecoratorRepository weaponDecoratorRepository;

    public CarryableBuilderService(
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
