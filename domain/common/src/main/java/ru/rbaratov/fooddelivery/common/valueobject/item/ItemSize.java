package ru.rbaratov.fooddelivery.common.valueobject.item;

import jakarta.persistence.Embeddable;
import ru.rbaratov.fooddelivery.common.valueobject.SizeUnit;
import ru.rbaratov.fooddelivery.common.valueobject.ValueObject;

import java.text.MessageFormat;

/**
 * Размер товара
 */
@Embeddable
public final class ItemSize implements ValueObject<String> {

    /**
     * Размер
     */
    private Integer size;

    /**
     * Единица измерения
     */
    private SizeUnit sizeUnit;

    protected ItemSize() {

    }

    public ItemSize(int size, SizeUnit sizeUnit) {
        if (size < 0) {
            throw new RuntimeException("Размер товара не может быть меньше нуля");
        }
        this.size = size;
        this.sizeUnit = sizeUnit;
    }

    public String value() {
        return MessageFormat.format("{0} {1}", size, sizeUnit);
    }
}
