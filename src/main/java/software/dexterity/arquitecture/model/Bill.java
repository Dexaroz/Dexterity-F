package software.dexterity.arquitecture.model;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Bill {
    private final int id;
    private final Client client;
    private final List<BillItem> items;
    private final Instant date;
    private final double taxRate;
    private final double subTotal;
    private final double total;

    private static final AtomicInteger ID_CLIENT = new AtomicInteger(1);

    public Bill(Client client, List<BillItem> items, double taxRate) {
        this.id = ID_CLIENT.getAndIncrement();
        this.client = client;
        this.items = List.copyOf(items);
        this.date = Instant.now();
        this.taxRate = taxRate;
        this.subTotal = calculateSubtotal();
        this.total = calculateTotal();
    }

    public Bill(Client client, List<BillItem> items, Instant date, double taxRate, double subTotal, double total){
        this.id = ID_CLIENT.getAndIncrement();
        this.client = client;
        this.items = List.copyOf(items);
        this.date = date;
        this.taxRate = taxRate;
        this.subTotal = subTotal;
        this.total = total;
    }

    public Bill editBill(Client client, List<BillItem> items, double taxRate){
        return new Bill(client, items, taxRate);
    }

    private double calculateSubtotal() {
        return items.stream().mapToDouble(BillItem::getTotalPrice).sum();
    }

    private double calculateTotal() {
        return subTotal + (subTotal * taxRate);
    }

    public int getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public List<BillItem> getItems() {
        return items;
    }

    public Instant getDate() {
        return date;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Bill bill = (Bill) object;
        return id == bill.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
