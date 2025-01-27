package software.dexterity.arquitecture.model.support;

import java.util.Objects;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Email {
    private final String email;
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    private Email(String email){
        this.email = email;
    }

    public static Email of(String email){
        if (validateEmail(email)){
            return new Email(email);
        } else {
            return null;
        }
    }

    private static boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Email email1 = (Email) object;
        return Objects.equals(email, email1.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }
}
