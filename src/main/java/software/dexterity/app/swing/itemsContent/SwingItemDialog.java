package software.dexterity.app.swing.itemsContent;

import software.dexterity.arquitecture.control.Command;
import software.dexterity.arquitecture.model.managers.ItemManager;
import software.dexterity.arquitecture.view.item.ItemFormDialog;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class SwingItemDialog extends JDialog implements ItemFormDialog {
    private final ItemManager itemManager;
    private JTextField nameField;
    private JTextField descriptionField;
    private JTextField priceField;
    private final Map<String, Command> commands;

    public SwingItemDialog(JFrame parentFrame, ItemManager itemManager, Map<String, Command> commands) {
        super(parentFrame, "Add/Edit Item", true);
        this.itemManager = itemManager;
        this.commands = commands;

        this.setSize(400, 300);
        this.setLocationRelativeTo(parentFrame);
        this.setLayout(new BorderLayout());

        this.add(createFormPanel(), BorderLayout.CENTER);
        this.add(createButtonPanel(), BorderLayout.SOUTH);
    }

    private JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("Item Name:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Description:"));
        descriptionField = new JTextField();
        panel.add(descriptionField);

        panel.add(new JLabel("Price per Unit:"));
        priceField = new JTextField();
        panel.add(priceField);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout());

        JButton addButton = new JButton("Add Item");
        addButton.addActionListener(e -> handleAddItem());

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> close());

        panel.add(addButton);
        panel.add(cancelButton);

        return panel;
    }

    private void handleAddItem() {
        String name = nameField.getText().trim();
        String description = descriptionField.getText().trim();
        double price;

        try {
            price = Double.parseDouble(priceField.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid price", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a valid item name", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        itemManager.addItem(name, description, price);
        JOptionPane.showMessageDialog(this, "Item added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

        close();
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
