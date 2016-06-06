package com.cooper;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.cooper.builder.CarryableBuilderFactory;
import com.cooper.builder.CharacterBuilder;
import com.cooper.controllers.arena.PoolController;
import com.cooper.controllers.arena.RoomController;
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
import com.cooper.game.arena.Room;
import com.cooper.game.player.ActivePool;

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

    @Bean
    public CarryableBuilderFactory getCarryableBuilderFactory() {

        return new CarryableBuilderFactory(
                armorRepository,
                armorDecoratorRepository,
                weaponRepository,
                weaponDecoratorRepository);
    }

    @Bean
    public CharacterBuilder getCharacterBuilder(
            final CarryableBuilderFactory carryableBuilderFactory) {

        return new CharacterBuilder(
                carryableBuilderFactory,
                characterRepository);
    }

    @Bean
    public PoolController poolController(
            final CharacterBuilder characterBuilder,
            final ActivePool activePool) {

        return new PoolController(
                characterBuilder,
                activePool);
    }

    @Bean
    public RoomController roomController(ActivePool activePool) {
        return new RoomController(activePool);
    }

    @Bean
    public Room getDUNGEON_1() {
        return new Room("src/main/resources/arena/DUNGEON_1.arena");
    }

    @Bean
    public Room getFARM_1() {
        return new Room("src/main/resources/arena/FARM_1.arena");
    }

    @Bean
    public ActivePool getActivePool(Room[] roomCollection) {
        List<Room> rooms = Arrays.asList(roomCollection);
        return new ActivePool(rooms);
    }

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("System loaded");
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
