/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.controllers.entity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooper.data.WeaponRepository;
import com.cooper.entities.WeaponEntity;

@RestController
@RequestMapping("/entity/weapon")
public class WeaponController extends BaseEntityControllerTemplate<WeaponEntity> {

    public WeaponController(
             WeaponRepository entityRepository) {

        super(entityRepository);
    }

    @Override
    protected WeaponEntity getNewEntity() {
        return new WeaponEntity();
    }
}
