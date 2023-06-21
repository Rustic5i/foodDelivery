package ru.rbaratov.fooddelivery.orders.context.domain;

import ru.rbaratov.fooddelivery.common.valueobject.Money;
import ru.rbaratov.fooddelivery.common.valueobject.item.ItemName;

import java.util.UUID;

/**
 * Выбранный товар.
 * В данном контексте, данные о товаре нельзя изменить после создания.
 */
public class Item extends AbstractDomain {

    /**
     * Имя товара
     */
    private ItemName name;

    /**
     * Цена
     */
    private Money price;

    private Item() {
    }

    /**
     * Получить цену товара
     *
     * @return цена товара
     */
    public Float getPrice() {
        return price.value();
    }

    /**
     * Получить имя товара
     *
     * @return имя товара
     */
    public ItemName getName() {
        return name;
    }

    public static IdBuilder startRevival() {
        return new Builder();
    }

    public interface IdBuilder {
        NameBuilder requiredId(UUID id);
    }

    public interface NameBuilder {
        PriceBuilder requiredName(ItemName name);
    }

    public interface PriceBuilder {
        FinalBuilder requiredPrice(Money price);
    }

    public interface FinalBuilder {
        Item revive();
    }

    private static class Builder implements IdBuilder, NameBuilder, PriceBuilder, FinalBuilder {

        private Item item = new Item();

        @Override
        public PriceBuilder requiredName(ItemName name) {
            item.name = name;
            return this;
        }

        @Override
        public FinalBuilder requiredPrice(Money price) {
            item.price = price;
            return this;
        }

        @Override
        public NameBuilder requiredId(UUID id) {
            item.id = id;
            return this;
        }

        @Override
        public Item revive() {
            return item;
        }
    }
}
