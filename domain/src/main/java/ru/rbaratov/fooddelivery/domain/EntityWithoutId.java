package ru.rbaratov.fooddelivery.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * Абстрактная сущность без идентификатора
 */
@MappedSuperclass
@Getter
@Setter
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
    @Column(name = "create", nullable = false)
    private Date create;

    /**
     * Дата обновления
     */
    @Column(name = "modified")
    private Date modified;
}
