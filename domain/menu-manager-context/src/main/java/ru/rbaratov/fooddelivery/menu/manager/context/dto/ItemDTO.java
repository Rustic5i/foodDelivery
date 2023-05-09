package ru.rbaratov.fooddelivery.menu.manager.context.dto;

import ru.rbaratov.fooddelivery.common.valueobject.SizeUnit;
import ru.rbaratov.fooddelivery.menu.manager.context.cqrs.commands.AddNewItemToMenuCommand;

import java.util.UUID;

public class ItemDTO {

    private UUID id;

    /**
     * Имя товара
     */
    private String name;

    /**
     * Категория товара
     */
    private String categoryName;

    /**
     * Описание
     */
    private String description;

    /**
     * Цена
     */
    private Float price;

    /**
     * Единица измерения
     */
    private SizeUnit sizeUnit;

    /**
     * Размер, вес, обьем и тд
     */
    private Integer size;

//    public static ItemDTO fromItem(ItemEntity item) {
//        ItemDTO dto = new ItemDTO();
//        dto.setId(item.getId());
//        dto.setName(item.showName().value());
//        dto.setCategoryName(item.showCategory().nameCategory().value());
//        dto.setSize(item.showSize().getSize());
//        dto.setSizeUnit(item.showSize().getSizeUnit());
//        dto.setDescription(item.showDescription().value());
//        dto.setPrice(item.showPrice());
//        return dto;
//    }

    public static ItemDTO from(AddNewItemToMenuCommand command) {
        ItemDTO dto = new ItemDTO();
        dto.setName(command.getItemName());
        dto.setCategoryName(command.getItemCategory());
        dto.setDescription(command.getItemDescription());
        dto.setPrice(command.getPrice());
        dto.setSize(command.getItemSize());
        dto.setSizeUnit(command.getSizeUnit());
        return dto;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
