/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.controllers.entity;

import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cooper.data.ArmorRepository;
import com.cooper.entities.ArmorEntity;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/entity/armor")
public class ArmorController extends BaseEntityControllerTemplate<ArmorEntity> {

    public ArmorController(
            ArmorRepository armorRepository) {

        super(armorRepository);
    }

    @Override
    protected ArmorEntity getNewEntity() {
        return new ArmorEntity();
    }
}
