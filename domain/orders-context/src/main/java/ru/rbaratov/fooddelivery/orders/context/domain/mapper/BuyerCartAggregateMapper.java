package ru.rbaratov.fooddelivery.orders.context.domain.mapper;

import ru.rbaratov.fooddelivery.orders.context.domain.Buyer;
import ru.rbaratov.fooddelivery.orders.context.domain.Cart;
import ru.rbaratov.fooddelivery.orders.context.entity.BuyerEntity;
import ru.rbaratov.fooddelivery.orders.context.entity.CartEntity;

/**
 * Маппер агрегата
 */
public interface BuyerCartAggregateMapper {

    /**
     * Преобразовать в агрегат
     * @param entity из чего
     * @return
     */
    Cart toBuyerCart(CartEntity entity);

    /**
     * Преобразовать в Entity
     * @param аggregate из чего
     * @return
     */
    CartEntity toBuyerCartEntity(Cart аggregate);

    Buyer to(BuyerEntity entity);
}
