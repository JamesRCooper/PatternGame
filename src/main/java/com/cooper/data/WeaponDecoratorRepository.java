/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.data;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cooper.entities.WeaponDecoratorEntity;

public interface WeaponDecoratorRepository extends MongoRepository<WeaponDecoratorEntity, String> {
}
