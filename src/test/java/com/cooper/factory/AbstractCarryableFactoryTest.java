/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.factory;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import com.cooper.builder.ArmorBuilder;
import com.cooper.builder.WeaponBuilder;
import com.cooper.models.armor.Armor;
import com.cooper.models.weapons.Weapon;
import com.cooper.utils.JSONHandler;

public class AbstractCarryableFactoryTest {

    private JSONHandler jsonHandler = new JSONHandler("testing builders");

    private FactoryBuilder factoryBuilder = new FactoryBuilder(charaJSON);

    @Test
    public void testWeaponBuilder() {

        JSONObject weaponInfo = jsonHandler.handle(
                JSONObject::new, getWeaponJSON());

        AbstractCarryableFactory<Weapon> weaponBuilder =
                new WeaponBuilder(weaponInfo);

        Weapon weapon = weaponBuilder.build();

        Assert.assertEquals("swift great sword of the beast", weapon.getName());
    }

    private String getWeaponJSON() {
        return "{ \"type\": \"great sword\", \"decorators\": [\"of the beast\"], "
                + "\"predecorators\": [\"swift\"] }";
    }

    @Test
    public void testArmorBuilder() {

        JSONObject armorInfo = jsonHandler.handle(
                JSONObject::new, getArmorJSON());

        AbstractCarryableFactory<Armor> armorBuilder =
                new ArmorBuilder(armorInfo);

        Armor armor = armorBuilder.build();

        Assert.assertEquals("shimmering chain mail", armor.getName());
    }

    private String getArmorJSON() {
        return "{ \"type\": \"chain mail\", \"decorators\": [],\n"
                + "\"predecorators\": [\"shimmering\"] }";
    }

    private static final String charaJSON = "{"
            + "\"profile\": {\n"
            + "                \"type\": \"human\",\n"
            + "                \"name\": \"The Great Gandorian\",\n"
            + "                \"basedDmg\": 10\n"
            + "                },\n"
            + "\"dialogue\": {\n"
            + "             \"type\": \"simple\",\n"
            + "             \"unfamiliar\": \"I look forward to hearing of your accalades\",\n"
            + "             \"familiar\": \"Welcome hero. Are you off on an adventure?\"\n"
            + "             },\n"
            + "\"weapon\": {\n"
            + "             \"type\": \"great sword\",\n"
            + "             \"decorators\": [\"of the beast\"],\n"
            + "             \"predecorators\": [\"swift\"]\n"
            + "             },\n"
            + "\"armor\": {\n"
            + "            \"type\": \"chain mail\",\n"
            + "            \"decorators\": [],\n"
            + "            \"predecorators\": [\"shimmering\"]\n"
            + "            },\n"
            + "}";
}
