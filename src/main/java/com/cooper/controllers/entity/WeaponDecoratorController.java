/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.controllers.entity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooper.controllers.entity.BaseEntityControllerTemplate;
import com.cooper.data.WeaponDecoratorRepository;
import com.cooper.entities.WeaponDecoratorEntity;

@RestController
@RequestMapping("/entity/weaponDecorator")
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
