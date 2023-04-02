package ru.rbaratov.fooddelivery.orders.context.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rbaratov.fooddelivery.common.valueobject.item.ItemName;
import ru.rbaratov.fooddelivery.orders.context.domain.SelectItem;

import java.util.UUID;

/**
 * Доступ к данным выбранных товаров {@link SelectItem}
 */
@Repository
public interface SelectItemRepository extends JpaRepository<SelectItem, UUID> {

    /**
     * Есть ли товар с данным именем
     *
     * @param itemName имя товара
     * @return true если данный товар с таким именем уже есть
     */
    Boolean existsByName(ItemName itemName);
}
