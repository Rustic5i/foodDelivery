package ru.rbaratov.fooddelivery.menu.manager.context.cqrs.query;

import java.util.UUID;

public class GetMenuByIdQuery {

    private UUID id;

    public GetMenuByIdQuery(UUID id) {
        this.id = id;
    }

    public UUID getMenuId() {
        return id;
    }
}
