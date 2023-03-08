package ru.rbaratov.fooddelivery.menu.domain.valueobject;

import jakarta.persistence.Embeddable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;

import java.text.MessageFormat;

/**
 * Имя товара
 */
@Embeddable
public final class ProductName implements ValueObject<String> {
    /**
     * Максимальная длина описания товара
     */
    private static final short MAX_LENGHT = 100;

    /**
     * Имя товара
     */
    private String name;

    public ProductName(@NonNull final String name) {
        if (StringUtils.isBlank(name)) {
            throw new RuntimeException("Имя товара не может быть пустым");
        }
        if (name.length() > MAX_LENGHT) {
            throw new RuntimeException(MessageFormat.format("Длина имени товара не может быть больше {}", MAX_LENGHT));
        }
        this.name = name;
    }

    protected ProductName() {
    }

    public String value() {
        return name;
    }
}
