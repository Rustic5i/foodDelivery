package ru.rbaratov.fooddelivery.menu.manager.context.cqrs.commands;

import org.springframework.lang.NonNull;
import ru.rbaratov.fooddelivery.common.cqrs.command.Command;

/**
 * Команда. Создать новое меню
 */
public class CreateNewMenuCommand implements Command {

    /**
     * Названия для нового меню
     */
    private String menuName;

    public CreateNewMenuCommand(@NonNull String menuName) {
        this.menuName = menuName;
    }

    @Override
    public boolean asynchronous() {
        return false;
    }

    public String getMenuName() {
        return menuName;
    }
}
