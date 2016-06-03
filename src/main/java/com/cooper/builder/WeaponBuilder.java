/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.builder;

import com.cooper.data.WeaponDecoratorRepository;
import com.cooper.data.WeaponRepository;
import com.cooper.entities.WeaponDecoratorEntity;
import com.cooper.entities.WeaponEntity;
import com.cooper.model.Weapon;
import com.cooper.model.WeaponBase;
import com.cooper.model.WeaponDecorator;

public class WeaponBuilder extends CarryableBuilderTemplate<Weapon, WeaponEntity, WeaponDecoratorEntity> {

    public WeaponBuilder(
            WeaponRepository baseRepo,
            WeaponDecoratorRepository decorRepo) {
        super(baseRepo, decorRepo);
    }

    @Override
    protected Weapon getCarryableFromEntity(WeaponEntity baseEntity) {

        return new WeaponBase(
                baseEntity.getName(),
                baseEntity.getHit().getSize(),
                baseEntity.getHit().getNumber(),
                baseEntity.getHit().getBase(),
                baseEntity.getDmg().getSize(),
                baseEntity.getDmg().getNumber(),
                baseEntity.getDmg().getBase());
    }

    @Override
    protected Weapon getDecoratedCarryableFromDecorEntity(
            Weapon entity, WeaponDecoratorEntity decorEntity) {
        return new WeaponDecorator(
                entity,
                decorEntity.getName(),
                decorEntity.getPlacement(),
                decorEntity.getHit().getSize(),
                decorEntity.getHit().getNumber(),
                decorEntity.getHit().getBase(),
                decorEntity.getDmg().getSize(),
                decorEntity.getDmg().getNumber(),
                decorEntity.getDmg().getBase());
    }
}
