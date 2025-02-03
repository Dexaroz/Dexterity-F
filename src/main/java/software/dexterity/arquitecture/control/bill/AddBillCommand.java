package software.dexterity.arquitecture.control.bill;

import software.dexterity.app.swing.billsContent.SwingBillDialog;
import software.dexterity.app.swing.support.SwingDesignInputField;
import software.dexterity.arquitecture.control.Command;
import software.dexterity.arquitecture.model.BillItem;
import software.dexterity.arquitecture.model.Client;
import software.dexterity.arquitecture.model.Item;
import software.dexterity.arquitecture.model.managers.BillManager;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class AddBillCommand implements Command {

    private final BillManager billManager;
    private JComboBox<Client> clientComboBox;
    private JList<Item> itemList;
    private SwingDesignInputField taxRateField;
    private final SwingBillDialog parentComponent;

    public AddBillCommand(BillManager billManager, JComboBox<Client> clientComboBox, JList<Item> itemList, SwingDesignInputField taxRateField, SwingBillDialog parentComponent) {
        this.billManager = billManager;
        this.clientComboBox = clientComboBox;
        this.itemList = itemList;
        this.taxRateField = taxRateField;
        this.parentComponent = parentComponent;
    }

    @Override
    public void execute() {
        Client selectedClient = (Client) clientComboBox.getSelectedItem();
        List<Item> selectedItems = itemList.getSelectedValuesList();
        double taxRate;

        try {
            taxRate = Double.parseDouble(taxRateField.getTextField().getText()) / 100;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(parentComponent, "Invalid tax rate", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (selectedClient == null || selectedItems.isEmpty()) {
            JOptionPane.showMessageDialog(parentComponent, "Please select a client and at least one item", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<BillItem> billItems = new ArrayList<>();
        for (Item item : selectedItems) {
            billItems.add(new BillItem(item, 1));
        }

        billManager.addBill(selectedClient, billItems, taxRate);
        JOptionPane.showMessageDialog(parentComponent, "Invoice created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

        parentComponent.close();
    }
}
