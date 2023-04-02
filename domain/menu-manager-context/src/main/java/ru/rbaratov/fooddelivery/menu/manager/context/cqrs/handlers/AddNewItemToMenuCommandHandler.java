package ru.rbaratov.fooddelivery.menu.manager.context.cqrs.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.rbaratov.fooddelivery.common.cqrs.command.Command;
import ru.rbaratov.fooddelivery.common.cqrs.handler.CommandHandler;
import ru.rbaratov.fooddelivery.common.valueobject.item.MenuName;
import ru.rbaratov.fooddelivery.menu.manager.context.cqrs.commands.AddNewItemToMenuCommand;
import ru.rbaratov.fooddelivery.menu.manager.context.domain.Item;
import ru.rbaratov.fooddelivery.menu.manager.context.domain.Menu;
import ru.rbaratov.fooddelivery.menu.manager.context.dto.ItemDTO;
import ru.rbaratov.fooddelivery.menu.manager.context.factory.ItemFactory;
import ru.rbaratov.fooddelivery.menu.manager.context.repository.menu.MenuRepository;

import java.text.MessageFormat;
import java.util.Objects;

@Component
public class AddNewItemToMenuCommandHandler implements CommandHandler<Void, AddNewItemToMenuCommand> {

    /**
     * Тип команды с которым работает данный обработчик
     */
    private final static Class<AddNewItemToMenuCommand> TYPE_COMMAND = AddNewItemToMenuCommand.class;

    private static final Logger LOGGER = LoggerFactory.getLogger(AddNewItemToMenuCommandHandler.class);

    @Autowired
    private MenuRepository menuRepository;

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
        MenuName menuName = new MenuName(command.getMenuName());
        Menu menu = menuRepository.findByName(menuName)
                .orElseThrow(() -> new RuntimeException(MessageFormat.format("Меню с именем {0} не найдено", menuName.value())));

        ItemDTO itemDTO = commandToDTO(command);
        Item newItem = itemFactory.createNewItem(itemDTO);

        menu.addNewItem(newItem);
        menuRepository.save(menu);
        LOGGER.info("В меню {} добавлен новый товар {}", menuName.value(), newItem.getName().value());
        return null;
    }

    @Override
    public boolean isAssignable(Command command) {
        return (command != null && TYPE_COMMAND == command.getClass());
    }

    private ItemDTO commandToDTO(AddNewItemToMenuCommand command) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setItemName(command.getItemName());
        itemDTO.setCategory(command.getItemCategory());
        itemDTO.setDescription(command.getItemDescription());
        itemDTO.setPrice(command.getPrice());
        itemDTO.setSize(command.getItemSize());
        itemDTO.setSizeUnit(command.getSizeUnit());
        return itemDTO;
    }

}
