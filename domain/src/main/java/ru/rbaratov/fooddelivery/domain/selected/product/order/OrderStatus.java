package ru.rbaratov.fooddelivery.domain.selected.product.order;

/**
 * Статус заказов
 */
public enum OrderStatus {

    /**
     * Принят
     */
    ACCEPTED,

    /**
     * Уплачен
     */
    PAID,

    /**
     * Готовится
     */
    PREPARATION,

    /**
     * В пути
     */
    ON_THE_WAY,

    /**
     * Доставлен
     */
    DELIVERED,

    /**
     * Отменен покупателем
     */
    CANCELED_BY_BUYER,

    /**
     * Отменен администратором
     */
    CANCELED_BY_ADMINISTRATOR
}
