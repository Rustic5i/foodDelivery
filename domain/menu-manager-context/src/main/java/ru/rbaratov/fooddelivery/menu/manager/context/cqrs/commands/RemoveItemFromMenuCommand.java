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

    public RemoveItemFromMenuCommand(UUID itemId) {
        this.itemId = itemId;
    }

    @Override
    public boolean asynchronous() {
        return false;
    }

    public UUID getItemId() {
        return itemId;
    }
}
