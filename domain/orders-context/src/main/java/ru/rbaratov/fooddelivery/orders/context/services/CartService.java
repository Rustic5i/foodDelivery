package ru.rbaratov.fooddelivery.orders.context.services;

import ru.rbaratov.fooddelivery.orders.context.domain.BuyerCart;

import java.util.UUID;

/**
 * Сервис обслуживания документа корзины {@link BuyerCart}
 */
public interface CartService {

    /**
     * Добавить товар в корзину
     *
     * @param addItem товар, который требуется добавить в корзину
     */
    void addItemToCart(UUID addItem);

    /**
     * Удалить товар из корзины
     *
     * @param removeItem товар, который требуется удалить из корзины
     */
    void removeItemFromCart(UUID removeItem);
}
