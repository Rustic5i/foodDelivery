package ru.rbaratov.fooddelivery.menu.manager.context.cqrs.commands;

import ru.rbaratov.fooddelivery.common.cqrs.command.Command;

/**
 * Команда, создать новую категорию товаров в меню
 */
public class CreateNewCategoryCommand implements Command {

    /**
     * Название новой категории
     */
    private String categoryName;


    CreateNewCategoryCommand() {
    }

    public CreateNewCategoryCommand(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }


    @Override
    public boolean asynchronous() {
        return false;
    }
}
