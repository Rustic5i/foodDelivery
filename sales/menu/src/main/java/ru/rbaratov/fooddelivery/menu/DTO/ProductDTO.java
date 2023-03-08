package ru.rbaratov.fooddelivery.menu.DTO;

import ru.rbaratov.fooddelivery.menu.domain.valueobject.SizeUnit;

public class ProductDTO {
    /**
     * Имя товара
     */
    private String productName;

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

    public  String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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
