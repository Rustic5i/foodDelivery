package ru.rbaratov.fooddelivery.orders.context.domain;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import ru.rbaratov.fooddelivery.common.entity.AbstractEntity;
import ru.rbaratov.fooddelivery.common.valueobject.Money;
import ru.rbaratov.fooddelivery.orders.context.domain.valueobject.Quantity;

import java.util.Objects;

/**
 * Выбранный товар в корзине.
 * Связь корзина-товар
 */
@Entity
@Table(name = "cart_item")
@Embeddable
public class SelectItemInCart extends AbstractEntity {

    /**
     * Товар
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    private SelectItem selectItem;

    /**
     * Количество товара
     */
    @AttributeOverride(name = "getQuantity", column = @Column(name = "getQuantity", nullable = false))
    private Quantity quantity = new Quantity(0);

    /**
     * Корзина
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    private BuyerCart buyerCart;

    /**
     * Итоговая цена
     */
    @Column(name = "total_price", nullable = false)
    private Money totalPrice = new Money(0);

    protected SelectItemInCart() {
    }

    protected SelectItemInCart(SelectItem selectItem) {
        Objects.requireNonNull(selectItem);
        this.selectItem = selectItem;
        quantity.increment();
        totalPrice = new Money(selectItem.getPrice());
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
    protected Integer getQuantity() {
        return quantity.value();
    }

    /**
     * @return Итоговая цена
     */
    protected Float getTotalPrice() {
        recalculateTotalPrice();
        return totalPrice.value();
    }

    /**
     * Получить товар
     *
     * @return товар
     */
    protected SelectItem getItem() {
        return selectItem;
    }

    /**
     * Сделать перерасчет итоговый цены по данной позиции
     */
    protected SelectItemInCart recalculateTotalPrice() {
        totalPrice = new Money(selectItem.getPrice());
        totalPrice.multiply(quantity.value());
        return this;
    }
}
