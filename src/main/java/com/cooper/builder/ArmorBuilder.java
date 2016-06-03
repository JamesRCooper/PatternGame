/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.builder;

import com.cooper.data.ArmorDecoratorRepository;
import com.cooper.data.ArmorRepository;
import com.cooper.entities.ArmorDecoratorEntity;
import com.cooper.entities.ArmorEntity;
import com.cooper.model.Armor;
import com.cooper.model.ArmorBase;
import com.cooper.model.ArmorDecorator;

public class ArmorBuilder extends CarryableBuilderTemplate<Armor, ArmorEntity, ArmorDecoratorEntity> {

    public ArmorBuilder(
            ArmorRepository baseRepo,
            ArmorDecoratorRepository decorRepo) {

        super(baseRepo, decorRepo);
    }

    @Override
    protected Armor getCarryableFromEntity(ArmorEntity baseEntity) {
        return new ArmorBase(baseEntity.getName(), baseEntity.getBaseAC());
    }

    @Override
    protected Armor getDecoratedCarryableFromDecorEntity(
            Armor entity, ArmorDecoratorEntity decorEntity) {

        return new ArmorDecorator(
                entity,
                decorEntity.getName(),
                decorEntity.getPlacement(),
                decorEntity.getBaseAC());
    }
}
