package ru.rbaratov.fooddelivery.orders.context.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rbaratov.fooddelivery.orders.context.entity.ItemEntity;

import java.util.UUID;

/**
 * Доступ к данным выбранных товаров {@link ItemEntity}
 */
@Repository
public interface ItemEntityRepository extends JpaRepository<ItemEntity, UUID> {

    /**
     * Есть ли товар с данным именем
     *
     * @param itemName имя товара
     * @return true если данный товар с таким именем уже есть
     */
    Boolean existsByName(String itemName);
}
