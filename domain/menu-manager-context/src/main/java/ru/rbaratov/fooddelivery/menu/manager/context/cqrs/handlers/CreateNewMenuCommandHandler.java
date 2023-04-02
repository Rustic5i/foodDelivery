package ru.rbaratov.fooddelivery.menu.manager.context.cqrs.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.rbaratov.fooddelivery.common.cqrs.command.Command;
import ru.rbaratov.fooddelivery.common.cqrs.handler.CommandHandler;
import ru.rbaratov.fooddelivery.common.valueobject.item.MenuName;
import ru.rbaratov.fooddelivery.menu.manager.context.cqrs.commands.CreateNewMenuCommand;
import ru.rbaratov.fooddelivery.menu.manager.context.domain.Menu;
import ru.rbaratov.fooddelivery.menu.manager.context.factory.MenuFactory;
import ru.rbaratov.fooddelivery.menu.manager.context.repository.menu.MenuRepository;

import java.util.Objects;

@Component
public class CreateNewMenuCommandHandler implements CommandHandler<Void, CreateNewMenuCommand> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateNewMenuCommandHandler.class);

    private static final Class<CreateNewMenuCommand> TYPE_COMMAND = CreateNewMenuCommand.class;

    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private MenuFactory menuFactory;

    /**
     * Создает новое меню
     *
     * @param command команда, которую требуется выполнить.
     * @return {@link Void}
     */
    @Override
    public Void execute(CreateNewMenuCommand command) {
        Objects.requireNonNull(command, "При создании нового меню, не было передано информация о новом меню");

        MenuName menuName = new MenuName(command.getMenuName());
        Menu newMenu = menuFactory.createNewMenu(menuName);
        menuRepository.save(newMenu);

        LOGGER.info("Создали новое меню с название {}", menuName.value());
        return null;
    }

    @Override
    public boolean isAssignable(Command command) {
        return (command != null && TYPE_COMMAND == command.getClass());
    }
}
