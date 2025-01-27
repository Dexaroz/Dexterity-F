package software.dexterity.arquitecture.io.clients;

import software.dexterity.arquitecture.model.Client;
import software.dexterity.arquitecture.model.support.Address;
import software.dexterity.arquitecture.model.support.Email;
import software.dexterity.arquitecture.model.support.PhoneNumber;
import software.dexterity.arquitecture.model.support.TaxID;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseClientReader implements AutoCloseable {
    private final Connection connection;

    public DatabaseClientReader(String dbPath) throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
    }

    public List<Client> readAll() throws SQLException {
        List<Client> clients = new ArrayList<>();
        String query = "SELECT * FROM Clients";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                clients.add(deserializeClient(resultSet));
            }
        }

        return clients;
    }

    private Client deserializeClient(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        Email email = Email.of(resultSet.getString("email"));
        PhoneNumber phoneNumber = PhoneNumber.of(resultSet.getString("phone_number"));
        Address address = Address.of(
                resultSet.getString("country"),
                resultSet.getString("province"),
                resultSet.getString("city"),
                resultSet.getInt("postal_code"),
                resultSet.getString("street"),
                resultSet.getInt("number"),
                resultSet.getString("suite")
        );
        TaxID taxID = TaxID.of(resultSet.getString("tax_id"));

        return new Client(name, email, phoneNumber, address, taxID);
    }

    @Override
    public void close() throws SQLException {
        connection.close();
    }
}
