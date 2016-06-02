/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooper.data.WeaponDecoratorRepository;
import com.cooper.entities.WeaponDecoratorEntity;

@RestController
@RequestMapping("/weaponDecorator")
public class WeaponDecoratorController extends BaseEntityControllerTemplate<WeaponDecoratorEntity> {

    public WeaponDecoratorController(
            WeaponDecoratorRepository entityRepository) {

        super(entityRepository);
    }

    @Override
    protected WeaponDecoratorEntity getNewEntity() {
        return new WeaponDecoratorEntity();
    }
}
