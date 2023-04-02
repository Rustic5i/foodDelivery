package ru.rbaratov.fooddelivery.menu.manager.context.cqrs.commands;

import ru.rbaratov.fooddelivery.common.cqrs.command.Command;
import ru.rbaratov.fooddelivery.common.valueobject.SizeUnit;

/**
 * Команда. Добавить новый товар в меню
 */
public class AddNewItemToMenuCommand implements Command {

    /**
     * Названия меню
     */
    private String menuName;

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

    @Override
    public boolean asynchronous() {
        return false;
    }

    public String getMenuName() {
        return menuName;
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
}
