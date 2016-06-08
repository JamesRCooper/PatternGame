package com.cooper.creator.entities.sub;

import java.util.List;

import com.cooper.creator.entities.CarryableEntity;

public interface CompositeEntity<C extends CarryableEntity> {

    String getBaseIdentifier();
    List<String> getDecorators();
}
