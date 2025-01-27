package software.ulpgc.test.support;

import org.junit.jupiter.api.Test;
import software.dexterity.arquitecture.model.support.Email;

import static org.junit.jupiter.api.Assertions.*;

public class EmailTesting {

    @Test
    void shouldCreateValidEmail() {
        Email email = Email.of("test@example.com");

        assertNotNull(email);
        assertEquals("test@example.com", email.getEmail());
    }

    @Test
    void shouldReturnNullForInvalidEmail() {
        Email email = Email.of("invalid-email");

        assertNull(email);
    }

    @Test
    void shouldReturnNullForNullEmail() {
        assertThrows(NullPointerException.class, () -> Email.of(null));
    }
}
