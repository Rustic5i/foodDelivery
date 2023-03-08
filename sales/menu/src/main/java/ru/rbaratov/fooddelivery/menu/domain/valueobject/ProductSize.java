package ru.rbaratov.fooddelivery.menu.domain.valueobject;

import jakarta.persistence.Embeddable;

import java.text.MessageFormat;

/**
 * Размер товара
 */
@Embeddable
public final class ProductSize implements ValueObject<String> {

    /**
     * Размер
     */
    private Integer size;

    /**
     * Единица измерения
     */
    private SizeUnit sizeUnit;

    protected ProductSize() {

    }

    public ProductSize(int size, SizeUnit sizeUnit) {
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
