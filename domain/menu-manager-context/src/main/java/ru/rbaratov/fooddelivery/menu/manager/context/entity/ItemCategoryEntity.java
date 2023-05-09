package ru.rbaratov.fooddelivery.menu.manager.context.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import ru.rbaratov.fooddelivery.common.entity.AbstractEntity;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Категория товара
 */
@Entity
@Table(name = "item_categories")
public final class ItemCategoryEntity extends AbstractEntity {

    /**
     * Имя категории
     */
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    /**
     * Список продуктов
     */
    @OneToMany(mappedBy = "category")
    private Collection<ItemEntity> items = new ArrayList<>();

    public ItemCategoryEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<ItemEntity> getItems() {
        return items;
    }

    public void setItems(Collection<ItemEntity> items) {
        this.items = items;
    }
}
