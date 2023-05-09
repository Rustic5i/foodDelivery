package ru.rbaratov.fooddelivery.menu.manager.context.cqrs.commands.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.rbaratov.fooddelivery.common.cqrs.command.Command;
import ru.rbaratov.fooddelivery.common.cqrs.handler.CommandHandler;
import ru.rbaratov.fooddelivery.common.valueobject.item.CategoryName;
import ru.rbaratov.fooddelivery.common.valueobject.item.MenuName;
import ru.rbaratov.fooddelivery.menu.manager.context.cqrs.commands.AddNewCategoryToMenuCommand;
import ru.rbaratov.fooddelivery.menu.manager.context.domain.Menu;
import ru.rbaratov.fooddelivery.menu.manager.context.domain.repository.MenuAggregateRepository;

import java.text.MessageFormat;
import java.util.Objects;

/**
 * Обработчик команды, добавления новой категории товаров в меню
 */
public class AddNewCategoryToMenuCommandHandler implements CommandHandler<Void, AddNewCategoryToMenuCommand> {

    /**
     * Тип команды с которым работает данный обработчик
     */
    private final static Class<AddNewCategoryToMenuCommand> TYPE_COMMAND = AddNewCategoryToMenuCommand.class;

    private static final Logger LOGGER = LoggerFactory.getLogger(AddNewCategoryToMenuCommandHandler.class);

    @Autowired
    private MenuAggregateRepository aggregateRepository;

    @Override
    public Void execute(AddNewCategoryToMenuCommand command) {
        Objects.requireNonNull(command, "При добавлении новой категории товара в меню, не было передана информация о новой категории");
        final String menuName = command.getMenuName();
        final String categoryName = command.getCategoryName();

        Menu menu = aggregateRepository.findByName(new MenuName(menuName))
                .orElseThrow(() -> new RuntimeException(MessageFormat.format("Меню с именем \"{0}\" не найдено", menuName)));
        menu.createNewCategoryItems(new CategoryName(categoryName));
        aggregateRepository.save(menu);

        LOGGER.info("Создана новая категория товаров с именем [{}], для меню [{}]", categoryName, menuName);
        return null;
    }

    @Override
    public boolean isAssignable(Command command) {
        return (command != null && TYPE_COMMAND == command.getClass());
    }
}
