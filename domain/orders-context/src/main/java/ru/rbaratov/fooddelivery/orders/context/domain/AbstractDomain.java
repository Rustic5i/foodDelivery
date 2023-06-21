package ru.rbaratov.fooddelivery.orders.context.domain;

import java.util.UUID;

public abstract class AbstractDomain {

    protected UUID id;

    public UUID getId() {
        return id;
    }
}
