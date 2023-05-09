package ru.rbaratov.fooddelivery.menu.manager.context.cqrs.commands;

import ru.rbaratov.fooddelivery.common.cqrs.command.Command;

/**
 * Команда, добавить новую категорию товаров в меню
 */
public class AddNewCategoryToMenuCommand implements Command {

    /**
     * Название новой категории
     */
    private String categoryName;

    /**
     * Названия меню
     */
    private String menuName;

    AddNewCategoryToMenuCommand() {
    }

    public AddNewCategoryToMenuCommand(String categoryName, String menuName) {
        this.categoryName = categoryName;
        this.menuName = menuName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getMenuName() {
        return menuName;
    }

    @Override
    public boolean asynchronous() {
        return false;
    }
}
