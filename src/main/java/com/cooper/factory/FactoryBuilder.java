/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.factory;

import java.util.function.Supplier;

import org.json.JSONObject;

import com.cooper.builder.ArmorBuilder;
import com.cooper.builder.WeaponBuilder;
import com.cooper.models.armor.Armor;
import com.cooper.models.dialogue.Dialogue;
import com.cooper.models.dialogue.SimpleDialogue;
import com.cooper.models.weapons.Weapon;
import com.cooper.utils.JSONHandler;

public class FactoryBuilder {

    private JSONHandler jsonHandler = new JSONHandler("Building factories");

    private JSONObject charaSheet;

    public FactoryBuilder(final String characterJson) {
        this.charaSheet = jsonHandler.handle(
                JSONObject::new, characterJson);
    }

    public AbstractCarryableFactory<Weapon> getWeaponFactory() {
        JSONObject weaponInfo = jsonHandler.handle(
                charaSheet::getJSONObject, "weapon");
        return new WeaponBuilder(weaponInfo);
    }

    public AbstractCarryableFactory<Armor> getArmorFactory() {
        JSONObject armorInfo = jsonHandler.handle(
                charaSheet::getJSONObject, "armor");
        return new ArmorBuilder(armorInfo);
    }

    public CharacterBuilder getCharacterBuilder() {
        JSONObject profileInfo = jsonHandler.handle(
                charaSheet::getJSONObject, "profile");
        return new CharacterBuilder(profileInfo);
    }

    public Supplier<Dialogue> getSimpleDialogueFactory() {
        JSONObject dialogueInfo = jsonHandler.handle(
                charaSheet::getJSONObject, "dialogue");
        return () -> {
            String familiar = jsonHandler.handle(
                    dialogueInfo::getString, "familiar");
            String unfamiliar = jsonHandler.handle(
                    dialogueInfo::getString, "unfamiliar");
            return new SimpleDialogue(familiar, unfamiliar);
        };
    }
}
