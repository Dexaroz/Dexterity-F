package software.dexterity.app.swing.billsContent;

import software.dexterity.app.swing.support.DarkGoldPalette;
import software.dexterity.app.swing.support.SwingDesignButton;
import software.dexterity.app.swing.support.SwingDesignInputField;
import software.dexterity.arquitecture.model.BillItem;
import software.dexterity.arquitecture.model.Client;
import software.dexterity.arquitecture.model.Item;
import software.dexterity.arquitecture.model.managers.BillManager;
import software.dexterity.arquitecture.model.managers.ClientManager;
import software.dexterity.arquitecture.model.managers.ItemManager;
import software.dexterity.arquitecture.view.bill.BillFormDialog;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SwingBillDialog extends JDialog implements BillFormDialog {

    private static final Color BACKGROUND_COLOR = DarkGoldPalette.Background.getColor();
    private static final Color TEXT_COLOR = DarkGoldPalette.TextColor.getColor();
    private static final Font TITLE_FONT = new Font("Arial", Font.PLAIN, 14);

    private final BillManager billManager;

    private JComboBox<Client> clientComboBox;
    private JList<Item> itemList;
    private SwingDesignInputField taxRateField;
    private JLabel totalAmountLabel;

    public SwingBillDialog(JFrame parentFrame, BillManager billManager) {
        super(parentFrame, "Create Bill", true);
        this.billManager = billManager;

        this.setSize(700, 700);
        this.setMaximumSize(new Dimension(700, 700));
        this.setMinimumSize(new Dimension(700, 700));
        this.setLocationRelativeTo(parentFrame);
        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(BACKGROUND_COLOR);

        // Initialize form components
        this.add(createFormPanel(), BorderLayout.CENTER);
        this.add(createButtonPanel(), BorderLayout.SOUTH);
    }

    private JPanel createFormPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.setBorder(new EmptyBorder(20, 40, 20, 40));

        // Client selection
        clientComboBox = new JComboBox<>(getClientsArray());
        clientComboBox.setBackground(DarkGoldPalette.SearchInputBackground.getColor());
        clientComboBox.setForeground(TEXT_COLOR);
        clientComboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        panel.add(createLabeledComponent("Select a client:", clientComboBox));

        // Item selection
        itemList = new JList<>(getItemsArray());
        itemList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        itemList.setBackground(BACKGROUND_COLOR);
        itemList.setForeground(TEXT_COLOR);
        itemList.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane itemScrollPane = new JScrollPane(itemList);
        itemScrollPane.setBorder(BorderFactory.createEmptyBorder());
        panel.add(createLabeledComponent("Items:", itemScrollPane));

        // Tax rate input
        taxRateField = new SwingDesignInputField("Enter tax rate (%)");
        panel.add(createLabeledComponent("Tax Rate (%):", taxRateField));

        // Total amount display
        totalAmountLabel = new JLabel("Total: $0.00");
        totalAmountLabel.setFont(new Font("Arial", Font.BOLD, 18));
        totalAmountLabel.setForeground(TEXT_COLOR);
        totalAmountLabel.setBorder(new EmptyBorder(10, 0, 10, 0));
        panel.add(totalAmountLabel);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.setOpaque(false);

        JButton createButton = new SwingDesignButton("Create Invoice").getButton();
        createButton.addActionListener(e -> handleAddBill());

        JButton cancelButton = new SwingDesignButton("Cancel").getButton();
        cancelButton.addActionListener(e -> close());

        panel.add(cancelButton);
        panel.add(createButton);

        return panel;
    }

    private void handleAddBill() {
        Client selectedClient = (Client) clientComboBox.getSelectedItem();
        List<Item> selectedItems = itemList.getSelectedValuesList();
        double taxRate;

        try {
            taxRate = Double.parseDouble(taxRateField.getTextField().getText()) / 100;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid tax rate", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (selectedClient == null || selectedItems.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a client and at least one item", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<BillItem> billItems = new ArrayList<>();
        for (Item item : selectedItems) {
            billItems.add(new BillItem(item, 1));
        }

        billManager.addBill(selectedClient, billItems, taxRate);
        JOptionPane.showMessageDialog(this, "Invoice created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

        close();
    }

    private Client[] getClientsArray() {
        return new ClientManager().getClients().toArray(new Client[0]);
    }

    private Item[] getItemsArray() {
        return new ItemManager().getItems().toArray(new Item[0]);
    }

    private JPanel createLabeledComponent(String labelText, Component component) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);

        JLabel label = new JLabel(labelText);
        label.setForeground(TEXT_COLOR);
        label.setFont(TITLE_FONT);

        panel.add(label, BorderLayout.NORTH);
        panel.add(component, BorderLayout.CENTER);
        panel.setBorder(new EmptyBorder(10, 0, 10, 0));

        return panel;
    }

    @Override
    public void open() {
        this.setVisible(true);
    }

    @Override
    public void close() {
        this.dispose();
    }
}
