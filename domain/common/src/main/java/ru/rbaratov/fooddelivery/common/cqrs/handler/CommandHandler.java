package ru.rbaratov.fooddelivery.common.cqrs.handler;

import ru.rbaratov.fooddelivery.common.cqrs.command.Command;

/**
 * Обработчик команд.
 *
 * @param <RESULT> тип результата. В случае если результат выполнения команды не требуется, требуется передать тип {@link Void}.
 */
public interface CommandHandler<RESULT, COMMAND extends Command> {

    /**
     * Выполняет переданную команду.
     *
     * @param command команда, которую требуется выполнить.
     * @return результат выполнения команды.
     */
    RESULT execute(COMMAND command);

    /**
     * Работает ли данный обработчик с данной командой.
     *
     * @param command команда.
     * @return true, если обработчик работает с данной командой.
     */
    boolean isAssignable(Command command);
}
