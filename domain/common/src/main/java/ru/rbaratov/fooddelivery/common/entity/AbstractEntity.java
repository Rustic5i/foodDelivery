package ru.rbaratov.fooddelivery.common.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.util.UUID;

/**
 * Абстрактная сущность
 */
@MappedSuperclass
public class AbstractEntity extends EntityWithoutId {
    @Id
    @GeneratedValue
    protected UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
