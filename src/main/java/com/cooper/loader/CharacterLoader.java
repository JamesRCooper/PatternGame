/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.cooper.factory.FactoryBuilder;
import com.cooper.loader.Loader;
import com.cooper.models.armor.Armor;
import com.cooper.models.character.Character;
import com.cooper.models.dialogue.Dialogue;
import com.cooper.models.weapons.Weapon;

public class CharacterLoader extends Loader<Character> {

    private static String rootDirectory = "src/main/resources/npcs";

    public CharacterLoader() {
        super(rootDirectory);
    }

    @Override
    protected Character createItemFromJsonSheet(final String characterJson) {

            FactoryBuilder factoryBuilder = new FactoryBuilder(characterJson);

            Weapon weapon = factoryBuilder.getWeaponFactory().build();
            Armor armor = factoryBuilder.getArmorFactory().build();
            Dialogue dialogue = factoryBuilder.getSimpleDialogueFactory().get();
            return factoryBuilder.getCharacterBuilder().buildCharacter(
                    dialogue, weapon, armor);
    }
}
