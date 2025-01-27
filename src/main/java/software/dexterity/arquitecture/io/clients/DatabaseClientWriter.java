package software.dexterity.arquitecture.io.clients;

import software.dexterity.arquitecture.model.Client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseClientWriter implements AutoCloseable {
    private final Connection connection;

    public DatabaseClientWriter(String dbPath) throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
        this.connection.setAutoCommit(false);
        createTableIfNotExists();
    }

    private void createTableIfNotExists() throws SQLException {
        String createTableSQL = """
                CREATE TABLE IF NOT EXISTS Clients (
                    id INTEGER PRIMARY KEY,
                    name TEXT NOT NULL,
                    email TEXT NOT NULL,
                    phone_number TEXT,
                    country TEXT,
                    province TEXT,
                    city TEXT,
                    postal_code INTEGER,
                    street TEXT,
                    number INTEGER,
                    suite TEXT,
                    tax_id TEXT NOT NULL
                )
            """;
        connection.createStatement().execute(createTableSQL);
    }

    public void write(Client client) throws SQLException {
        String insertSQL = """
                INSERT INTO Clients (id, name, email, phone_number, country, province, city, postal_code, street, number, suite, tax_id)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;

        try (PreparedStatement statement = connection.prepareStatement(insertSQL)) {
            statement.setInt(1, client.id());
            statement.setString(2, client.name());
            statement.setString(3, client.email().getEmail());
            statement.setString(4, client.phoneNumber().getPhoneNumber());
            statement.setString(5, client.address().getCountry());
            statement.setString(6, client.address().getProvince());
            statement.setString(7, client.address().getCity());
            statement.setInt(8, client.address().getPostalCode());
            statement.setString(9, client.address().getStreet());
            statement.setInt(10, client.address().getNumber());
            statement.setString(11, client.address().getSuite());
            statement.setString(12, client.taxID().getTaxId());

            statement.executeUpdate();
        }
    }

    @Override
    public void close() throws SQLException {
        connection.commit();
        connection.close();
    }
}
