/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.creator.builder;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cooper.creator.entities.CarryableDecoratorEntity;
import com.cooper.creator.entities.CarryableEntity;
import com.cooper.creator.entities.sub.CompositeEntity;
import com.cooper.creator.model.Carryable;

public abstract class CarryableBuilderTemplate<
        C extends Carryable, E extends CarryableEntity, Q extends CarryableDecoratorEntity<E>> {

    private MongoRepository<E, String> baseRepo;
    private MongoRepository<Q, String> decorRepo;

    public CarryableBuilderTemplate(
            MongoRepository<E, String> baseRepo,
            MongoRepository<Q, String> decorRepo) {

        this.baseRepo = baseRepo;
        this.decorRepo = decorRepo;
    }

    public C build(CompositeEntity<E> compositeEntity) {

        if (compositeEntity == null) { return null; }

        String entityId = compositeEntity.getBaseIdentifier();
        C baseCarrable = getBaseCarryable(entityId);
        List<String> decors = compositeEntity.getDecorators();
        return decorateCarryable(baseCarrable, decors);
    }

    private C getBaseCarryable(String entityId) {

        E entity = baseRepo.findOne(entityId);
        return getCarryableFromEntity(entity);
    }

    protected abstract C getCarryableFromEntity(E baseEntity);

    private C decorateCarryable(C baseCarryable, List<String> decorIds) {

        C decoratedCarryable = baseCarryable;
        List<Q> decorEntities = decorIds
                .stream()
                .map(decorRepo::findOne)
                .collect(Collectors.toList());

        for (Q decorEntity : decorEntities) {
            decoratedCarryable = getDecoratedCarryableFromDecorEntity(
                    decoratedCarryable, decorEntity);
        }
        return decoratedCarryable;
    }

    protected abstract C getDecoratedCarryableFromDecorEntity(C entity, Q decorEntity);
}
