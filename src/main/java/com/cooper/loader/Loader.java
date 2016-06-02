/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.loader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.cooper.entities.CarryableEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Loader {



    public <Q extends CarryableEntity> Q load(
            String filePath,
            Class<Q> entityClass) {

        ObjectMapper mapper = new ObjectMapper();
        try {
            File f = new File(filePath);
            String jsonBody = new String(Files.readAllBytes(f.toPath()), "utf8");
            return mapper.readValue(jsonBody, entityClass);
        } catch (IOException ioEx) {
            throw new RuntimeException("Read was not successful", ioEx);
        }
    }

}
