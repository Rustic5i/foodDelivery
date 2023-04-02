package ru.rbaratov.fooddelivery.orders.context.domain;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.annotations.Immutable;
import ru.rbaratov.fooddelivery.common.entity.AbstractEntity;
import ru.rbaratov.fooddelivery.common.valueobject.Money;
import ru.rbaratov.fooddelivery.common.valueobject.item.ItemDescription;
import ru.rbaratov.fooddelivery.common.valueobject.item.ItemName;
import ru.rbaratov.fooddelivery.common.valueobject.item.ItemSize;

/**
 * Выбранный товар.
 * В данном контексте, данные о товаре нельзя изменить, и нельзя создать новый товар.
 * По этому данную сущность помечена как @Immutable
 */
@Entity
@Table(name = "items")
@Immutable
public final class SelectItem extends AbstractEntity {

    /**
     * Имя товара
     */
    @AttributeOverride(name = "name", column = @Column(name = "name", nullable = false, unique = true))
    private ItemName name;

    /**
     * Цена
     */
    @AttributeOverride(name = "money", column = @Column(name = "price", nullable = false))
    private Money price;

    /**
     * Старая цена
     */
    @AttributeOverride(name = "money", column = @Column(name = "old_price"))
    private Money oldPrice;

    /**
     * Является ли товар новым
     */
    @Column(name = "is_new_item", nullable = false)
    private boolean isNewItem = false;

    /**
     * Описание
     */
    @AttributeOverride(name = "description", column = @Column(name = "description", nullable = false))
    private ItemDescription description;

    /**
     * Размер, вес, обьем и тд
     */
    @AttributeOverrides({
            @AttributeOverride(name = "size", column = @Column(name = "size", nullable = false)),
            @AttributeOverride(name = "sizeUnit", column = @Column(name = "size_unit", nullable = false))
    })
    private ItemSize size;

    /**
     * Является ли составным товаром или набором
     */
    @Column(name = "is_set", nullable = false)
    private boolean isSet = false;

    protected SelectItem() {
        //Исключительно только для ORM
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
