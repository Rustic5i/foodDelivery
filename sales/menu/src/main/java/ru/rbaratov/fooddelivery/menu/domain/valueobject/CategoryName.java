package ru.rbaratov.fooddelivery.menu.domain.valueobject;

import jakarta.persistence.Embeddable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;

import java.text.MessageFormat;

/**
 * Названия категории товаров
 */
@Embeddable
public class CategoryName implements ValueObject<String> {

    /**
     * Максимальная длина названия категории
     */
    private static final short MAX_LENGHT = 100;

    /**
     * Названия категории
     */
    private String name;

    protected CategoryName() {
    }

    public CategoryName(@NonNull final String name) {
        if (StringUtils.isBlank(name)) {
            throw new RuntimeException("Названия категории товаров не может быть пустым");
        }
        if (name.length() > MAX_LENGHT) {
            throw new RuntimeException(MessageFormat.format("Длина для названия категории товаров не может быть больше {}", MAX_LENGHT));
        }
        this.name = name;
    }

    public String value() {
        return name;
    }
}
