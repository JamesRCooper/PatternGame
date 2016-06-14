package com.cooper.game.interactive;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import com.cooper.game.interactive.dirt.Ripe;
import com.cooper.game.interactive.dirt.Sprouts;
import com.cooper.game.interactive.dirt.Tilled;

public class InteractiveFactory {

    private Map<String, Function<String, Interactive>> interactiveCreatorLookup = new HashMap<>();

    public InteractiveFactory() {

        interactiveCreatorLookup.put("SIGN", SignInteractive::new);
        interactiveCreatorLookup.put("DIRT", state -> {
            switch (state) {
            case ("Tilled"):
                return new DirtInteractive(new Tilled());
            case ("Sprouts"):
                return new DirtInteractive(new Sprouts());
            case ("Ripe"):
                return new DirtInteractive(new Ripe());
            }
            throw new RuntimeException("Dirt could not be initialized with state: " + state);
        });
        interactiveCreatorLookup.put("DUMMY", s -> new PracticeDummyInteractive());
    }

    public Supplier<Interactive> getInteractiveFromMetaRow(String name, String argument) {

        return () -> interactiveCreatorLookup.get(name).apply(argument);
    }
}
