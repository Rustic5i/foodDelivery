package ru.rbaratov.fooddelivery.domain;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

/**
 * Абстрактная сущность
 */
@MappedSuperclass
public class AbstractEntity {
    @Id
    private UUID id;
}
