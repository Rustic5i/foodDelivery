package ru.rbaratov.fooddelivery.common.domain;

import java.util.Date;
import java.util.UUID;

public abstract class AbstractDomain {

    private UUID id;

    /**
     * Сообщение об ошибке
     */
    private String errorMessage;

    /**
     * Код ошибки
     */
    private String errorCode;

    /**
     * Дата создания
     */
    private Date created = new Date();

    /**
     * Дата обновления
     */
    private Date modified;

    public UUID getId() {
        return id;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public Date getCreated() {
        return created;
    }

    public Date getModified() {
        return modified;
    }
}
