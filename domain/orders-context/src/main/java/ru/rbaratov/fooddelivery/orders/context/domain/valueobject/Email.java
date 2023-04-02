package ru.rbaratov.fooddelivery.orders.context.domain.valueobject;

import jakarta.persistence.Embeddable;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.lang.NonNull;
import ru.rbaratov.fooddelivery.common.exception.RuntimeApplicationException;
import ru.rbaratov.fooddelivery.common.valueobject.ValueObject;

import java.text.MessageFormat;
import java.util.Objects;

/**
 * Электронная почта
 */
@Embeddable
public final class Email implements ValueObject<String> {

    private static final EmailValidator EMAIL_VALIDATOR = EmailValidator.getInstance();

    private String email;

    public Email(@NonNull String email) {
        validation(email);
        this.email = email.toLowerCase();
    }

    protected Email() {
    }

    @Override
    public String value() {
        return email;
    }

    /**
     * Валидация электронной почты
     *
     * @param email электронная почта
     */
    public static void validation(String email) {
        Objects.requireNonNull(email);
        boolean isValid = EMAIL_VALIDATOR.isValid(email);
        if (!isValid) {
            throw new RuntimeApplicationException(MessageFormat.format("Неверный формат электронной почты {0}", email));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email1 = (Email) o;
        if (email1.email == null) return false;
        return email.toLowerCase().equals(email1.email.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(email.toLowerCase());
    }
}
