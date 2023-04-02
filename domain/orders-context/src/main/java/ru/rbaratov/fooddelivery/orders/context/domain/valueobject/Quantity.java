package ru.rbaratov.fooddelivery.orders.context.domain.valueobject;

import jakarta.persistence.Embeddable;
import org.springframework.lang.NonNull;
import ru.rbaratov.fooddelivery.common.valueobject.ValueObject;

import java.util.Objects;

/**
 * Количество
 */
@Embeddable
public final class Quantity implements ValueObject<Integer> {

    /**
     * Количество
     */
    private Integer quantity = 0;

    protected Quantity() {
    }

    public Quantity(@NonNull Integer quantity) {
        Objects.requireNonNull(quantity, "Не задано количества");
        this.quantity = quantity;
    }

    /**
     * Прибавить +1
     *
     * @return новое значение
     */
    public Integer increment() {
        quantity++;
        return quantity;
    }

    /**
     * Уменьшить на 1
     *
     * @return новое значение
     */
    public Integer decrement() {
        if (quantity < 1) {
            throw new RuntimeException("Количество не может быть отрицательным");
        }
        quantity--;
        return quantity;
    }

    @Override
    public Integer value() {
        return quantity;
    }
}