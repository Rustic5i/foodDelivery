package ru.rbaratov.fooddelivery.common.exception;

/**
 * Ошибка приложения
 */
public class RuntimeApplicationException extends RuntimeException {

    public RuntimeApplicationException(String message) {
        super(message);
    }

    public RuntimeApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
