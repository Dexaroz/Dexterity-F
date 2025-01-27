package software.dexterity.arquitecture.io.items;

import software.dexterity.arquitecture.model.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseItemReader implements AutoCloseable {
    private final Connection connection;

    public DatabaseItemReader(String dbPath) throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
    }

    public List<Item> readAll() throws SQLException {
        List<Item> items = new ArrayList<>();
        String query = "SELECT * FROM Items";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                items.add(deserializeItem(resultSet));
            }
        }

        return items;
    }

    private Item deserializeItem(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        double pricePerUnit = resultSet.getDouble("price_per_unit");

        return new Item(id, name, description, pricePerUnit);
    }

    @Override
    public void close() throws SQLException {
        connection.close();
    }
}
