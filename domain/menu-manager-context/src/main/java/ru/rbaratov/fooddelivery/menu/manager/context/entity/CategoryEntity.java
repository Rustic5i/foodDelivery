package ru.rbaratov.fooddelivery.menu.manager.context.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Категория товара
 */
@Entity
@Table(name = "categories")
public final class CategoryEntity {

    /**
     * Имя категории
     */
    @Id
    private String name;

    /**
     * Список продуктов
     */
    @OneToMany(mappedBy = "category")
    private Collection<ItemEntity> items = new ArrayList<>();

    public CategoryEntity() {
    }

    public CategoryEntity(String name) {
        this.name = name;
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
