package com.example.exception.database;

public class AddInDatabaseException extends RuntimeException {
    public AddInDatabaseException(Throwable cause) {
        super(cause);
    }

    public AddInDatabaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public AddInDatabaseException() {
    }

    public AddInDatabaseException(String message) {
        super(message);
    }

    public AddInDatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
