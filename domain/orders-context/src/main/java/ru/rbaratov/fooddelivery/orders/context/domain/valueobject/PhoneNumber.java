package ru.rbaratov.fooddelivery.orders.context.domain.valueobject;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import jakarta.persistence.Embeddable;
import org.springframework.lang.NonNull;
import ru.rbaratov.fooddelivery.common.exception.RuntimeApplicationException;
import ru.rbaratov.fooddelivery.common.valueobject.ValueObject;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Objects;

import static com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat.E164;

@Embeddable
public final class PhoneNumber implements ValueObject<String>, Serializable {

    private static final PhoneNumberUtil PHONE_NUMBER_UTIL = PhoneNumberUtil.getInstance();
    private static final long serialVersionUID = -1243003953561757652L;

    private String phoneNumber;

    protected PhoneNumber() {
    }

    public PhoneNumber(@NonNull String phoneNumber) {
        this.phoneNumber = validateAndNormalizePhoneNumber(phoneNumber);
    }

    @Override
    public String value() {
        return phoneNumber;
    }

    /**
     * Валидация и нормализация номера телефона
     *
     * @param value номер телефона
     * @return нормализованный номер телефона
     */
    private static String validateAndNormalizePhoneNumber(String value) {
        Objects.requireNonNull(value, "Пустое значения номера телефона");
        try {
            final Phonenumber.PhoneNumber phoneNumber = PHONE_NUMBER_UTIL.parse(value, "RU");
            boolean isMobileNumber = PHONE_NUMBER_UTIL.isPossibleNumberForType(phoneNumber, PhoneNumberUtil.PhoneNumberType.MOBILE);
            if (!isMobileNumber) {
                throw new RuntimeApplicationException(MessageFormat.format("Не корректный номер телефона: {0}", value));
            }
            // Формат E164 возвращает номер телефона с символом +
            final String formattedPhoneNumber = PHONE_NUMBER_UTIL.format(phoneNumber, E164);
            return formattedPhoneNumber;
        } catch (NumberParseException e) {
            throw new RuntimeApplicationException(MessageFormat.format("Не корректный номер телефона: {0}", value), e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber that = (PhoneNumber) o;
        return phoneNumber.equals(that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber);
    }
}
