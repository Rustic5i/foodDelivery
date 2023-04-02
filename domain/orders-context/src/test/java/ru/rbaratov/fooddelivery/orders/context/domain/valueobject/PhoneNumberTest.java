package ru.rbaratov.fooddelivery.orders.context.domain.valueobject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import ru.rbaratov.fooddelivery.common.exception.RuntimeApplicationException;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PhoneNumberTest {

    @ParameterizedTest
    @CsvSource({
            "+79375554433,+79375554433",
            "89375554433,+79375554433",
            "9375554433,+79375554433",
            "937 555 44 33,+79375554433",
            "937-555-44-33,+79375554433"
    })
    @DisplayName("Должен успешно анализировать телефонные номера")
    void shouldParsePhoneNumbersSuccessfully(String input, String expectedOutput) {
        final PhoneNumber phoneNumber = assertDoesNotThrow(
                () -> new PhoneNumber(input)
        );
        assertEquals(expectedOutput, phoneNumber.value());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "893755544", "-1", "Не номер телефона", "", "+7937355a458"
    })
    @DisplayName("Должно вызвать исключение, если номер телефона недействителен")
    void shouldThrowExceptionIfPhoneNumberIsNotValid(String input) {
        assertThrows(
                RuntimeApplicationException.class,
                () -> new PhoneNumber(input)
        );
    }

    @DisplayName("Должно быть все равны")
    @Test
    void shouldIsEquals() {
        Set<PhoneNumber> phoneNumberSet = new HashSet<>();
        phoneNumberSet.add(new PhoneNumber("+79375554433"));
        phoneNumberSet.add(new PhoneNumber("89375554433"));
        phoneNumberSet.add(new PhoneNumber("9375554433"));
        phoneNumberSet.add(new PhoneNumber("937-555-44-33"));

        assertEquals(phoneNumberSet.size(), 1);
    }

    @DisplayName("Не должны быть равны")
    @Test
    void shouldIsNotEquals() {
        Set<PhoneNumber> phoneNumberSet = new HashSet<>();
        phoneNumberSet.add(new PhoneNumber("+79375554433"));
        phoneNumberSet.add(new PhoneNumber("+79445554433"));
        phoneNumberSet.add(new PhoneNumber("89877776655"));

        assertEquals(phoneNumberSet.size(), 3);
    }
}