package ru.rbaratov.fooddelivery.orders.context.domain.services;

import ru.rbaratov.fooddelivery.orders.context.domain.Cart;
import ru.rbaratov.fooddelivery.orders.context.domain.valueobject.PhoneNumber;
import ru.rbaratov.fooddelivery.orders.context.entity.CartEntity;

import java.util.UUID;

/**
 * Сервис обслуживания документа корзины {@link Cart}
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

    /**
     * Создать корзину
     * @param phoneNumber
     * @return
     */
    CartEntity createCart(PhoneNumber phoneNumber);
}
