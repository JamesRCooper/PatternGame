package com.cooper.data;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cooper.entities.ArmorEntity;

public interface ArmorRepository extends MongoRepository<ArmorEntity, String> {

}
