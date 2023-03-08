package ru.rbaratov.fooddelivery.menu.domain.valueobject;

import jakarta.persistence.Embeddable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;

import java.text.MessageFormat;

/**
 * Описание товара
 */
@Embeddable
public final class ProductDescription implements ValueObject<String> {

    /**
     * Максимальная длина описания товара
     */
    private static final short MAX_LENGHT = 125;

    /**
     * Описание
     */
    private String description;

    protected ProductDescription() {

    }

    public ProductDescription(@NonNull final String description) {
        if (StringUtils.isBlank(description)) {
            throw new RuntimeException("Описание не может быть пустым");
        }
        if (description.length() > MAX_LENGHT) {
            throw new RuntimeException(MessageFormat.format("Длина описания товара не может быть больше {}", MAX_LENGHT));
        }
        this.description = description;
    }

    public String value() {
        return description;
    }
}
