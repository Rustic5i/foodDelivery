package ru.rbaratov.fooddelivery.orders.context.domain;

import ru.rbaratov.fooddelivery.common.entity.AbstractEntity;
import ru.rbaratov.fooddelivery.common.valueobject.Money;
import ru.rbaratov.fooddelivery.orders.context.domain.valueobject.Quantity;

import java.util.Objects;
import java.util.UUID;

/**
 * Выбранный товар в корзине.
 * Связь корзина-товар
 */
public class SelectItemInCart extends AbstractEntity {

    /**
     * Товар
     */
    private Item item;

    /**
     * Количество товара
     */
    private Quantity quantity = new Quantity(0);

    /**
     * Итоговая цена
     */
    private Money totalPrice = new Money(0);

    public SelectItemInCart(Item item, Quantity quantity) {
        this.item = item;
        this.quantity = quantity;
        recalculateTotalPrice();
    }

    protected SelectItemInCart(Item item) {
        Objects.requireNonNull(item);
        this.item = item;
        quantity.increment();
        totalPrice = new Money(item.getPrice());
    }

    /**
     * Добавить еще один товар.
     * Увеличивает данную позицию товара на одну единицу.
     *
     * @return количества данной позиции в корзине.
     */
    protected Integer addOneMore() {
        Integer count = quantity.increment();
        recalculateTotalPrice();
        return count;
    }

    /**
     * Убрать один товар.
     * Уменьшает данную позицию товара на одну единицу.
     *
     * @return количества данной позиции в корзине
     */
    protected Integer removeOne() {
        Integer count = quantity.decrement();
        recalculateTotalPrice();
        return count;
    }

    /**
     * @return количества данной позиции в корзине
     */
    public Integer showQuantity() {
        return quantity.value();
    }

    /**
     * @return Итоговая цена
     */
    public Float showTotalPrice() {
        recalculateTotalPrice();
        return totalPrice.value();
    }

    /**
     * Показать id выбранного товара
     *
     * @return id выбранного товара
     */
    public UUID showIdSelectItem() {
        return item.getId();
    }

    /**
     * Получить товар
     *
     * @return товар
     */
    protected Item getItem() {
        return item;
    }

    /**
     * Сделать перерасчет итоговый цены по данной позиции
     */
    protected SelectItemInCart recalculateTotalPrice() {
        totalPrice = new Money(item.getPrice());
        totalPrice.multiply(quantity.value());
        return this;
    }
}
