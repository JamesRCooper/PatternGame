/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.models.dialogue;

import com.cooper.models.Model;

public abstract class Dialogue implements Model {

    public abstract String getFamiliarityBasedResponse(Boolean isFamiliar);
}
