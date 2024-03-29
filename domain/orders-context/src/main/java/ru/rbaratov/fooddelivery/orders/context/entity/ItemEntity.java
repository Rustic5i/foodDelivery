package ru.rbaratov.fooddelivery.orders.context.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.hibernate.annotations.Immutable;
import ru.rbaratov.fooddelivery.common.entity.AbstractEntity;
import ru.rbaratov.fooddelivery.common.valueobject.SizeUnit;

import java.util.ArrayList;
import java.util.List;

/**
 * Выбранный товар.
 * В данном контексте, данные о товаре нельзя изменить, и нельзя создать новый товар.
 * По этому данную сущность помечена как @Immutable
 */
@Entity
@Table(name = "items")
@Immutable
public class ItemEntity extends AbstractEntity {

    /**
     * Имя товара
     */
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    /**
     * Цена
     */
    @Column(name = "price", nullable = false)
    private Float price;

    /**
     * Старая цена
     */
    @Column(name = "old_price")
    private Float oldPrice;

    /**
     * Является ли товар новым
     */
    @Column(name = "is_new_item", nullable = false)
    private boolean isNewItem = false;

    /**
     * Описание
     */
    @Column(name = "description", nullable = false)
    private String description;

    /**
     * Размер, вес, обьем и тд
     */
    @Column(name = "size", nullable = false)
    private Integer size;

    /**
     * Единица измерения
     */
    @Column(name = "size_unit", nullable = false)
    private SizeUnit sizeUnit;

    /**
     * Является ли составным товаром или набором
     */
    @Column(name = "is_set", nullable = false)
    private boolean isSet = false;

    @OneToMany(mappedBy = "item")
    private List<CartItemEntity> cartItems = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(Float oldPrice) {
        this.oldPrice = oldPrice;
    }

    public boolean isNewItem() {
        return isNewItem;
    }

    public void setNewItem(boolean newItem) {
        isNewItem = newItem;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public SizeUnit getSizeUnit() {
        return sizeUnit;
    }

    public void setSizeUnit(SizeUnit sizeUnit) {
        this.sizeUnit = sizeUnit;
    }

    public boolean isSet() {
        return isSet;
    }

    public void setSet(boolean set) {
        isSet = set;
    }
}
