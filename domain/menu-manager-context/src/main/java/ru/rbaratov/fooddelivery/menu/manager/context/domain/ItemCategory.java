package ru.rbaratov.fooddelivery.menu.manager.context.domain;

import ru.rbaratov.fooddelivery.common.domain.AbstractDomain;
import ru.rbaratov.fooddelivery.common.valueobject.item.CategoryName;

import java.util.ArrayList;
import java.util.Collection;

public class ItemCategory extends AbstractDomain {
    /**
     * Имя категории
     */
    private CategoryName name;

    /**
     * Список продуктов
     */
    private Collection<Item> items = new ArrayList<>();

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
