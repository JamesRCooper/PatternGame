package com.cooper.data;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cooper.entities.ArmorBase;

public interface ArmorRepository extends MongoRepository<ArmorBase, String> {

}
