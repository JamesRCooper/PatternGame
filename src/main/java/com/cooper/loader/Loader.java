/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.loader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cooper.data.ArmorDecoratorRepository;
import com.cooper.data.ArmorRepository;
import com.cooper.data.CharacterRepository;
import com.cooper.data.WeaponDecoratorRepository;
import com.cooper.data.WeaponRepository;
import com.cooper.entities.ArmorDecoratorEntity;
import com.cooper.entities.ArmorEntity;
import com.cooper.entities.CarryableEntity;
import com.cooper.entities.CharacterEntity;
import com.cooper.entities.WeaponDecoratorEntity;
import com.cooper.entities.WeaponEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Loader {

    private static String armorFiles = "src/main/resources/armor/";
    private static String armorDecorFiles = "src/main/resources/armor_decorator/";
    private static String weaponFiles = "src/main/resources/weapon/";
    private static String weaponDecorFiles = "src/main/resources/weapon_decorator/";
    private static String characterFiles = "src/main/resources/npcs/";

    private ArmorRepository armorRepository;
    private ArmorDecoratorRepository armorDecoratorRepository;
    private WeaponRepository weaponRepository;
    private WeaponDecoratorRepository weaponDecoratorRepository;
    private CharacterRepository characterRepository;

    public Loader(
            ArmorRepository armorRepository,
            ArmorDecoratorRepository armorDecoratorRepository,
            WeaponRepository weaponRepository,
            WeaponDecoratorRepository weaponDecoratorRepository,
            CharacterRepository characterRepository) {

        this.armorRepository = armorRepository;
        this.armorDecoratorRepository = armorDecoratorRepository;
        this.weaponRepository = weaponRepository;
        this.weaponDecoratorRepository = weaponDecoratorRepository;
        this.characterRepository = characterRepository;
    }

    public void loadAll() {

        loadAll(armorFiles, ArmorEntity.class, armorRepository);
        loadAll(armorDecorFiles, ArmorDecoratorEntity.class, armorDecoratorRepository);
        loadAll(weaponFiles, WeaponEntity.class, weaponRepository);
        loadAll(weaponDecorFiles, WeaponDecoratorEntity.class, weaponDecoratorRepository);
        loadAll(characterFiles, CharacterEntity.class, characterRepository);
    }

    private <Q> void loadAll(
            String fileDir, Class<Q> entityClass, MongoRepository<Q, String> repo) {

        List<File> files = getListofFiles(fileDir);
        List<Q> entities = new ArrayList<>();
        files.forEach(file -> entities.add(load(file, entityClass)));
        repo.save(entities);
    }

    private List<File> getListofFiles(String filePath) {

        File f = new File(filePath);
        File[] files = f.listFiles();
        return files != null ? Arrays.asList(files) : Collections.emptyList();
    }

    private <Q> Q load(
            File file,
            Class<Q> entityClass) {

        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonBody = new String(Files.readAllBytes(file.toPath()), "utf8");
            return mapper.readValue(jsonBody, entityClass);
        } catch (IOException ioEx) {
            throw new RuntimeException("Read was not successful", ioEx);
        }
    }
}
