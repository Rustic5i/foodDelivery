package ru.rbaratov.fooddelivery.orders.context.domain.valueobject;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.rbaratov.fooddelivery.common.exception.RuntimeApplicationException;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmailTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "rus@mail.ru", "rus12r@yan.com", "RuS3ii@gmail.com", "RUS@mail.ru"
    })
    @DisplayName("Должен успешно провалидировать email")
    void shouldParsePhoneNumbersSuccessfully(String input) {
        final Email email = assertDoesNotThrow(
                () -> new Email(input)
        );
        assertNotNull(email.value());
        assertTrue(StringUtils.isNotBlank(email.value()));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "rus@mail", "rus&mail.ru", "", "rus@mail.12"
    })
    @DisplayName("Должно вызвать исключение, если email недействителен")
    void shouldThrowExceptionIfEmailIsNotValid(String email) {
        assertThrows(
                RuntimeApplicationException.class,
                () -> new Email(email)
        );
    }

    @DisplayName("Должно быть все равны")
    @Test
    void shouldIsEquals() {
        Set<Email> emailSet = new HashSet<>();
        emailSet.add(new Email("RUS@mail.ru"));
        emailSet.add(new Email("rus@mail.ru"));
        emailSet.add(new Email("RUS@MAIL.RU"));
        emailSet.add(new Email("rus@mail.RU"));
        emailSet.add(new Email("Rus@mail.ru"));

        assertEquals(emailSet.size(), 1);
    }

    @DisplayName("Не должны быть равны")
    @Test
    void shouldIsNotEquals() {
        Set<Email> emailSet = new HashSet<>();
        emailSet.add(new Email("RUS@mail.com"));
        emailSet.add(new Email("rus@mail.ru"));
        emailSet.add(new Email("egor@mail.ru"));

        assertEquals(emailSet.size(), 3);
    }
}