package com.cooper.game.interactive;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static com.cooper.Mocks.getMockCarryable;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import org.junit.Test;

import com.cooper.creator.enums.CarryableType;
import com.cooper.creator.model.Carryable;
import com.cooper.creator.model.Weapon;
import com.cooper.dto.InteractiveBlockDTO;
import com.cooper.game.character.InventoryExchanger;

public class TestPracticeDummy {

    private InventoryExchanger exchanger = InventoryExchanger.getTickInventory();

    @Test
    public void testOptions() {

        Interactive dummy = new PracticeDummyInteractive();

        assertEquals(
                new HashSet<>(Arrays.asList("STRIKE!!!", "Get Stats")),
                new HashSet<>(dummy.getOptions(new InventoryExchanger(
                        () -> getMockCarryable(),
                        getMockCarryable(),
                        c -> {}
                )).getCommands()));
    }

    @Test
    public void testCumulationStrikes() {

        InteractiveBlockDTO strikeDto = new InteractiveBlockDTO("STRIKE!!!");
        strikeDto.setArguments(Collections.singletonList("5"));

        InteractiveBlockDTO statCheckDto = new InteractiveBlockDTO("Get Stats");

        Interactive dummy = new PracticeDummyInteractive();

        InteractiveBlockDTO response = dummy.performCommand(strikeDto, exchanger);

        assertEquals(
                "1 points of dmg dealt by fist.",
                response.getMessage());
        assertEquals(
                "1 damage dealt across 1 strikes, for an average damage dealt of 1/strike.",
                dummy.performCommand(statCheckDto, exchanger).getMessage());
    }

    @Test
    public void testWeaponStrike() {

        InteractiveBlockDTO strikeDto = new InteractiveBlockDTO("STRIKE!!!");
        strikeDto.setArguments(Collections.singletonList("5"));

        Interactive dummy = new PracticeDummyInteractive();

        InteractiveBlockDTO response = dummy.performCommand(strikeDto, getMockWeaponExchanger());

        assertEquals(
                "5 points of dmg dealt by mock weapon.",
                response.getMessage());
    }

    private InventoryExchanger getMockWeaponExchanger() {

        InventoryExchanger exchanger = mock(InventoryExchanger.class);
        Carryable wpn = getMockWeapon();
        when(exchanger.lookAt()).thenReturn(wpn);
        return exchanger;
    }

    private Weapon getMockWeapon() {

        Weapon wpn = mock(Weapon.class);
        when(wpn.dmg()).thenReturn(5);
        when(wpn.getName()).thenReturn("mock weapon");
        when(wpn.getType()).thenReturn(CarryableType.WEAPON);
        return wpn;
    }
}
