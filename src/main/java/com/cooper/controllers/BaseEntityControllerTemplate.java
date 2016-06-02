/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.controllers;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public abstract class BaseEntityControllerTemplate<Q> {

    protected MongoRepository<Q, String> entityRepository;

    public BaseEntityControllerTemplate(MongoRepository<Q, String> entityRepository) {
        this.entityRepository = entityRepository;
    }

    @RequestMapping("/")
    @ResponseBody
    public List<Q> getAllEntities() {

        return entityRepository.findAll();
    }
}
