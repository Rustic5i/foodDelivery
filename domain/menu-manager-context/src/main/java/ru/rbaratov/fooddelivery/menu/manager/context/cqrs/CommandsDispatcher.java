package ru.rbaratov.fooddelivery.menu.manager.context.cqrs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.rbaratov.fooddelivery.common.cqrs.command.Command;
import ru.rbaratov.fooddelivery.common.cqrs.handler.CommandHandler;

import java.util.Objects;

/**
 * Обработчик обработчиков команд
 */
@Component
public class CommandsDispatcher {

    @Autowired
    public ProviderCommandHandler providerCommandHandler;

    protected Objects run(Command command) {
        CommandHandler commandHandler = providerCommandHandler.getHandler(command);
        return (Objects) commandHandler.execute(command);
    }
}
