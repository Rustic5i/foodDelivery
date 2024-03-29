package ru.rbaratov.fooddelivery.orders.context.dto;

import java.util.UUID;

public class ItemDTO {

    private UUID id;

    /**
     * Имя товара
     */
    private String name;

    /**
     * Цена
     */
    private Float price;

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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
