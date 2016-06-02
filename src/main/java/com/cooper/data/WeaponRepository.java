/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.data;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cooper.entities.WeaponBase;

public interface WeaponRepository extends MongoRepository<WeaponBase, String> {
}
