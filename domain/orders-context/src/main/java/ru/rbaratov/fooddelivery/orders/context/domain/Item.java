package ru.rbaratov.fooddelivery.orders.context.domain;

import ru.rbaratov.fooddelivery.common.domain.AbstractDomain;
import ru.rbaratov.fooddelivery.common.valueobject.Money;
import ru.rbaratov.fooddelivery.common.valueobject.item.ItemName;

/**
 * Выбранный товар.
 * В данном контексте, данные о товаре нельзя изменить, и нельзя создать новый товар.
 * По этому данную сущность помечена как @Immutable
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

    public Item(ItemName name, Money price) {
        this.name = name;
        this.price = price;
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
}
