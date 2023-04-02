package ru.rbaratov.fooddelivery.menu.manager.context.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.rbaratov.fooddelivery.common.valueobject.Money;
import ru.rbaratov.fooddelivery.common.valueobject.item.CategoryName;
import ru.rbaratov.fooddelivery.common.valueobject.item.ItemDescription;
import ru.rbaratov.fooddelivery.common.valueobject.item.ItemName;
import ru.rbaratov.fooddelivery.common.valueobject.item.ItemSize;
import ru.rbaratov.fooddelivery.menu.manager.context.domain.Item;
import ru.rbaratov.fooddelivery.menu.manager.context.domain.ItemCategory;
import ru.rbaratov.fooddelivery.menu.manager.context.dto.ItemDTO;
import ru.rbaratov.fooddelivery.menu.manager.context.repository.category.CategoryRepository;
import ru.rbaratov.fooddelivery.menu.manager.context.repository.item.ItemRepository;

import java.text.MessageFormat;
import java.util.Objects;
import java.util.Optional;

/**
 * Фабрика для создания товара
 */
@Component
public class ItemFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemFactory.class);

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * Создать новый товар
     *
     * @param itemDTO данные о новом товаре
     * @return новый товар
     */
    public Item createNewItem(ItemDTO itemDTO) {
        Objects.requireNonNull(itemDTO, "Отсутствуют данные для создания нового товара");
        ItemName itemName = new ItemName(itemDTO.getItemName());
        Boolean isExistsItem = itemRepository.existsByName(itemName);
        if (isExistsItem) {
            throw new RuntimeException(MessageFormat.format("Товар с именем : {0} уже существует", itemName.value()));
        }

        CategoryName categoryName = new CategoryName(itemDTO.getCategory());
        Optional<ItemCategory> itemCategory = categoryRepository.findByName(categoryName);
        if (itemCategory.isEmpty()) {
            throw new RuntimeException(
                    MessageFormat.format("Ошибка при создания товара с именем : {0}. Категория товара с именем : {1} еще не создана или не сохранена",
                            itemName.value(), categoryName.value()));
        }

        Item newItem = new Item(itemName,
                new Money(itemDTO.getPrice()),
                new ItemDescription(itemDTO.getDescription()),
                new ItemSize(itemDTO.getSize(), itemDTO.getSizeUnit()),
                itemCategory.get());
        LOGGER.info("Создан новый товар с именем : {}", itemName.value());
        return newItem;
    }
}
