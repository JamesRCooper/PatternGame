/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.model.dialogue;

import com.cooper.model.Model;

public abstract class Dialogue implements Model {

    public abstract String getFamiliarityBasedResponse(Boolean isFamiliar);
}
