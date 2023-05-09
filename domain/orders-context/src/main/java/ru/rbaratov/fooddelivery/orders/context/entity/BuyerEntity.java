package ru.rbaratov.fooddelivery.orders.context.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import ru.rbaratov.fooddelivery.common.entity.AbstractEntity;

import java.util.Date;

@Entity(name = "buyers")
public class BuyerEntity extends AbstractEntity {

    /**
     * Номер телефона
     */
    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

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
    @Column(name = "email", unique = true)
    private String email;

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
