package com.cooper;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import com.cooper.controllers.entity.ArmorController;
import com.cooper.controllers.entity.ArmorDecoratorController;
import com.cooper.controllers.entity.CharacterController;
import com.cooper.controllers.entity.WeaponController;
import com.cooper.controllers.entity.WeaponDecoratorController;
import com.cooper.data.ArmorDecoratorRepository;
import com.cooper.data.ArmorRepository;
import com.cooper.data.CharacterRepository;
import com.cooper.data.WeaponDecoratorRepository;
import com.cooper.data.WeaponRepository;
import com.cooper.game.arena.RoomPipes;
import com.cooper.loader.Loader;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private ArmorRepository armorRepository;

    @Autowired
    private ArmorDecoratorRepository armorDecoratorRepository;

    @Autowired
    private WeaponRepository weaponRepository;

    @Autowired
    private WeaponDecoratorRepository weaponDecoratorRepository;

    @Autowired
    private CharacterRepository characterRepository;

    @Bean
    public ArmorController armorController() {
        return new ArmorController(armorRepository);
    }

    @Bean
    public ArmorDecoratorController armorDecoratorController() {
        return new ArmorDecoratorController(armorDecoratorRepository);
    }

    @Bean
    public WeaponController weaponController() {
        return new WeaponController(weaponRepository);
    }

    @Bean
    public WeaponDecoratorController weaponDecoratorController() {
        return new WeaponDecoratorController(weaponDecoratorRepository);
    }

    @Bean
    public CharacterController characterController() {
        return new CharacterController(characterRepository);
    }

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        //armorRepository.deleteAll();
        //armorDecoratorRepository.deleteAll();
        //weaponRepository.deleteAll();
        //weaponDecoratorRepository.deleteAll();

        /*Loader loader = new Loader(
                armorRepository,
                armorDecoratorRepository,
                weaponRepository,
                weaponDecoratorRepository,
                characterRepository);
        loader.loadAll();*/
    }
}
