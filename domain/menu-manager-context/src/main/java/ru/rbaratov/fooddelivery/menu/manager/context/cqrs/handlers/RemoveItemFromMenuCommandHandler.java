package ru.rbaratov.fooddelivery.menu.manager.context.cqrs.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.rbaratov.fooddelivery.common.cqrs.command.Command;
import ru.rbaratov.fooddelivery.common.cqrs.handler.CommandHandler;
import ru.rbaratov.fooddelivery.common.valueobject.item.MenuName;
import ru.rbaratov.fooddelivery.menu.manager.context.cqrs.commands.RemoveItemFromMenuCommand;
import ru.rbaratov.fooddelivery.menu.manager.context.domain.Item;
import ru.rbaratov.fooddelivery.menu.manager.context.domain.Menu;
import ru.rbaratov.fooddelivery.menu.manager.context.repository.item.ItemRepository;
import ru.rbaratov.fooddelivery.menu.manager.context.repository.menu.MenuRepository;

import java.text.MessageFormat;
import java.util.Objects;

@Component
public class RemoveItemFromMenuCommandHandler implements CommandHandler<Void, RemoveItemFromMenuCommand> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RemoveItemFromMenuCommandHandler.class);

    /**
     * Тип команды с которым работает данный обработчик
     */
    private final static Class<RemoveItemFromMenuCommand> TYPE_COMMAND = RemoveItemFromMenuCommand.class;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private ItemRepository itemRepository;

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
        Menu menu = menuRepository.findByName(menuName)
                .orElseThrow(() -> new RuntimeException(MessageFormat.format("Меню с именем {0} не найдено", menuName.value())));

        Item item = itemRepository.findById(command.getItemId())
                .orElseThrow(() -> new RuntimeException(MessageFormat.format("Товар с id: {} не найден", command.getItemId())));

        menu.deleteItem(item);
        LOGGER.info("Из меню {} был удален товар с именем {}", menuName.value(), item.getName().value());

        return null;
    }

    @Override
    public boolean isAssignable(Command command) {
        return (command != null && TYPE_COMMAND == command.getClass());
    }
}
