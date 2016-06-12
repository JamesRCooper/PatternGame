package com.cooper.game.pool;

import java.util.Collections;
import java.util.List;

import com.cooper.container.LocalResponse;
import com.cooper.dto.InteractiveBlockDTO;
import com.cooper.game.interactive.Interactive;

public class MockBlock implements Interactive{

    List<String> testString;

    public MockBlock(List<String> testString) {
        this.testString = testString;
    }

    @Override public String getIdentifier() {
        return "mockBlockl";
    }

    @Override public Character getIdentifieingToken() {
        return 'm';
    }

    @Override public Boolean isOccupiable() {
        return false;
    }

    @Override public InteractiveBlockDTO getOptions() {
        return new InteractiveBlockDTO(Collections.singletonList("NONE"));
    }

    @Override public InteractiveBlockDTO performCommand(InteractiveBlockDTO blockDTO) {
        return new InteractiveBlockDTO(Collections.singletonList("NONE"));
    }

    @Override
    public void beatHeart() {
        testString.add("test");
    }
}
