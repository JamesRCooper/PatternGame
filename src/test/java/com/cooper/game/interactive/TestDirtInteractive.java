package com.cooper.game.interactive;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.cooper.container.LocalError;
import com.cooper.dto.InteractiveBlockDTO;
import com.cooper.enums.LocalErrorType;
import com.cooper.game.interactive.dirt.DirtState;

public class TestDirtInteractive {

    private String dirtState1Name = "state 1";
    private String dirtState2Name = "state 2";

    @Test
    public void testIdentifier() {

        Interactive dirtBlock = new DirtInteractive();
        assertEquals("Dirt: Tilled Soil", dirtBlock.getIdentifier());
    }

    @Test
    public void testHeartBeat() {

        DirtInteractive dirtBlock = new DirtInteractive(getMockDirtState());

        dirtBlock.beatHeart();
        assertEquals("Dirt: " + dirtState2Name, dirtBlock.getIdentifier());
    }

    private DirtState getMockDirtState() {

        DirtState dirtState1 = mock(DirtState.class);
        DirtState dirtState2 = mock(DirtState.class);
        when(dirtState1.performCommandForNewState(any(InteractiveBlockDTO.class)))
                .thenReturn(dirtState2);
        when(dirtState1.getIdentifier()).thenReturn(dirtState1Name);
        when(dirtState2.performCommandForNewState(any(InteractiveBlockDTO.class)))
                .thenReturn(dirtState1);
        when(dirtState2.getIdentifier()).thenReturn(dirtState2Name);

        return dirtState1;
    }

    @Test
    public void testMissingCommands() {

        Interactive dirtBlock = new DirtInteractive(getMockDirtState());

        InteractiveBlockDTO blockDto = dirtBlock.performCommand(new InteractiveBlockDTO());
        assertFalse(blockDto.isSuccessful());
    }

    @Test
    public void testIllegalTickCall() {

        Interactive dirtBlock = new DirtInteractive(getMockDirtState());

        InteractiveBlockDTO blockDto = dirtBlock.performCommand(new InteractiveBlockDTO("TICK"));
        assertFalse(blockDto.isSuccessful());
    }

    @Test
    public void testLocalErrorCatch() {

        Interactive dirtBlock = new DirtInteractive(getErroredDirtState());

        InteractiveBlockDTO blockDto = dirtBlock.performCommand(new InteractiveBlockDTO("REAL_COMMAND"));
        assertEquals(LocalErrorType.COMMAND_DOES_NOT_EXIST, blockDto.getErrors().get(0));
    }

    private DirtState getErroredDirtState() {

        DirtState dirtState = mock(DirtState.class);
        when(dirtState.performCommandForNewState(any(InteractiveBlockDTO.class)))
                .thenThrow(new LocalError("", LocalErrorType.COMMAND_DOES_NOT_EXIST));
        return dirtState;
    }
}
