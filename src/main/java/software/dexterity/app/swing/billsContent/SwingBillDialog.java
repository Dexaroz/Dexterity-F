package software.dexterity.app.swing.billsContent;

import software.dexterity.app.swing.support.DarkGoldPalette;
import software.dexterity.app.swing.support.SwingDesignButton;
import software.dexterity.app.swing.support.SwingDesignInputField;
import software.dexterity.arquitecture.control.Command;
import software.dexterity.arquitecture.control.bill.AddBillCommand;
import software.dexterity.arquitecture.control.bill.CancelAddBillCommand;
import software.dexterity.arquitecture.model.Client;
import software.dexterity.arquitecture.model.Item;
import software.dexterity.arquitecture.model.managers.BillManager;
import software.dexterity.arquitecture.model.managers.ClientManager;
import software.dexterity.arquitecture.model.managers.ItemManager;
import software.dexterity.arquitecture.view.bill.BillFormDialog;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Map;

public class SwingBillDialog extends JDialog implements BillFormDialog {

    private static final Color BACKGROUND_COLOR = DarkGoldPalette.Background.getColor();
    private static final Color SEARCH_INPUT_BACKGROUND = DarkGoldPalette.SearchInputBackground.getColor();
    private static final Color LINE_BORDER = DarkGoldPalette.BorderLineTable.getColor();
    private static final Color TEXT_COLOR = DarkGoldPalette.TextColor.getColor();
    private static final Font TEXT_FONT = new Font("Arial", Font.PLAIN, 14);


    private final BillManager billManager;

    private JComboBox<Client> clientComboBox;
    private JList<Item> itemList;
    private SwingDesignInputField taxRateField;
    private JLabel totalAmountLabel;
    private Map<String, Command> commands;

    public SwingBillDialog(JFrame parentFrame, BillManager billManager, Map<String, Command> commands) {
        super(parentFrame, "Create Bill", true);
        this.billManager = billManager;
        this.commands = commands;

        this.setSize(700,  900);
        this.setMaximumSize(new Dimension(700, 900));
        this.setMinimumSize(new Dimension(700, 900));


        this.setLocationRelativeTo(parentFrame);
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        this.getContentPane().setBackground(BACKGROUND_COLOR);

        this.add(createFormPanel(), BorderLayout.CENTER);
        this.add(Box.createVerticalStrut(10));
        this.add(createButtonPanel(), BorderLayout.SOUTH);
    }

    private JPanel createFormPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.setBorder(new EmptyBorder(20, 40, 20, 40));

        JPanel clientComboBoxPanel = getClientComboBox();
        clientComboBoxPanel.setPreferredSize(new Dimension(700, 90));
        clientComboBoxPanel.setMaximumSize(new Dimension(700, 90));
        panel.add(Box.createVerticalStrut(10));

        panel.add(clientComboBoxPanel);
        panel.add(Box.createVerticalStrut(10));

        panel.add(getItemList());
        panel.add(Box.createVerticalStrut(10));

        panel.add(getTaxRateField());

        panel.add(Box.createVerticalStrut(10));
        panel.add(getTotalAmountLabel());

        return panel;
    }

    private JPanel getClientComboBox(){
        clientComboBox = new JComboBox<>(getClientsArray());
        clientComboBox.setBackground(SEARCH_INPUT_BACKGROUND);
        clientComboBox.setForeground(TEXT_COLOR);
        clientComboBox.setFont(TEXT_FONT);

        return createLabeledComponent("Select a client:", clientComboBox);
    }

    private JPanel getItemList(){
        itemList = new JList<>(getItemsArray());
        itemList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        itemList.setBackground(BACKGROUND_COLOR);
        itemList.setForeground(TEXT_COLOR);
        itemList.setFont(TEXT_FONT);

        JScrollPane itemScrollPane = new JScrollPane(itemList);
        itemScrollPane.setPreferredSize(new Dimension(400, 500));
        itemScrollPane.setMaximumSize(new Dimension(400, 500));
        itemScrollPane.setBorder(BorderFactory.createLineBorder(LINE_BORDER));

        return createLabeledComponent("Items:", itemScrollPane);
    }

    private JPanel getTaxRateField(){
        taxRateField = new SwingDesignInputField("Enter tax rate (%)");

        taxRateField.setPreferredSize(new Dimension(400,50));
        taxRateField.setMaximumSize(new Dimension(400,50));
        taxRateField.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));

        return createLabeledComponent("Tax Rate (%):", taxRateField);
    }

    private JPanel getTotalAmountLabel(){
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);

        totalAmountLabel = new JLabel("Total: $0.00");
        totalAmountLabel.setFont(new Font("Arial", Font.BOLD, 18));
        totalAmountLabel.setForeground(TEXT_COLOR);
        totalAmountLabel.setBorder(new EmptyBorder(10, 0, 10, 0));
        totalAmountLabel.setHorizontalAlignment(SwingConstants.CENTER);

        panel.add(totalAmountLabel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 0));
        panel.setOpaque(false);

        SwingDesignButton createButton = new SwingDesignButton("Create Bill");
        JButton createButtonComponent = createButton.getButton();
        createButtonComponent.addActionListener(e -> new AddBillCommand(billManager, clientComboBox, itemList, taxRateField, this).execute());

        panel.add(createButton);

        return panel;
    }

    private Client[] getClientsArray() {
        return new ClientManager().getClientsToArray();
    }

    private Item[] getItemsArray() {
        return new ItemManager().getItemsToArray();
    }

    private JPanel createLabeledComponent(String labelText, Component component) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);

        JLabel label = new JLabel(labelText);
        label.setForeground(TEXT_COLOR);
        label.setFont(TEXT_FONT);

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
