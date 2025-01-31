package software.dexterity.app.swing.support;

import software.dexterity.arquitecture.view.VisualComponent;

import javax.swing.*;
import java.awt.*;

public class SwingDesignButton extends JPanel implements VisualComponent {

    private final static Color BACKGROUND_BUTTON_COLOR = DarkGoldPalette.ButtonBackgroundGold.getColor();
    private static final Color TEXT_COLOR = DarkGoldPalette.TextColor.getColor();
    private static final Font BUTTON_TEXT_FONT = new Font("Arial", Font.BOLD , 15);

    public SwingDesignButton(String label) {
        this.setOpaque(false);
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(10,280,0,280));

        this.add(getButtonDesign(label), BorderLayout.CENTER);
    }

    private Component getButtonDesign(String label) {
        JButton button = new JButton(label);
        button.setBackground(BACKGROUND_BUTTON_COLOR);
        button.setFont(BUTTON_TEXT_FONT);
        button.setForeground(TEXT_COLOR);
        button.setBorder(new SwingRoundedBorder(1));

        return button;
    }

    @Override
    public Object getComponent() {
        return this;
    }
}
