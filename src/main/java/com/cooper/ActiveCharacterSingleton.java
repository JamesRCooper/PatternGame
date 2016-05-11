/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.cooper.factory.CharacterLoader;
import com.cooper.models.character.Character;

public class ActiveCharacterSingleton {

    private static ActiveCharacterSingleton instance;

    private Map<String, Character> characters;

    private ActiveCharacterSingleton() {
        CharacterLoader characterLoader = new CharacterLoader();
        characters = characterLoader.getCharacters()
                .stream()
                .collect(Collectors.toMap(Character::getName, chara -> chara));
    }

    public static ActiveCharacterSingleton getInstance() {
        if (instance == null) {
            instance = new ActiveCharacterSingleton();
        }
        return instance;
    }

    public List<Character> getCharacterList() {
        return new ArrayList<>(characters.values());
    }

    public Character getCharacterByName(final String name) {
         if (characters.containsKey(name)) {
             return characters.get(name);
         }
        return null;
    }

    public Boolean attemptToAddCharacter(Character character) {
        if (characters.containsKey(character.getName())) {
            return false;
        }
        characters.put(character.getName(), character);
        return true;
    }
}
