package software.dexterity.app.swing.support;

import software.dexterity.arquitecture.view.VisualComponent;

import javax.swing.*;
import java.awt.*;

public class SwingButtonText extends JButton implements VisualComponent {

    private static final Color TEXT_COLOR = DarkGoldPalette.TextColor.getColor();
    private static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 15);

    public SwingButtonText(String label){
        this.setLabel(label);
        this.setMargin(new Insets(1,1,1,1));
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setForeground(TEXT_COLOR);
        this.setFont(TITLE_FONT);
    }

    @Override
    public Object getComponent() {
        return this;
    }
}
