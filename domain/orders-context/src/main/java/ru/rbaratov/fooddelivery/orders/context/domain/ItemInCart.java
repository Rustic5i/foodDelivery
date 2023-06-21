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
public class ItemInCart extends AbstractEntity {

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

    private ItemInCart(Item item, Quantity quantity) {
        this.item = item;
        this.quantity = quantity;
        recalculateTotalPrice();
    }

    protected ItemInCart(Item item) {
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
    protected ItemInCart recalculateTotalPrice() {
        totalPrice = new Money(item.getPrice());
        totalPrice = totalPrice.multiply(quantity.value());
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (!(o instanceof ItemInCart)) return false;

        ItemInCart that = (ItemInCart) o;
        if (that.getItem() == null || item == null) {
            return false;
        }
        if (that.getItem().getId() == null || item.getId() == null) {
            return false;
        }
        return getItem().getId().equals(that.getItem().getId());
    }

    @Override
    public int hashCode() {
        return item == null || item.getId() == null ? 0 : Objects.hash(item.id);
    }

    public static RequiredValues startRevival() {
        return new Builder();
    }

    public interface RequiredValues {
        FinalBuilder requiredValues(Item item, Quantity quantity, UUID id);
    }


    public interface FinalBuilder {
        ItemInCart revive();
    }

    private static class Builder implements FinalBuilder, RequiredValues {

        private ItemInCart itemInCart;


        @Override
        public FinalBuilder requiredValues(Item item, Quantity quantity, UUID id) {
            itemInCart = new ItemInCart(item, quantity);
            itemInCart.id = id;
            return this;
        }

        @Override
        public ItemInCart revive() {
            return itemInCart;
        }
    }
}
