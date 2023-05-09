package ru.rbaratov.fooddelivery.menu.manager.context.cqrs.commands;

import org.springframework.lang.NonNull;
import ru.rbaratov.fooddelivery.common.cqrs.command.Command;

import java.io.Serializable;

/**
 * Команда. Создать новое меню
 */
public class CreateNewMenuCommand implements Command, Serializable {

    /**
     * Названия для нового меню
     */
    private String menuName;

    CreateNewMenuCommand() {
    }

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

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
}
