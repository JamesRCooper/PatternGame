/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooper.data.ArmorDecoratorRepository;
import com.cooper.entities.ArmorDecorator;

@RestController
@RequestMapping("/armorDecorator")
public class ArmorDecoratorController extends BaseEntityControllerTemplate<ArmorDecorator> {

    public ArmorDecoratorController(
            ArmorDecoratorRepository armorDecoratorRepository) {

        super(armorDecoratorRepository);
    }

    @Override
    protected ArmorDecorator getNewEntity() {
        return new ArmorDecorator();
    }
}
