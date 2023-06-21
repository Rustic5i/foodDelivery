package ru.rbaratov.fooddelivery.menu.manager.context.domain;

import java.util.ArrayList;
import java.util.Collection;

public class Category {

    /**
     * Имя категории
     */
    private String name;

    /**
     * Список продуктов
     */
    private Collection<Item> items = new ArrayList<>();

    public Category(String name) {
        if (name == null) {
            throw new RuntimeException("Названия категории товара не было указано");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
