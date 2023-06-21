package ru.rbaratov.fooddelivery.menu.manager.context.cqrs.commands.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.rbaratov.fooddelivery.common.cqrs.command.Command;
import ru.rbaratov.fooddelivery.common.cqrs.handler.CommandHandler;
import ru.rbaratov.fooddelivery.menu.manager.context.cqrs.commands.CreateNewCategoryCommand;
import ru.rbaratov.fooddelivery.menu.manager.context.domain.Category;
import ru.rbaratov.fooddelivery.menu.manager.context.domain.repository.ItemCategoryDomainRepository;

import java.util.Objects;

/**
 * Обработчик команды, добавления новой категории товаров
 */
@Component
public class CreateNewCategoryCommandHandler implements CommandHandler<Void, CreateNewCategoryCommand> {

    /**
     * Тип команды с которым работает данный обработчик
     */
    private final static Class<CreateNewCategoryCommand> TYPE_COMMAND = CreateNewCategoryCommand.class;

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateNewCategoryCommandHandler.class);

    @Autowired
    private ItemCategoryDomainRepository categoryDomainRepository;

    @Override
    public Void execute(CreateNewCategoryCommand command) {
        Objects.requireNonNull(command, "При создании новой категории товара, не было передана информация о новой категории");
        final String categoryName = command.getCategoryName();
        Category category = new Category(categoryName);
        categoryDomainRepository.save(category);

        LOGGER.info("Создана новая категория товаров с именем \"{}\"", categoryName);
        return null;
    }

    @Override
    public boolean isAssignable(Command command) {
        return (command != null && TYPE_COMMAND == command.getClass());
    }
}
