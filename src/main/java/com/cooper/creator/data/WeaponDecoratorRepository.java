/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.creator.data;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cooper.creator.entities.WeaponDecoratorEntity;

public interface WeaponDecoratorRepository extends MongoRepository<WeaponDecoratorEntity, String> {
}
