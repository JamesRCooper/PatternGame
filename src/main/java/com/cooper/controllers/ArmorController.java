/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooper.data.ArmorRepository;
import com.cooper.entities.ArmorBase;

@RestController
@RequestMapping("/armor")
public class ArmorController extends BaseEntityControllerTemplate<ArmorBase> {

    public ArmorController(
            ArmorRepository armorRepository) {

        super(armorRepository);
    }
}
