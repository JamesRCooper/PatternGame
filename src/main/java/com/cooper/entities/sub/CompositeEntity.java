package com.cooper.entities.sub;

import java.util.List;

import com.cooper.entities.CarryableEntity;

public interface CompositeEntity<C extends CarryableEntity> {

    String getBaseIdentifier();
    List<String> getDecorators();
}
