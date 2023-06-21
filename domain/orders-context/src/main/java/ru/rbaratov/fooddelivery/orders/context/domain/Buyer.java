package ru.rbaratov.fooddelivery.orders.context.domain;


import ru.rbaratov.fooddelivery.orders.context.domain.valueobject.Email;
import ru.rbaratov.fooddelivery.orders.context.domain.valueobject.PhoneNumber;

import java.util.Date;
import java.util.UUID;

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

    private Buyer() {
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isActive() {
        return isActive;
    }

    public static IdBuilder startRevival() {
        return new Builder();
    }

    public interface IdBuilder {
        PhoneNumberBuilder requiredId(UUID id);
    }

    public interface PhoneNumberBuilder{
        FirstNameBuilder requiredPhoneNumber(PhoneNumber phoneNumber);
    }

    public interface FirstNameBuilder {
        IsActiveBuilder requiredFirstName(String firstName);
    }

    public interface IsActiveBuilder{
        FinalBuilder requiredIsActive(boolean isActive);
    }

    public interface FinalBuilder {
        Buyer revive();
    }

    private static class Builder implements IdBuilder,
            PhoneNumberBuilder, FirstNameBuilder, IsActiveBuilder, FinalBuilder {

        private Buyer buyer = new Buyer();

        @Override
        public PhoneNumberBuilder requiredId(UUID id) {
            buyer.id = id;
            return this;
        }

        @Override
        public FirstNameBuilder requiredPhoneNumber(PhoneNumber phoneNumber) {
            buyer.phoneNumber = phoneNumber;
            return this;
        }

        @Override
        public IsActiveBuilder requiredFirstName(String firstName) {
            buyer.firstName = firstName;
            return this;
        }

        @Override
        public FinalBuilder requiredIsActive(boolean isActive) {
            buyer.isActive = isActive;
            return this;
        }

        @Override
        public Buyer revive() {
            return buyer;
        }
    }
}
