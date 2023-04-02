package ru.rbaratov.fooddelivery.orders.context.domain;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import ru.rbaratov.fooddelivery.common.entity.AbstractEntity;
import ru.rbaratov.fooddelivery.orders.context.domain.valueobject.Email;
import ru.rbaratov.fooddelivery.orders.context.domain.valueobject.PhoneNumber;

import java.util.Date;

/**
 * Покупатель
 *
 * @author rbaratov
 */
@Entity(name = "buyers")
public class Buyer extends AbstractEntity {

    /**
     * Номер телефона
     */
//    @EmbeddedId
    @AttributeOverride(name = "phoneNumber", column = @Column(name = "phone_number", nullable = false, unique = true))
    private PhoneNumber phoneNumber;

    /**
     * Имя пользователя
     */
    @Column(name = "first_name", nullable = false)
    private String firstName;

    /**
     * Фамилия пользователя
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * Отчество
     */
    @Column(name = "patronymic")
    private String patronymic;

    /**
     * Электронная почта
     */
    @AttributeOverride(name = "email", column = @Column(name = "email", unique = true))
    private Email email;

    /**
     * Дата рождения
     */
    @Column(name = "birthday")
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthday;

    /**
     * Активный пользователь или заблокированный
     */
    @Column(name = "is_active")
    private boolean isActive = true;

    protected Buyer() {
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isActive() {
        return isActive;
    }
}
