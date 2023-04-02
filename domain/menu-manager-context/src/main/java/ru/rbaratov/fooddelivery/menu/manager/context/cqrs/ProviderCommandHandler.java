package ru.rbaratov.fooddelivery.menu.manager.context.cqrs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.rbaratov.fooddelivery.common.cqrs.command.Command;
import ru.rbaratov.fooddelivery.common.cqrs.handler.CommandHandler;

import java.text.MessageFormat;
import java.util.List;

/**
 * Провайдер обработчиков команд.
 */
@Component
public class ProviderCommandHandler {

    @Autowired
    private List<CommandHandler> commandHandlerList;

    /**
     * Получить обработчик соответствующей переданной команде.
     *
     * @param command команда.
     * @return Обработчик соответствующей переданной команде.
     */
    public CommandHandler getHandler(Command command) {
        return commandHandlerList.stream().filter(c -> c.isAssignable(command))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(MessageFormat.format("Для команды {} не был найден подходящий обработчик", command.toString())));
    }
}
