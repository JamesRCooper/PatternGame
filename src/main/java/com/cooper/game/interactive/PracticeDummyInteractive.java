package com.cooper.game.interactive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

import com.cooper.creator.enums.CarryableType;
import com.cooper.creator.model.Weapon;
import com.cooper.dto.InteractiveBlockDTO;
import com.cooper.enums.LocalErrorType;
import com.cooper.game.character.InventoryExchanger;

public class PracticeDummyInteractive implements Interactive {

    private Map<String, BiFunction<List<String>, InventoryExchanger, InteractiveBlockDTO>> commandMethods;

    private Integer timeStruck = 0;
    private Integer damageDealt = 0;

    public PracticeDummyInteractive() {

        this.commandMethods = new HashMap<>();
        this.commandMethods.put("STRIKE!!!", this::strike);
        this.commandMethods.put("Get Stats", this::getStats);
    }

    @Override
    public String getIdentifier() {
        return "practice dummy: struck " + timeStruck + " times";
    }

    @Override
    public Character getIdentifieingToken() {
        return 'p';
    }

    @Override
    public Boolean isOccupiable() {
        return false;
    }

    @Override
    public InteractiveBlockDTO getOptions(InventoryExchanger exchanger) {

        List<String> commands = new ArrayList<>(commandMethods.keySet());
        return new InteractiveBlockDTO(commands);
    }

    @Override
    public InteractiveBlockDTO performCommand(
            InteractiveBlockDTO blockDTO,
            InventoryExchanger exchanger) {

        if(blockDTO.getCommands() == null || blockDTO.getCommands().size() == 0)
            return new InteractiveBlockDTO(LocalErrorType.NO_COMMAND_GIVEN);
        if(!commandMethods.containsKey(blockDTO.getCommands().get(0))) {
            return new InteractiveBlockDTO(LocalErrorType.COMMAND_DOES_NOT_EXIST);
        }

        return commandMethods
                .get(blockDTO.getCommands().get(0))
                .apply(blockDTO.getArguments(), exchanger);
    }

    private InteractiveBlockDTO strike(List<String> args, InventoryExchanger exchanger) {

        Integer dmg;
        String weaponIdentifier;


        if (exchanger.lookAt() != null && exchanger.lookAt().getType() == CarryableType.WEAPON) {
            dmg = ((Weapon) exchanger.lookAt()).dmg();
            weaponIdentifier = ((Weapon) exchanger.lookAt()).getName();
        } else {
            dmg = 1;
            weaponIdentifier = "fist";
        }

        timeStruck++;
        damageDealt += dmg;
        InteractiveBlockDTO dto = new InteractiveBlockDTO();
        dto.setMessage(String.format("%d points of dmg dealt by %s.", dmg, weaponIdentifier));
        return dto;
    }

    private InteractiveBlockDTO getStats(List<String> args, InventoryExchanger exchanger) {

        InteractiveBlockDTO dto = new InteractiveBlockDTO();
        String msgTemplate = "%d damage dealt across %d strikes, for an average damage dealt of %d/strike.";
        dto.setMessage(String.format(msgTemplate, damageDealt, timeStruck, damageDealt/timeStruck));
        return dto;
    }

    @Override
    public void beatHeart() {

    }
}
