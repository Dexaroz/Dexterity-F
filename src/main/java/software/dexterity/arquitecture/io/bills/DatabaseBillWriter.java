package software.dexterity.arquitecture.io.bills;

import software.dexterity.arquitecture.model.Bill;
import software.dexterity.arquitecture.model.BillItem;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DatabaseBillWriter implements BillWriter, AutoCloseable {
    private final Connection connection;

    public DatabaseBillWriter(String dbPath) throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
        this.connection.setAutoCommit(false);
        createTableIfNotExists();
    }

    private void createTableIfNotExists() throws SQLException {
        String createTableSQL = """
                CREATE TABLE IF NOT EXISTS Bills (
                    id INTEGER PRIMARY KEY,
                    clientId INTEGER,
                    items TEXT,
                    taxRate REAL,
                    subtotal REAL,
                    total REAL,
                    date TEXT
                )
            """;
        connection.createStatement().execute(createTableSQL);
    }

    @Override
    public void write(Bill bill) throws IOException {
        String insertSQL = """
                INSERT INTO Bills (id, clientId, items, taxRate, subtotal, total, date)
                VALUES (?, ?, ?, ?, ?, ?, ?)
            """;

        try (PreparedStatement statement = connection.prepareStatement(insertSQL)) {
            statement.setInt(1, bill.getId());
            statement.setInt(2, bill.getClient().id());
            statement.setString(3, serializeItems(bill.getItems()));
            statement.setDouble(4, bill.getTaxRate());
            statement.setDouble(5, bill.getSubTotal());
            statement.setDouble(6, bill.getTotal());
            statement.setString(7, bill.getDate().toString());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IOException(e);
        }
    }

    private String serializeItems(List<BillItem> items) {
        return items.stream()
                .map(item -> item.getItem().name() + ":" + item.getQuantity())
                .reduce((a, b) -> a + ";" + b)
                .orElse("");
    }

    @Override
    public void close() throws Exception {
        connection.commit();
        connection.close();
    }
}
