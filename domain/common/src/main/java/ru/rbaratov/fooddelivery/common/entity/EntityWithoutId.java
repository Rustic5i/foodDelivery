package ru.rbaratov.fooddelivery.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;

/**
 * Абстрактная сущность без идентификатора
 */
@MappedSuperclass
public abstract class EntityWithoutId {

    /**
     * Сообщение об ошибке
     */
    @Column(name = "error_message")
    private String errorMessage;

    /**
     * Код ошибки
     */
    @Column(name = "error_code")
    private String errorCode;

    /**
     * Дата создания
     */
    @Column(name = "created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created = new Date();

    /**
     * Дата обновления
     */
    @Column(name = "modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modified;
}
