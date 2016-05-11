package com.cooper.models.character;

import com.cooper.models.Carryable;
import com.cooper.models.armor.Armor;
import com.cooper.models.armor.ClothArmor;
import com.cooper.models.dialogue.Dialogue;
import com.cooper.models.weapons.Weapon;

public abstract class Character implements Carryable {

    protected String name;
    protected Integer baseDmg;
    protected String characterRace = "human";
    protected String characterClass = "nomad";

    protected Dialogue dialogue;

    protected Weapon weapon;
    protected Armor armor = new ClothArmor();

    protected Boolean knowsYou = false;

    public Character(String name, Integer baseDmg) {
        this.name = name;
        this.baseDmg = baseDmg;
    }

    public String getName() {
        return name;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public Integer getBaseDmg() {
        return baseDmg;
    }

    public String getResponse() {
        return dialogue.getFamiliarityBasedResponse(knowsYou);
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public void setDialogue(Dialogue dialogue) {
        this.dialogue = dialogue;
    }

    public String getCharacterRace() {
        return characterRace;
    }

    public String getCharacterClass() {
        return characterClass;
    }
}
