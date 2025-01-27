package software.dexterity.arquitecture.model.support;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumber {
    private final String phoneNumber;
    private static final String PHONENUMBER_REGEX = "^\\+?[0-9]{1,4}?[-.\\\\s]?(\\(?[0-9]{1,3}?\\)?[-.\\\\s]?)?[0-9]{3,4}[-.\\\\s]?[0-9]{3,4}$";

    private PhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public static PhoneNumber of(String phoneNumber){
        if (validatePhoneNumber(phoneNumber)){
            return new PhoneNumber(phoneNumber);
        } else {
            return null;
        }
    }

    private static boolean validatePhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile(PHONENUMBER_REGEX);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        PhoneNumber that = (PhoneNumber) object;
        return Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(phoneNumber);
    }
}
