package software.dexterity.app.swing.clientContent;

import software.dexterity.arquitecture.model.managers.ClientManager;
import software.dexterity.arquitecture.view.client.ClientFormDialog;

import javax.swing.*;
import java.awt.*;

public class SwingClientDialog extends JDialog implements ClientFormDialog {

    private JTextField nameField;
    private JTextField emailField;
    private JTextField phoneField;
    private JTextField countryField;
    private JTextField provinceField;
    private JTextField cityField;
    private JTextField postalCodeField;
    private JTextField streetField;
    private JTextField numberField;
    private JTextField suiteField;
    private JTextField taxIDField;
    private final ClientManager clientManager;

    public SwingClientDialog(JFrame parentFrame, ClientManager clientManager) {
        super(parentFrame, "Add New Client", true);
        this.clientManager = clientManager;

        this.setSize(600, 500);
        this.setLocationRelativeTo(parentFrame);
        this.setLayout(new BorderLayout());

        this.add(createFormPanel(), BorderLayout.CENTER);
        this.add(createButtonPanel(), BorderLayout.SOUTH);
    }

    private JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridLayout(11, 2, 10, 10));

        // Name field
        panel.add(new JLabel("Name:"));
        nameField = new JTextField();
        panel.add(nameField);

        // Email field
        panel.add(new JLabel("Email:"));
        emailField = new JTextField();
        panel.add(emailField);

        // Phone number field
        panel.add(new JLabel("Phone Number:"));
        phoneField = new JTextField();
        panel.add(phoneField);

        // Country field
        panel.add(new JLabel("Country:"));
        countryField = new JTextField();
        panel.add(countryField);

        // Province field
        panel.add(new JLabel("Province:"));
        provinceField = new JTextField();
        panel.add(provinceField);

        // City field
        panel.add(new JLabel("City:"));
        cityField = new JTextField();
        panel.add(cityField);

        // Postal code field
        panel.add(new JLabel("Postal Code:"));
        postalCodeField = new JTextField();
        panel.add(postalCodeField);

        // Street field
        panel.add(new JLabel("Street:"));
        streetField = new JTextField();
        panel.add(streetField);

        // Number field
        panel.add(new JLabel("Street Number:"));
        numberField = new JTextField();
        panel.add(numberField);

        // Suite field
        panel.add(new JLabel("Suite (optional):"));
        suiteField = new JTextField();
        panel.add(suiteField);

        // Tax ID field
        panel.add(new JLabel("Tax ID:"));
        taxIDField = new JTextField();
        panel.add(taxIDField);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout());

        JButton addButton = new JButton("Add Client");
        addButton.addActionListener(e -> handleAddClient());

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> close());

        panel.add(addButton);
        panel.add(cancelButton);

        return panel;
    }

    private void handleAddClient() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String phoneNumber = phoneField.getText().trim();
        String country = countryField.getText().trim();
        String province = provinceField.getText().trim();
        String city = cityField.getText().trim();
        String postalCodeText = postalCodeField.getText().trim();
        String street = streetField.getText().trim();
        String numberText = numberField.getText().trim();
        String suite = suiteField.getText().trim();
        String taxID = taxIDField.getText().trim();

        // Validations
        if (name.isEmpty() || email.isEmpty() || phoneNumber.isEmpty() || country.isEmpty() || province.isEmpty()
                || city.isEmpty() || postalCodeText.isEmpty() || street.isEmpty() || numberText.isEmpty() || taxID.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields except 'Suite' are required", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!isValidEmail(email)) {
            JOptionPane.showMessageDialog(this, "Invalid email format", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int postalCode, number;
        try {
            postalCode = Integer.parseInt(postalCodeText);
            number = Integer.parseInt(numberText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Postal code and street number must be valid integers", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Create and add client
        clientManager.addClient(name, email, phoneNumber, country, province, city, postalCode, street, number, suite, taxID);

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