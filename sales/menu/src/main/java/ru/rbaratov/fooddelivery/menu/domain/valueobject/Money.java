package ru.rbaratov.fooddelivery.menu.domain.valueobject;

import jakarta.persistence.Embeddable;

/**
 * Класс Value-Object, Деньги
 */
@Embeddable
public final class Money implements ValueObject<Float> {

    public static final Money ZERO = new Money(0.f);

    private static final float MAX_VALUE = 1_000_000.f;

    private Float money;

    protected Money() {

    }

    public Money(float money) {
        if (money < 0) {
            throw new IllegalArgumentException("Деньги не могут быть меньше нуля.");
        }
        if (money > MAX_VALUE) {
            throw new IllegalArgumentException("Денег не может быть больше чем " + MAX_VALUE + ".");
        }
        this.money = money;
    }

    /**
     * Прибавить
     *
     * @param summand
     * @return сумма денег
     */
    public Money add(Money summand) {
        return new Money(money + summand.value());
    }

    /**
     * Умножить
     *
     * @param factor сколько раз умножить
     * @return сумма денег
     */
    public Money multiply(int factor) {
        return new Money(money * factor);
    }

    /**
     * @return Сумма денег
     */
    public Float value() {
        return money;
    }
}
