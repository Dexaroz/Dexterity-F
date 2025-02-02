package software.dexterity.app.swing.support;

import software.dexterity.arquitecture.view.VisualComponent;

import javax.swing.*;
import java.awt.*;

public class SwingDesignButton extends JPanel implements VisualComponent {

    private static final Color BACKGROUND_BUTTON_COLOR = DarkGoldPalette.ButtonBackgroundGold.getColor();
    private static final Color TEXT_COLOR = DarkGoldPalette.TextColor.getColor();
    private static final Font BUTTON_TEXT_FONT = new Font("Arial", Font.BOLD, 15);

    private final JButton button;

    public SwingDesignButton(String label) {
        this.setOpaque(false);
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(10, 280, 10, 280));

        JPanel panel = (JPanel) createRoundedButton(label);
        button = (JButton) panel.getComponent(0);

        this.add(panel, BorderLayout.CENTER);
    }

    private Component createRoundedButton(String label) {
        SwingRoundedPanel roundedPanel = new SwingRoundedPanel(BACKGROUND_BUTTON_COLOR, 10);
        roundedPanel.setLayout(new BorderLayout());

        JButton button = new JButton(label);
        button.setFont(BUTTON_TEXT_FONT);
        button.setForeground(TEXT_COLOR);
        button.setContentAreaFilled(false);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setFocusPainted(false);
        Dimension buttonSize = new Dimension(200, 30);
        button.setMaximumSize(buttonSize);
        button.setPreferredSize(buttonSize);
        button.setMinimumSize(buttonSize);

        roundedPanel.add(button, BorderLayout.CENTER);
        return roundedPanel;
    }

    public JButton getButton(){
        return button;
    }

    @Override
    public Object getComponent() {
        return this;
    }
}