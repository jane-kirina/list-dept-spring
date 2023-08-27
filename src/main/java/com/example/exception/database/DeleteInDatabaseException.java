package com.example.exception.database;

public class DeleteInDatabaseException extends RuntimeException {
    public DeleteInDatabaseException(Throwable cause) {
        super(cause);
    }

    public DeleteInDatabaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public DeleteInDatabaseException() {
    }

    public DeleteInDatabaseException(String message) {
        super(message);
    }

    public DeleteInDatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
