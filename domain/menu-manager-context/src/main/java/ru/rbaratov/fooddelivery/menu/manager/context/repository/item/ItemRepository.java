package ru.rbaratov.fooddelivery.menu.manager.context.repository.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rbaratov.fooddelivery.common.valueobject.item.ItemName;
import ru.rbaratov.fooddelivery.menu.manager.context.domain.Item;

import java.util.UUID;

/**
 * Доступ к данным товара {@link Item}
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, UUID> {

    /**
     * Есть ли товар с данным именем
     *
     * @param itemName имя товара
     * @return true если данный товар с таким именем уже есть
     */
    Boolean existsByName(ItemName itemName);
}
