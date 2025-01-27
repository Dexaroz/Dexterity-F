package software.dexterity.arquitecture.model.support;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaxID {
    private final String taxId;
    private static final String TAXID_REGEX = "^[0-9]{9}$"; // Example regex for a 9-digit tax ID

    private TaxID(String taxId) {
        this.taxId = taxId;
    }

    public static TaxID of(String taxId) {
        if (validateTaxID(taxId)) {
            return new TaxID(taxId);
        } else {
            return null;
        }
    }

    private static boolean validateTaxID(String taxId) {
        Pattern pattern = Pattern.compile(TAXID_REGEX);
        Matcher matcher = pattern.matcher(taxId);
        return matcher.matches();
    }

    public String getTaxId() {
        return taxId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        TaxID taxID1 = (TaxID) object;
        return Objects.equals(taxId, taxID1.taxId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(taxId);
    }
}
