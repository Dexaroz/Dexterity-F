package software.dexterity.arquitecture.model.managers;

import software.dexterity.arquitecture.model.Bill;
import software.dexterity.arquitecture.model.BillItem;
import software.dexterity.arquitecture.model.Client;

import java.util.ArrayList;
import java.util.List;

public class BillManager {
    private final List<Bill> bills;

    public BillManager() {
        this.bills = new ArrayList<>();

    }

    public void addBill(Client client, List<BillItem> items, double taxRate){
        bills.add(new Bill(client, items, taxRate));
    }

    public void removeBill(Bill bill){
        bills.remove(bill);
    }

    public void editBill(Bill bill, Client client, List<BillItem> items, double taxRate){
        bills.remove(bill);
        bills.add(bill.editBill(client, items, taxRate));
    }

    public List<Bill> getBills(){
        return new ArrayList<>(bills);
    }

    public Bill[] getBillsToArray(){
        return bills.toArray(new Bill[0]);
    }
}
