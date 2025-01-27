package software.ulpgc.test.support;

import org.junit.jupiter.api.Test;
import software.dexterity.arquitecture.model.support.PhoneNumber;

import static org.junit.jupiter.api.Assertions.*;

public class PhoneNumberTesting {

    @Test
    void shouldCreateValidPhoneNumber() {
        PhoneNumber phoneNumber = PhoneNumber.of("+1234567890");

        assertNotNull(phoneNumber);
        assertEquals("+1234567890", phoneNumber.getPhoneNumber());
    }

    @Test
    void shouldReturnNullForInvalidPhoneNumber() {
        PhoneNumber phoneNumber = PhoneNumber.of("123-abc");

        assertNull(phoneNumber);
    }

    @Test
    void shouldReturnNullForNullPhoneNumber() {
        assertThrows(NullPointerException.class, () -> PhoneNumber.of(null));
    }
}
