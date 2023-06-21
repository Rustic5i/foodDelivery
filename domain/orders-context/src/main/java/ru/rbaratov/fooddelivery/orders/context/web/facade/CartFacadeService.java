package ru.rbaratov.fooddelivery.orders.context.web.facade;

import ru.rbaratov.fooddelivery.orders.context.dto.CartInfoDTO;

import java.util.UUID;

/**
 * Фасадный сервис по корзине покупателя
 */
public interface CartFacadeService {

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
     * Получить информацию по корзине покупателя
     *
     * @return информация по корзине покупателя
     */
    CartInfoDTO getBuyerCartInfo();
}
