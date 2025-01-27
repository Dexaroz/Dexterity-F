package software.dexterity.arquitecture.model;

import java.util.Objects;

public class BillItem {
    private final Item item;
    private final int quantity;
    private final double totalPrice;

    public BillItem(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
        this.totalPrice = calculateTotalPrice();
    }

    public BillItem editBillItem(int quantity){
        return new BillItem(item, quantity);
    }

    private double calculateTotalPrice(){
        return quantity * item.pricePerUnit();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        BillItem billItem = (BillItem) object;
        return billItem.item.id() == this.item.id();
    }

    @Override
    public int hashCode() {
        return Objects.hash(item.id());
    }

    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
