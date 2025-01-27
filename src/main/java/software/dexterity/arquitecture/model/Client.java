package software.dexterity.arquitecture.model;

import software.dexterity.arquitecture.model.support.Address;
import software.dexterity.arquitecture.model.support.Email;
import software.dexterity.arquitecture.model.support.PhoneNumber;
import software.dexterity.arquitecture.model.support.TaxID;

import java.util.concurrent.atomic.AtomicInteger;

public record Client(int id, String name, Email email, PhoneNumber phoneNumber, Address address, TaxID taxID) {
    private static final AtomicInteger ID_CLIENT = new AtomicInteger(1);

    public Client(String name, String email, String phoneNumber, String country, String province, String city, int postalCode, String street, int number, String suite , String taxID){
        this(ID_CLIENT.getAndIncrement(), name, Email.of(email), PhoneNumber.of(phoneNumber), Address.of(country, province, city, postalCode, street, number, suite), TaxID.of(taxID));
    }

    public Client editClient(String name, String email, String phoneNumber, String country, String province, String city, int postalCode, String street, int number, String suite , String taxID){
        return new Client(this.id, name, Email.of(email), PhoneNumber.of(phoneNumber), Address.of(country, province, city, postalCode, street, number, suite), TaxID.of(taxID));
    }
}
