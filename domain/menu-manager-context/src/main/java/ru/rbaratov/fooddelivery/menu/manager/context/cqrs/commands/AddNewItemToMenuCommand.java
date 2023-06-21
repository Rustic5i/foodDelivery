package ru.rbaratov.fooddelivery.menu.manager.context.cqrs.commands;

import ru.rbaratov.fooddelivery.common.cqrs.command.Command;
import ru.rbaratov.fooddelivery.common.valueobject.SizeUnit;

/**
 * Команда. Добавить новый товар в меню
 */
public class AddNewItemToMenuCommand implements Command {

    /**
     * Имя товара
     */
    private String itemName;

    /**
     * Цена
     */
    private Float price;

    /**
     * Описание товара
     */
    private String itemDescription;

    /**
     * Размер, вес, обьем и тд
     */
    private Integer itemSize;

    /**
     * Единица измерения
     */
    private SizeUnit sizeUnit;

    /**
     * Является ли составным товаром или набором
     */
    private boolean isSet;

    /**
     * Категория товара
     */
    private String itemCategory;

    public AddNewItemToMenuCommand() {
    }

    @Override
    public boolean asynchronous() {
        return false;
    }

    public String getItemName() {
        return itemName;
    }

    public Float getPrice() {
        return price;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public Integer getItemSize() {
        return itemSize;
    }

    public SizeUnit getSizeUnit() {
        return sizeUnit;
    }

    public boolean isSet() {
        return isSet;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public void setItemSize(Integer itemSize) {
        this.itemSize = itemSize;
    }

    public void setSizeUnit(SizeUnit sizeUnit) {
        this.sizeUnit = sizeUnit;
    }

    public void setSet(boolean set) {
        isSet = set;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }
}
