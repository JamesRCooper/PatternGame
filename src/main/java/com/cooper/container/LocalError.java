package com.cooper.container;

import com.cooper.enums.LocalErrorType;

public class LocalError extends RuntimeException {

    private LocalErrorType errorType;

    public LocalError(String message, LocalErrorType errorType) {
        super(message);
        this.errorType = errorType;
    }

    public LocalError(String message, Throwable cause, LocalErrorType errorType) {
        super(message, cause);
        this.errorType = errorType;
    }

    public LocalErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(LocalErrorType errorType) {
        this.errorType = errorType;
    }
}
