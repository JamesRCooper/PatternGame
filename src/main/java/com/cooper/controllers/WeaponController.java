/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooper.data.WeaponRepository;
import com.cooper.entities.WeaponBase;

@RestController
@RequestMapping("/weapon")
public class WeaponController extends BaseEntityControllerTemplate<WeaponBase> {

    public WeaponController(
             WeaponRepository entityRepository) {

        super(entityRepository);
    }
}
