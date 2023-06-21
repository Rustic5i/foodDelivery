package ru.rbaratov.fooddelivery.menu.manager.context.domain.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.rbaratov.fooddelivery.common.valueobject.Money;
import ru.rbaratov.fooddelivery.common.valueobject.item.ItemDescription;
import ru.rbaratov.fooddelivery.common.valueobject.item.ItemName;
import ru.rbaratov.fooddelivery.common.valueobject.item.ItemSize;
import ru.rbaratov.fooddelivery.menu.manager.context.cqrs.commands.AddNewItemToMenuCommand;
import ru.rbaratov.fooddelivery.menu.manager.context.domain.Category;
import ru.rbaratov.fooddelivery.menu.manager.context.domain.Item;

import java.util.Objects;

/**
 * Фабрика для создания товара
 */
@Component
public class ItemFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemFactory.class);

    /**
     * Создать новый товар
     *
     * @param command данные о новом товаре
     * @return новый товар
     */
    public Item createNewItem(AddNewItemToMenuCommand command) {
        Objects.requireNonNull(command, "Отсутствуют данные для создания нового товара");
        ItemName itemName = new ItemName(command.getItemName());
        Item newItem = new Item(itemName,
                new Money(command.getPrice()),
                new ItemDescription(command.getItemDescription()),
                new ItemSize(command.getItemSize(), command.getSizeUnit()),
                new Category(command.getItemCategory()),
                null);
        LOGGER.info("Создан новый товар с именем : \"{}\"", itemName.value());
        return newItem;
    }
}
