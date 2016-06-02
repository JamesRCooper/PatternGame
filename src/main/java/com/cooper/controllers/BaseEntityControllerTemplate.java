/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.controllers;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cooper.entities.CarryableEntity;

public abstract class BaseEntityControllerTemplate<Q extends CarryableEntity> {

    protected MongoRepository<Q, String> entityRepository;

    public BaseEntityControllerTemplate(MongoRepository<Q, String> entityRepository) {
        this.entityRepository = entityRepository;
    }

    @RequestMapping("/")
    @ResponseBody
    public List<Q> getAllEntities() {

        return entityRepository.findAll();
    }

    @RequestMapping("/{identifier}")
    @ResponseBody
    public Q getEntity(@PathVariable String identifier) {

        return entityRepository.findOne(identifier);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public Q postEntity(@RequestBody Q newEntity) {

        String id = newEntity.getIdentifier();
        if (StringUtils.isEmpty(id)) {
            return getNewEntity();
        } else if (entityRepository.findOne(id) != null) {
            return getNewEntity();
        }

        return entityRepository.save(newEntity);
    }

    @RequestMapping(value = "/{identifier}", method = RequestMethod.PUT)
    @ResponseBody
    public Q putEntity(@PathVariable String identifier, @RequestBody Q updatedEntity) {

        if (StringUtils.isEmpty(identifier) ||
                StringUtils.isEmpty(updatedEntity.getIdentifier()) ||
                !identifier.equals(updatedEntity.getIdentifier())) {
            return getNewEntity();
        } else if (entityRepository.findOne(identifier) == null) {
            return getNewEntity();
        }

        return entityRepository.save(updatedEntity);
    }

    protected abstract Q getNewEntity();

    @RequestMapping(value = "/{identifier}", method = RequestMethod.DELETE)
    @ResponseBody
    public Boolean deleteEntity(@PathVariable String identifier) {

        entityRepository.delete(identifier);
        return true;
    }

}
