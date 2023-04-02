package ru.rbaratov.fooddelivery.menu.manager.context.cqrs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.rbaratov.fooddelivery.common.cqrs.command.Command;

import java.util.Objects;

@Component
public class Gate {

    @Autowired
    private CommandsDispatcher commandsDispatcher;

    /**
     * Отправляет команду на выполнение
     *
     * @param command команда
     * @return результат выполнения команды
     */
    public Objects send(Command command) {
        return commandsDispatcher.run(command);
    }

}
