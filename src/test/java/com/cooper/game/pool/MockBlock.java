package com.cooper.game.pool;

import java.util.List;

import com.cooper.container.LocalResponse;
import com.cooper.game.interactive.Interactive;

public class MockBlock implements Interactive{

    List<String> testString;

    public MockBlock(List<String> testString) {
        this.testString = testString;
    }

    @Override public String getIdentifier() {
        return "mockBlockl";
    }

    @Override public Character getIdentifieingMark() {
        return 'm';
    }

    @Override public Boolean isOccupiable() {
        return false;
    }

    @Override public LocalResponse getOptions() {
        return new LocalResponse("[NONE]");
    }

    @Override public LocalResponse performCommand(String cmd, String args) {
        return new LocalResponse(cmd);
    }

    @Override
    public void beatHeart() {
        testString.add("test");
    }
}
