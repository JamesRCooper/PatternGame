/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.controllers.entity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooper.creator.data.ArmorDecoratorRepository;
import com.cooper.creator.entities.ArmorDecoratorEntity;

@CrossOrigin
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
