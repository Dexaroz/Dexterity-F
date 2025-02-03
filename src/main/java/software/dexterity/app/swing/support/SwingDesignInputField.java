package software.dexterity.app.swing.support;

import software.dexterity.arquitecture.view.VisualComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class SwingDesignInputField extends JPanel implements VisualComponent {

    private static final Color TEXT_COLOR = DarkGoldPalette.TextColor.getColor();
    private static final Color BACKGROUND_INPUT_COLOR = DarkGoldPalette.SearchInputBackground.getColor();
    private static final Font SEARCH_FONT = new Font("Arial", Font.PLAIN, 18);
    private static final Font PLACEHOLDER_FONT = new Font("Arial", Font.ITALIC, 18);

    private final JTextField inputField;

    public SwingDesignInputField(String label) {
        this.setOpaque(false);
        this.setLayout(new BorderLayout());

        inputField = (JTextField) createInputField(label);

        SwingRoundedPanel roundedPanel = new SwingRoundedPanel(BACKGROUND_INPUT_COLOR, 20);
        roundedPanel.setLayout(new BorderLayout());
        roundedPanel.add(inputField, BorderLayout.CENTER);

        this.add(roundedPanel, BorderLayout.CENTER);
    }

    private Component createInputField(String label) {
        JTextField searchField = new JTextField();
        searchField.setFont(SEARCH_FONT);
        searchField.setForeground(TEXT_COLOR);
        searchField.setCaretColor(TEXT_COLOR);
        searchField.setOpaque(false);
        searchField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        searchField.setMaximumSize(new Dimension(200, 25));
        searchField.setMinimumSize(new Dimension(200, 25));

        searchField.setText(label);
        searchField.setFont(PLACEHOLDER_FONT);
        searchField.setForeground(Color.GRAY);

        searchField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals(label)) {
                    searchField.setText("");
                    searchField.setFont(SEARCH_FONT);
                    searchField.setForeground(TEXT_COLOR);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText(label);
                    searchField.setFont(PLACEHOLDER_FONT);
                    searchField.setForeground(Color.GRAY);
                }
            }
        });


        return searchField;
    }

    public JTextField getTextField() {
        return inputField;
    }

    @Override
    public Object getComponent() {
        return this;
    }
}