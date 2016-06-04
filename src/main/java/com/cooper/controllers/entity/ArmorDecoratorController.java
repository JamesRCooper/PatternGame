/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.controllers.entity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooper.data.ArmorDecoratorRepository;
import com.cooper.entities.ArmorDecoratorEntity;

@RestController
@RequestMapping("/entity/armorDecorator")
public class ArmorDecoratorController extends BaseEntityControllerTemplate<ArmorDecoratorEntity> {

    public ArmorDecoratorController(
            ArmorDecoratorRepository armorDecoratorRepository) {

        super(armorDecoratorRepository);
    }

    @Override
    protected ArmorDecoratorEntity getNewEntity() {
        return new ArmorDecoratorEntity();
    }
}
