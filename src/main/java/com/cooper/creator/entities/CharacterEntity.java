/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.creator.entities;

import java.util.List;

import org.springframework.data.annotation.Id;

import com.cooper.creator.entities.sub.ArmorCompositeEntity;
import com.cooper.creator.entities.sub.WeaponCompositeEntity;
import com.cooper.creator.enums.ClassType;
import com.cooper.creator.enums.RaceType;
import com.cooper.creator.model.Carryable;

public class CharacterEntity {

    @Id
    private String name;

    private RaceType raceType;
    private ClassType classType;

    private Integer strength;
    private Integer constitution;
    private Integer dexterity;
    private Integer intelligence;
    private Integer charisma;
    private Integer wisdom;

    private WeaponCompositeEntity mainHand;
    private WeaponCompositeEntity offHand;

    private ArmorCompositeEntity armor;

    private List<Carryable> bag;

    public CharacterEntity(
            String name,
            RaceType raceType,
            ClassType classType,
            Integer strength,
            Integer constitution,
            Integer dexterity,
            Integer intelligence,
            Integer charisma,
            Integer wisdom,
            WeaponCompositeEntity mainHand,
            WeaponCompositeEntity offHand,
            ArmorCompositeEntity armor, List<Carryable> bag) {
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

    public CharacterEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RaceType getRaceType() {
        return raceType;
    }

    public void setRaceType(RaceType raceType) {
        this.raceType = raceType;
    }

    public ClassType getClassType() {
        return classType;
    }

    public void setClassType(ClassType classType) {
        this.classType = classType;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public Integer getConstitution() {
        return constitution;
    }

    public void setConstitution(Integer constitution) {
        this.constitution = constitution;
    }

    public Integer getDexterity() {
        return dexterity;
    }

    public void setDexterity(Integer dexterity) {
        this.dexterity = dexterity;
    }

    public Integer getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(Integer intelligence) {
        this.intelligence = intelligence;
    }

    public Integer getCharisma() {
        return charisma;
    }

    public void setCharisma(Integer charisma) {
        this.charisma = charisma;
    }

    public Integer getWisdom() {
        return wisdom;
    }

    public void setWisdom(Integer wisdom) {
        this.wisdom = wisdom;
    }

    public WeaponCompositeEntity getMainHand() {
        return mainHand;
    }

    public void setMainHand(WeaponCompositeEntity mainHand) {
        this.mainHand = mainHand;
    }

    public WeaponCompositeEntity getOffHand() {
        return offHand;
    }

    public void setOffHand(WeaponCompositeEntity offHand) {
        this.offHand = offHand;
    }

    public ArmorCompositeEntity getArmor() {
        return armor;
    }

    public void setArmor(ArmorCompositeEntity armor) {
        this.armor = armor;
    }

    public List<Carryable> getBag() {
        return bag;
    }

    public void setBag(List<Carryable> bag) {
        this.bag = bag;
    }
}
