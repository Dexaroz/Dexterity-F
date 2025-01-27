package software.dexterity.arquitecture.model.support;

public class Address {
    private final String country;
    private final String province;
    private final String city;
    private final int postalCode;
    private final String street;
    private final int number;
    private final String suite;

    private static final String DEFAULT_COUNTRY = "España";
    private static final String DEFAULT_PROVINCE = "Las Palmas de Gran Canaria";

    private Address(String country, String province, String city, int postalCode, String street, int number, String suite) {
        this.country = country != null ? country : DEFAULT_COUNTRY;
        this.province = province != null ? province : DEFAULT_PROVINCE;
        this.city = city;
        this.postalCode = postalCode;
        this.street = street;
        this.number = number;
        this.suite = suite;
    }

    public static Address of(String country, String province, String city, int postalCode, String street, int number, String suite) {
        return new Address(country, province, city, postalCode, street, number, suite);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(street);
        if ((Integer) number != null) {
            sb.append(" ").append(number);
        }
        if (suite != null && !suite.isEmpty()) {
            sb.append(", ").append(suite);
        }
        sb.append(", ").append(city);
        sb.append(", ").append(province);
        sb.append(", ").append(postalCode);
        sb.append(", ").append(country);
        return sb.toString();
    }

    public String getCountry() {
        return country;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public String getStreet() {
        return street;
    }

    public int getNumber() {
        return number;
    }

    public String getSuite() {
        return suite;
    }
}
