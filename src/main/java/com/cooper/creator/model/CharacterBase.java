/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.creator.model;

import java.util.List;

import com.cooper.creator.enums.ClassType;
import com.cooper.creator.enums.RaceType;

public class CharacterBase implements Character {

    private String name;

    private RaceType raceType;
    private ClassType classType;

    private Integer strength;
    private Integer constitution;
    private Integer dexterity;
    private Integer intelligence;
    private Integer charisma;
    private Integer wisdom;

    private Weapon mainHand;
    private Weapon offHand;

    private Armor armor;

    private List<Carryable> bag;

    public CharacterBase(
            String name,
            RaceType raceType,
            ClassType classType,
            Integer strength,
            Integer constitution,
            Integer dexterity,
            Integer intelligence,
            Integer charisma,
            Integer wisdom,
            Weapon mainHand,
            Weapon offHand,
            Armor armor,
            List<Carryable> bag) {

        this.name = name;
        this.raceType = raceType;
        this.classType = classType;
        this.strength = strength;
        this.constitution = constitution;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
        this.charisma = charisma;
        this.wisdom = wisdom;
        this.mainHand = mainHand;
        this.offHand = offHand;
        this.armor = armor;
        this.bag = bag;
    }

    public String getName() {
        return name;
    }

    public String getMainHandName() {
        return mainHand == null ? "nothing" : mainHand.getName();
    }

    public String getOffHandName() {
        return offHand == null ? "nothing" : offHand.getName();
    }

    public String getArmorName() {
        return armor == null ? "nothing" : armor.getName();
    }

    public String getMainHandIdentifer() {
        return mainHand == null ? "nothing" : mainHand.getIdentifier();
    }

    public String getOffHandIdentifier() {
        return offHand == null ? "nothing" : offHand.getIdentifier();
    }

    public String getArmorIdentifer() {
        return armor == null ? "nothing" : armor.getIdentifier();
    }
    
    @Override
    public String getIdentifier() {

        return String.format(
                "%s,\nholding a %s in his right hand\nand a %s in his left,\nwearing %s\n",
                getName(),
                mainHand == null ? "nothing" : mainHand.getName(),
                offHand == null ? "nothing" : offHand.getName(),
                armor == null ? "nothing" : armor.getName());
    }
}
