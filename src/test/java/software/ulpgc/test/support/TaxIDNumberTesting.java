package software.ulpgc.test.support;

import org.junit.jupiter.api.Test;
import software.dexterity.arquitecture.model.support.PhoneNumber;
import software.dexterity.arquitecture.model.support.TaxID;

import static org.junit.jupiter.api.Assertions.*;

public class TaxIDNumberTesting {

    @Test
    void shouldCreateValidTaxID() {
        TaxID taxID = TaxID.of("123456789");

        assertNotNull(taxID);
        assertEquals("123456789", taxID.getTaxId());
    }

    @Test
    void shouldReturnNullForInvalidTaxID() {
        TaxID taxID = TaxID.of("invalid-tax-id");

        assertNull(taxID);
    }

    @Test
    void shouldReturnNullForNullTaxID() {
        assertThrows(NullPointerException.class, () -> TaxID.of(null));
    }
}
