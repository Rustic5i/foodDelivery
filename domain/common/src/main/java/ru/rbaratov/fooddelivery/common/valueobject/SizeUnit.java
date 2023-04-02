package ru.rbaratov.fooddelivery.common.valueobject;

/**
 * Единица измерения
 *
 * @author Ruslan
 */
public enum SizeUnit implements ValueObject {
    KG("кг"),
    PCS("шт");


    /**
     * Единица измерения на русском
     */
    private final String size;

    SizeUnit(String size) {
        this.size = size;
    }

    @Override
    public Object value() {
        return size;
    }
}
