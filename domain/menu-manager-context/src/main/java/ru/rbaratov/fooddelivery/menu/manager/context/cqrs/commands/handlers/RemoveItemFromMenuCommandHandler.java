package ru.rbaratov.fooddelivery.menu.manager.context.cqrs.commands.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rbaratov.fooddelivery.common.cqrs.command.Command;
import ru.rbaratov.fooddelivery.common.cqrs.handler.CommandHandler;
import ru.rbaratov.fooddelivery.common.valueobject.item.MenuName;
import ru.rbaratov.fooddelivery.menu.manager.context.cqrs.commands.RemoveItemFromMenuCommand;
import ru.rbaratov.fooddelivery.menu.manager.context.domain.Menu;
import ru.rbaratov.fooddelivery.menu.manager.context.domain.repository.MenuAggregateRepository;

import java.text.MessageFormat;
import java.util.Objects;

@Service
public class RemoveItemFromMenuCommandHandler implements CommandHandler<Void, RemoveItemFromMenuCommand> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RemoveItemFromMenuCommandHandler.class);

    /**
     * Тип команды с которым работает данный обработчик
     */
    private final static Class<RemoveItemFromMenuCommand> TYPE_COMMAND = RemoveItemFromMenuCommand.class;

    @Autowired
    private MenuAggregateRepository aggregateRepository;

    /**
     * Удаляет товар из меню
     *
     * @param command команда, которую требуется выполнить.
     * @return {@link Void}
     */
    @Transactional
    @Override
    public Void execute(RemoveItemFromMenuCommand command) {
        Objects.requireNonNull(command, "При удалении товара из меню, не было передана информация о товаре");

        MenuName menuName = new MenuName(command.getMenuName());
        Menu menu = aggregateRepository.findByName(menuName)
                .orElseThrow(() -> new RuntimeException(MessageFormat.format("Меню с именем {0} не найдено", menuName.value())));
        menu.deleteItem(command.getItemId());
        aggregateRepository.save(menu);

        LOGGER.info("Из меню {} был удален товар с id {}", menuName.value(), command.getItemId());
        return null;
    }

    @Override
    public boolean isAssignable(Command command) {
        return (command != null && TYPE_COMMAND == command.getClass());
    }
}
