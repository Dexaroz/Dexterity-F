package software.ulpgc.test.support;

import org.junit.jupiter.api.Test;
import software.dexterity.arquitecture.model.support.Address;
import software.dexterity.arquitecture.model.support.Email;

import static org.junit.jupiter.api.Assertions.*;

public class AddressTesting {

    @Test
    void shouldCreateValidAddress() {
        Address address = Address.of(
                "España", "Madrid", "Madrid", 28001, "Gran Via", 10, "Suite 1A"
        );

        assertNotNull(address);
        assertEquals("España", address.getCountry());
        assertEquals("Madrid", address.getProvince());
        assertEquals("Madrid", address.getCity());
        assertEquals(28001, address.getPostalCode());
        assertEquals("Gran Via", address.getStreet());
        assertEquals(10, address.getNumber());
        assertEquals("Suite 1A", address.getSuite());
    }

    @Test
    void shouldFallbackToDefaultCountryAndProvince() {
        Address address = Address.of(
                null, null, "Madrid", 28001, "Gran Via", 10, "Suite 1A"
        );

        assertNotNull(address);
        assertEquals("España", address.getCountry());
        assertEquals("Las Palmas de Gran Canaria", address.getProvince());
    }

    @Test
    void shouldAllowNullSuite() {
        Address address = Address.of(
                "España", "Madrid", "Madrid", 28001, "Gran Via", 10, null
        );

        assertNotNull(address);
        assertEquals("España", address.getCountry());
        assertNull(address.getSuite());
    }
}
