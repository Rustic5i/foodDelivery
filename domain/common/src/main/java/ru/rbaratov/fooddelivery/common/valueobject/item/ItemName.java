package ru.rbaratov.fooddelivery.common.valueobject.item;

import jakarta.persistence.Embeddable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import ru.rbaratov.fooddelivery.common.valueobject.ValueObject;

import java.text.MessageFormat;
import java.util.Objects;

/**
 * Имя товара
 */
@Embeddable
public final class ItemName implements ValueObject<String> {
    /**
     * Максимальная длина описания товара
     */
    private static final short MAX_LENGHT = 100;

    /**
     * Имя товара
     */
    private String name;

    public ItemName(@NonNull final String name) {
        if (StringUtils.isBlank(name)) {
            throw new RuntimeException("Имя товара не может быть пустым");
        }
        if (name.length() > MAX_LENGHT) {
            throw new RuntimeException(MessageFormat.format("Длина имени товара не может быть больше {}", MAX_LENGHT));
        }
        this.name = name;
    }

    protected ItemName() {
    }

    public String value() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemName itemName = (ItemName) o;
        return Objects.equals(name, itemName.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
