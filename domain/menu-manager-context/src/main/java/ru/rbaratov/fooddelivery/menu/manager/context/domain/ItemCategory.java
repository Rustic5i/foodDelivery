package ru.rbaratov.fooddelivery.menu.manager.context.domain;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import ru.rbaratov.fooddelivery.common.entity.AbstractEntity;
import ru.rbaratov.fooddelivery.common.valueobject.item.CategoryName;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Категория товара
 */
@Entity
@Table(name = "item_categories")
public final class ItemCategory extends AbstractEntity {

    /**
     * Имя категории
     */
    @AttributeOverride(name = "name", column = @Column(name = "name", nullable = false, unique = true))
    private CategoryName name;

    /**
     * Список продуктов
     */
    @OneToMany(mappedBy = "category")
    private Collection<Item> items = new ArrayList<>();

    protected ItemCategory() {

    }

    public ItemCategory(CategoryName name) {
        if (name == null) {
            throw new RuntimeException("Названия категории товара не было указано");
        }
        this.name = name;
    }

    public CategoryName nameCategory() {
        return name;
    }
}
