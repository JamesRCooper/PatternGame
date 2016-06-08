/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.creator.builder;

import com.cooper.creator.data.ArmorDecoratorRepository;
import com.cooper.creator.data.ArmorRepository;
import com.cooper.creator.entities.ArmorDecoratorEntity;
import com.cooper.creator.entities.ArmorEntity;
import com.cooper.creator.model.Armor;
import com.cooper.creator.model.ArmorBase;
import com.cooper.creator.model.ArmorDecorator;

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
