package ru.rbaratov.fooddelivery.menu.manager.context.dto;

import ru.rbaratov.fooddelivery.common.valueobject.SizeUnit;

public class ItemDTO {
    /**
     * Имя товара
     */
    private String itemName;

    /**
     * Категория товара
     */
    private String category;

    /**
     * Описание
     */
    private String description;

    /**
     * Единица измерения
     */
    private SizeUnit sizeUnit;

    /**
     * Цена
     */
    private Float price;

    /**
     * Размер, вес, обьем и тд
     */
    private Integer size;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
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
}
