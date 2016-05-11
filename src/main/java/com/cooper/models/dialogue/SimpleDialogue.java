/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.models.dialogue;

public class SimpleDialogue extends Dialogue{

    protected String familiarDialogue;
    protected String unFamiliarDialogie;

    public SimpleDialogue(String familiarDialogue, String unFamiliarDialogie) {
        this.familiarDialogue = familiarDialogue;
        this.unFamiliarDialogie = unFamiliarDialogie;
    }

    @Override
    public String getFamiliarityBasedResponse(Boolean isFamiliar) {
        return isFamiliar ? familiarDialogue : unFamiliarDialogie;
    }
}
