package com.cooper.creator.data;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cooper.creator.entities.ArmorEntity;

public interface ArmorRepository extends MongoRepository<ArmorEntity, String> {

}
