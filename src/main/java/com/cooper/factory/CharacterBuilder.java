/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.factory;

import org.json.JSONObject;

import com.cooper.models.armor.Armor;
import com.cooper.models.character.Character;
import com.cooper.models.character.HumanCharacter;
import com.cooper.models.dialogue.Dialogue;
import com.cooper.models.weapons.Weapon;
import com.cooper.utils.JSONHandler;

public class CharacterBuilder {

    private JSONHandler jsonHandler = new JSONHandler("Builder people");

    private JSONObject profile;

    private String characterName;
    private Integer characterStats;

    private Character character;

    public CharacterBuilder(JSONObject profile) {
        this.profile = profile;
    }

    public Character buildCharacter(Dialogue dialogue, Weapon weapon, Armor armor) {

        retrieveNameAndStats();
        String race = retrieveRace();
        createCharacterForRace(race);
        setDialogue(dialogue);
        setWeaponAndArmor(weapon, armor);
        return character;
    }

    private void retrieveNameAndStats() {
        characterName = jsonHandler.handle(
                profile::getString, "name");
        characterStats = jsonHandler.handle(
                profile::getInt, "baseDmg");
    }

    private String retrieveRace() {
        return jsonHandler.handle(
                profile::getString, "race");
    }

    private Character createCharacterForRace(final String race) {

        switch (race) {
        case ("human"):
            character = new HumanCharacter(characterName, characterStats);
            break;
        default:
            throw new RuntimeException("No race found of type " + race);
        }
        return null;
    }

    private void setDialogue(Dialogue dialogue) {
        character.setDialogue(dialogue);
    }

    private void setWeaponAndArmor(Weapon weapon, Armor armor) {
        character.setWeapon(weapon);
        character.setArmor(armor);
    }
}
