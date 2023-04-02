package ru.rbaratov.fooddelivery.common.cqrs.command;

/**
 * Команда
 */
public interface Command {

    /**
     * @return true, если команда должны выполняться асинхронно.
     * Если true, то {@link ru.rbaratov.fooddelivery.common.cqrs.handler.CommandHandler} должен возвращать {@link Void},
     * Иначе сервер выдаст исключение.
     */
    boolean asynchronous();
}
