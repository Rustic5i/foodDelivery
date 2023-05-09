package ru.rbaratov.fooddelivery.orders.context.domain.repository.cart;

import ru.rbaratov.fooddelivery.orders.context.domain.Item;

import java.util.UUID;

public interface ItemRepository {

    /**
     * Есть ли товар с данным именем
     *
     * @param itemName имя товара
     * @return true если данный товар с таким именем уже есть
     */
    Boolean existsByName(String itemName);

    Item findById(UUID id);
}
