package ru.rbaratov.fooddelivery.orders.context.domain;

import ru.rbaratov.fooddelivery.common.domain.AbstractDomain;
import ru.rbaratov.fooddelivery.orders.context.domain.valueobject.Email;
import ru.rbaratov.fooddelivery.orders.context.domain.valueobject.PhoneNumber;

import java.util.Date;

/**
 * Покупатель
 *
 * @author rbaratov
 */
public class Buyer extends AbstractDomain {

    /**
     * Номер телефона
     */
    private PhoneNumber phoneNumber;

    /**
     * Имя пользователя
     */
    private String firstName;

    /**
     * Активный пользователь или заблокированный
     */
    private boolean isActive = true;

    public Buyer(PhoneNumber phoneNumber, String firstName, boolean isActive) {
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.isActive = isActive;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isActive() {
        return isActive;
    }
}
