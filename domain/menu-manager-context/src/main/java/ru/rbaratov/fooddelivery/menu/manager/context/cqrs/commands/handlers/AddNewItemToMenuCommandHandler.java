package ru.rbaratov.fooddelivery.menu.manager.context.cqrs.commands.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rbaratov.fooddelivery.common.cqrs.command.Command;
import ru.rbaratov.fooddelivery.common.cqrs.handler.CommandHandler;
import ru.rbaratov.fooddelivery.menu.manager.context.cqrs.commands.AddNewItemToMenuCommand;
import ru.rbaratov.fooddelivery.menu.manager.context.domain.Item;
import ru.rbaratov.fooddelivery.menu.manager.context.domain.Menu;
import ru.rbaratov.fooddelivery.menu.manager.context.domain.factory.ItemFactory;
import ru.rbaratov.fooddelivery.menu.manager.context.domain.repository.MenuAggregateRepository;

import java.util.Objects;

@Service
public class AddNewItemToMenuCommandHandler implements CommandHandler<Void, AddNewItemToMenuCommand> {

    /**
     * Тип команды с которым работает данный обработчик
     */
    private final static Class<AddNewItemToMenuCommand> TYPE_COMMAND = AddNewItemToMenuCommand.class;

    private static final Logger LOGGER = LoggerFactory.getLogger(AddNewItemToMenuCommandHandler.class);

    @Autowired
    private MenuAggregateRepository aggregateRepository;

    @Autowired
    private ItemFactory itemFactory;

    /**
     * Добавляет новый товар в меню
     *
     * @param command команда, которую требуется выполнить.
     * @return {@link Void}
     */
    @Transactional
    @Override
    public Void execute(AddNewItemToMenuCommand command) {
        Objects.requireNonNull(command, "При добавлении нового товара в меню, не было передана информация о новом товаре");

        Menu menu = aggregateRepository.getMenu();
        Item newItem = itemFactory.createNewItem(command);
        menu.addNewItem(newItem);
        aggregateRepository.save(menu);

        LOGGER.info("В меню добавлен новый товар \"{}\"", newItem.getName().value());
        return null;
    }

    @Override
    public boolean isAssignable(Command command) {
        return (command != null && TYPE_COMMAND == command.getClass());
    }
}
