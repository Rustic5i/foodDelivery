package ru.rbaratov.fooddelivery.common.valueobject.item;

import jakarta.persistence.Embeddable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import ru.rbaratov.fooddelivery.common.valueobject.ValueObject;

import java.text.MessageFormat;

/**
 * Имя меню
 */
@Embeddable
public class MenuName implements ValueObject<String> {

    /**
     * Максимальная длина названия меню
     */
    private static final short MAX_LENGHT = 100;

    /**
     * Названия меню
     */
    private String name;

    protected MenuName() {
    }

    public MenuName(@NonNull final String name) {
        if (StringUtils.isBlank(name)) {
            throw new RuntimeException("Названия меню не может быть пустым");
        }
        if (name.length() > MAX_LENGHT) {
            throw new RuntimeException(MessageFormat.format("Длина для названия меню не может быть больше {}", MAX_LENGHT));
        }
        this.name = name;
    }

    public String value() {
        return name;
    }
}
