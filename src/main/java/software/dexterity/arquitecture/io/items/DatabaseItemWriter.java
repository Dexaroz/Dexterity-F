package software.dexterity.arquitecture.io.items;

import software.dexterity.arquitecture.model.Item;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseItemWriter implements AutoCloseable {
    private final Connection connection;

    public DatabaseItemWriter(String dbPath) throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
        this.connection.setAutoCommit(false);
        createTableIfNotExists();
    }

    private void createTableIfNotExists() throws SQLException {
        String createTableSQL = """
                CREATE TABLE IF NOT EXISTS Items (
                    id INTEGER PRIMARY KEY,
                    name TEXT NOT NULL,
                    description TEXT,
                    price_per_unit REAL NOT NULL
                )
            """;
        connection.createStatement().execute(createTableSQL);
    }

    public void write(Item item) throws SQLException {
        String insertSQL = """
                INSERT INTO Items (id, name, description, price_per_unit)
                VALUES (?, ?, ?, ?)
            """;

        try (PreparedStatement statement = connection.prepareStatement(insertSQL)) {
            statement.setInt(1, item.id());
            statement.setString(2, item.name());
            statement.setString(3, item.description());
            statement.setDouble(4, item.pricePerUnit());

            statement.executeUpdate();
        }
    }

    @Override
    public void close() throws SQLException {
        connection.commit();
        connection.close();
    }
}