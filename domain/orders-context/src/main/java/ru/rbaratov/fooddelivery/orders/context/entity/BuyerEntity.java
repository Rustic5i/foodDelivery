package ru.rbaratov.fooddelivery.orders.context.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import ru.rbaratov.fooddelivery.common.entity.AbstractEntity;
import ru.rbaratov.fooddelivery.orders.context.domain.AbstractDomain;

import java.util.Date;

@Entity(name = "buyers")
public class BuyerEntity extends AbstractEntity {

    /**
     * Номер телефона
     */
    @Column(name = "phone_number", nullable = false, unique = true, updatable = false, insertable = false)
    private String phoneNumber;

    /**
     * Имя пользователя
     */
    @Column(name = "first_name", nullable = false, updatable = false, insertable = false)
    private String firstName;

    /**
     * Активный пользователь или заблокированный
     */
    @Column(name = "is_active")
    private boolean isActive = true;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public boolean isActive() {
        return isActive;
    }
}
