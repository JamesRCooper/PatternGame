/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.creator.exceptions;

public class JSONRunTimeException extends RuntimeException {

    public JSONRunTimeException(String operation, Throwable cause) {
        super(operation, cause);
    }
}
