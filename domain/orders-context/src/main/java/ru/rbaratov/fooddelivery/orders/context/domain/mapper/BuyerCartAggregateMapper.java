package ru.rbaratov.fooddelivery.orders.context.domain.mapper;

import ru.rbaratov.fooddelivery.orders.context.domain.BuyerCart;
import ru.rbaratov.fooddelivery.orders.context.entity.BuyerCartEntity;

/**
 * Маппер агрегата
 */
public interface BuyerCartAggregateMapper {

    /**
     * Преобразовать в агрегат
     * @param entity из чего
     * @return
     */
    BuyerCart toBuyerCart(BuyerCartEntity entity);

    /**
     * Преобразовать в Entity
     * @param аggregate из чего
     * @return
     */
    BuyerCartEntity toBuyerCartEntity(BuyerCart аggregate);
}
