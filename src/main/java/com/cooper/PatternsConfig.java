/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.cooper.controllers.TalkingController;
import com.cooper.factory.WeaponFactory;
import com.cooper.loader.WeaponLoader;
import com.cooper.models.character.Character;

@Configuration
@ComponentScan(basePackageClasses = { Character.class})
public class PatternsConfig {

    @Bean
    public WeaponFactory weaponFactory() {
        WeaponLoader loader = new WeaponLoader();
        return new WeaponFactory(loader.getItems());
    }

    @Bean
    public TalkingController talkingController() {
        return new TalkingController();
    }
}
