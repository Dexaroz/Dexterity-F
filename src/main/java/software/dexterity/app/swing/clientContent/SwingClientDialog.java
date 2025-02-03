package software.dexterity.app.swing.clientContent;

import software.dexterity.app.swing.support.DarkGoldPalette;
import software.dexterity.app.swing.support.SwingDesignButton;
import software.dexterity.app.swing.support.SwingDesignInputField;
import software.dexterity.arquitecture.control.Command;
import software.dexterity.arquitecture.model.managers.ClientManager;
import software.dexterity.arquitecture.view.client.ClientFormDialog;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Map;

public class SwingClientDialog extends JDialog implements ClientFormDialog {

    private static final Color BACKGROUND_COLOR = DarkGoldPalette.Background.getColor();
    private static final Color TEXT_COLOR = DarkGoldPalette.TextColor.getColor();
    private static final Font LABEL_FONT = new Font("Arial", Font.BOLD, 14);

    private final ClientManager clientManager;
    private final Map<String, Command> commands;

    private SwingDesignInputField nameField;
    private SwingDesignInputField emailField;
    private SwingDesignInputField phoneField;
    private SwingDesignInputField countryField;
    private SwingDesignInputField provinceField;
    private SwingDesignInputField cityField;
    private SwingDesignInputField postalCodeField;
    private SwingDesignInputField streetField;
    private SwingDesignInputField numberField;
    private SwingDesignInputField suiteField;
    private SwingDesignInputField taxIDField;

    public SwingClientDialog(JFrame parentFrame, ClientManager clientManager, Map<String, Command> commands) {
        super(parentFrame, "Create a new client", true);
        this.clientManager = clientManager;
        this.commands = commands;

        this.setSize(700, 900);
        this.setMaximumSize(new Dimension(700, 900));
        this.setMinimumSize(new Dimension(700, 900));
        this.setLocationRelativeTo(parentFrame);
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        this.getContentPane().setBackground(BACKGROUND_COLOR);

        this.add(createFormPanel());
        this.add(Box.createVerticalStrut(20));
        this.add(createButtonPanel());
    }

    private JPanel createFormPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.setBorder(new EmptyBorder(20, 40, 20, 40));

        panel.add(createInputField("Full name", nameField = new SwingDesignInputField("Enter full name")));
        panel.add(createInputField("Email address", emailField = new SwingDesignInputField("Enter email address")));
        panel.add(createInputField("Phone number", phoneField = new SwingDesignInputField("Enter phone number")));
        panel.add(createInputField("Street", streetField = new SwingDesignInputField("Enter street name")));
        panel.add(createDoubleFieldRow("City", cityField = new SwingDesignInputField("Enter city"), "State/province", provinceField = new SwingDesignInputField("Enter state/province")));
        panel.add(createDoubleFieldRow("Postal code", postalCodeField = new SwingDesignInputField("Enter postal code"), "Country", countryField = new SwingDesignInputField("Enter country")));

        return panel;
    }

    private JPanel createInputField(String label, SwingDesignInputField inputField) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);

        JLabel fieldLabel = new JLabel(label);
        fieldLabel.setForeground(TEXT_COLOR);
        fieldLabel.setFont(LABEL_FONT);

        panel.add(fieldLabel, BorderLayout.NORTH);
        panel.add(inputField, BorderLayout.CENTER);
        inputField.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
        panel.setBorder(new EmptyBorder(10, 0, 10, 0));

        return panel;
    }

    private JPanel createDoubleFieldRow(String label1, SwingDesignInputField field1, String label2, SwingDesignInputField field2) {
        JPanel rowPanel = new JPanel();
        rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.X_AXIS));
        rowPanel.setOpaque(false);

        JPanel panel1 = createInputField(label1, field1);
        JPanel panel2 = createInputField(label2, field2);

        panel1.setBorder(new EmptyBorder(0, 0, 0, 10));
        panel2.setBorder(new EmptyBorder(0, 10, 0, 0));

        rowPanel.add(panel1);
        rowPanel.add(panel2);

        return rowPanel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.setOpaque(false);

        SwingDesignButton saveButton = new SwingDesignButton("Save");
        JButton saveButtonComponent = saveButton.getButton();
        saveButtonComponent.addActionListener(e -> handleAddClient());

        panel.add(saveButton);

        return panel;
    }

    private void handleAddClient() {
        String name = nameField.getTextField().getText().trim();
        String email = emailField.getTextField().getText().trim();
        String phoneNumber = phoneField.getTextField().getText().trim();
        String street = streetField.getTextField().getText().trim();
        String city = cityField.getTextField().getText().trim();
        String province = provinceField.getTextField().getText().trim();
        String postalCodeText = postalCodeField.getTextField().getText().trim();
        String country = countryField.getTextField().getText().trim();

        if (name.isEmpty() || email.isEmpty() || phoneNumber.isEmpty() || street.isEmpty()
                || city.isEmpty() || province.isEmpty() || postalCodeText.isEmpty() || country.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!isValidEmail(email)) {
            JOptionPane.showMessageDialog(this, "Invalid email format", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int postalCode;
        try {
            postalCode = Integer.parseInt(postalCodeText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Postal code must be a valid integer", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        clientManager.addClient(name, email, phoneNumber, country, province, city, postalCode, street, 0, "", "");

        JOptionPane.showMessageDialog(this, "Client added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        close();
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email.matches(emailRegex);
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