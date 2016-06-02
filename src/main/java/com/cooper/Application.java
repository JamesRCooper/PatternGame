package com.cooper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.cooper.controllers.ArmorController;
import com.cooper.controllers.ArmorDecoratorController;
import com.cooper.controllers.WeaponController;
import com.cooper.controllers.WeaponDecoratorController;
import com.cooper.data.ArmorDecoratorRepository;
import com.cooper.data.ArmorRepository;
import com.cooper.data.WeaponDecoratorRepository;
import com.cooper.data.WeaponRepository;
import com.cooper.entities.ArmorBase;
import com.cooper.entities.ArmorDecorator;
import com.cooper.entities.WeaponBase;
import com.cooper.entities.WeaponDecorator;
import com.cooper.entities.sub.Dice;
import com.cooper.enums.CarryableType;
import com.cooper.enums.DecoratorPlacement;
import com.cooper.enums.DieType;
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

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        armorRepository.deleteAll();
        armorDecoratorRepository.deleteAll();
        weaponRepository.deleteAll();
        weaponDecoratorRepository.deleteAll();

        armorRepository.save(new ArmorBase(
                "HYDE",
                CarryableType.ARMOR,
                "hyde leather",
                2));
        armorDecoratorRepository.save(new ArmorDecorator(
                "TOUGH",
                CarryableType.ARMOR_DECORATOR,
                "tough",
                DecoratorPlacement.PREFIX,
                1));

        weaponRepository.save(new WeaponBase(
                "GREAT_SWORD",
                CarryableType.WEAPON,
                "great sword",
                new Dice(4, 2, DieType.D4),
                new Dice(5, 1, DieType.D10)));
        WeaponDecorator weaponDecorator = new WeaponDecorator(
                "SHARP",
                CarryableType.WEAPON_DECORATOR,
                "sharp",
                DecoratorPlacement.PREFIX,
                new Dice(0, 2, DieType.D2),
                new Dice(0, 0, DieType.D2));
        weaponDecoratorRepository.save(weaponDecorator);

        Loader loader = new Loader();
        ArmorBase armor = loader.load("src/main/resources/armor/PlateMailArmor.json", ArmorBase.class);
        armorRepository.save(armor);
        ArmorDecorator armorDecorator = loader.load(
                "src/main/resources/armor_decorator/ShimmeringWeaponDecorator.json", ArmorDecorator.class);
        armorDecoratorRepository.save(armorDecorator);


    }
}
