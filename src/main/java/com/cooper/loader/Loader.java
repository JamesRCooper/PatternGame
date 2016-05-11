/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import com.cooper.factory.AbstractCarryableFactory;
import com.cooper.models.Carryable;
import com.cooper.utils.Dice;

public abstract class Loader<T extends Carryable> {

    protected Map<String, T> items;
    protected String rootDirectory;

    public Loader(String rootDirectory) {
        this.rootDirectory = rootDirectory;
        items = new HashMap<>();
        populateItems();
    }

    private void populateItems() {

        List<File> itemFiles = collectItemFiles();
        itemFiles.forEach(this::loadItem);
    }

    private List<File> collectItemFiles() {
        File directory = new File(rootDirectory);
        File[] files = directory.listFiles();
        if (files != null) {
            return Arrays.asList(files);
        } else {
            return new ArrayList<>();
        }
    }

    private void loadItem(File file) {
        String itemSheet = getJsonSheetFromFile(file);
        T item = createItemFromJsonSheet(itemSheet);
        items.put(item.getIdentifier(), item);
    }

    private String getJsonSheetFromFile(File file) {
        try (FileInputStream stream = new FileInputStream(file)) {
            byte[] bytes = new byte[(int) file.length()];
            stream.read(bytes);
            stream.close();
            return new String(bytes, "UTF-8");
        } catch (IOException ioEx) {
            throw new RuntimeException(ioEx);
        }
    }

    protected abstract T createItemFromJsonSheet(final String itemSheet);

    protected Function<Integer, Integer> getDiceRoller(String diceType, Integer diceNumber) {

        switch (diceType) {
        case ("d2"):
            return (augment) -> Dice.xd2_plusy(diceNumber, augment);
        case ("d4"):
            return (augment) -> Dice.xd4_plusy(diceNumber, augment);
        case ("d6"):
            return (augment) -> Dice.xd6_plusy(diceNumber, augment);
        case ("d8"):
            return (augment) -> Dice.xd8_plusy(diceNumber, augment);
        case ("d10"):
            return (augment) -> Dice.xd10_plusy(diceNumber, augment);
        case ("d12"):
            return (augment) -> Dice.xd12_plusy(diceNumber, augment);
        case ("d20"):
            return (augment) -> Dice.xd20_plusy(diceNumber, augment);
        default:
            return (augment) -> augment;
        }
    }

    public Map<String, T> getItems() {
        return items;
    }
}
