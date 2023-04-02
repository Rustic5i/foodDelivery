package ru.rbaratov.fooddelivery.menu.manager.context.cqrs.commands;

import org.springframework.lang.NonNull;
import ru.rbaratov.fooddelivery.common.cqrs.command.Command;

import java.util.UUID;

/**
 * Команда удаления товара из меню
 */
public class RemoveItemFromMenuCommand implements Command {

    /**
     * id товара
     */
    @NonNull
    private UUID itemId;

    /**
     * Имя меню
     */
    @NonNull
    private String menuName;

    public RemoveItemFromMenuCommand(UUID itemId, String menuName) {
        this.itemId = itemId;
        this.menuName = menuName;
    }

    @Override
    public boolean asynchronous() {
        return false;
    }

    public UUID getItemId() {
        return itemId;
    }

    public String getMenuName() {
        return menuName;
    }
}
