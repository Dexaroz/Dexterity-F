package software.dexterity.app.swing.support;

import software.dexterity.arquitecture.view.VisualComponent;

import javax.swing.*;
import java.awt.*;

public class SwingDesignInputField extends JPanel implements VisualComponent {

    private static final Color TEXT_COLOR = DarkGoldPalette.TextColor.getColor();
    private static final Color BACKGROUND_INPUT_COLOR = DarkGoldPalette.SearchInputBackground.getColor();
    private static final Font SEARCH_FONT = new Font("Arial", Font.PLAIN, 18);

    public SwingDesignInputField(String label) {
        this.setOpaque(false);
        this.setLayout(new BorderLayout());

        this.add(createInputField(label), BorderLayout.CENTER);
    }

    private Component createInputField(String label) {
        JTextField searchField = new JTextField(label);
        searchField.setFont(SEARCH_FONT);
        searchField.setForeground(TEXT_COLOR);
        searchField.setBackground(BACKGROUND_INPUT_COLOR);
        searchField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        searchField.setBorder(new SwingRoundedBorder(1));

        return searchField;
    }

    @Override
    public Object getComponent() {
        return this;
    }
}
