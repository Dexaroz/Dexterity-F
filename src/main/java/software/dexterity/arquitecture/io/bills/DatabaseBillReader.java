package software.dexterity.arquitecture.io.bills;

import software.dexterity.arquitecture.model.Bill;
import software.dexterity.arquitecture.model.BillItem;
import software.dexterity.arquitecture.model.Client;
import software.dexterity.arquitecture.model.Item;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseBillReader implements BillReader {
    private final Connection connection;

    public DatabaseBillReader(String dbPath) throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
    }

    @Override
    public Bill read() throws IOException {
        throw new UnsupportedOperationException("Use readAll() to load all bills.");
    }

    public List<Bill> readAll() throws SQLException {
        List<Bill> bills = new ArrayList<>();
        String query = "SELECT * FROM Bills";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                bills.add(deserializeBill(resultSet));
            }
        }
        return bills;
    }

    private Bill deserializeBill(ResultSet resultSet) throws SQLException {
        Client client = new Client(resultSet.getInt("clientId"), "Placeholder", null, null, null, null);
        List<BillItem> items = deserializeItems(resultSet.getString("items"));
        double taxRate = resultSet.getDouble("taxRate");
        double subtotal = resultSet.getDouble("subtotal");
        double total = resultSet.getDouble("total");
        Timestamp date = resultSet.getTimestamp("date");

        return new Bill(client, items, date.toInstant(), taxRate, subtotal, total);
    }

    private List<BillItem> deserializeItems(String items) {
        List<BillItem> billItems = new ArrayList<>();
        String[] itemEntries = items.split(";");
        for (String entry : itemEntries) {
            String[] parts = entry.split(":");
            Item item = new Item(parts[0], "Placeholder Description", 0.0); // Crea un item genérico.
            int quantity = Integer.parseInt(parts[1]);
            billItems.add(new BillItem(item, quantity));
        }
        return billItems;
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
