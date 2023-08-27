package com.example.exception.database;

public class UpdateDataInDatabaseException extends RuntimeException {
    public UpdateDataInDatabaseException() {
    }

    public UpdateDataInDatabaseException(String message) {
        super(message);
    }

    public UpdateDataInDatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateDataInDatabaseException(Throwable cause) {
        super(cause);
    }

    public UpdateDataInDatabaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
