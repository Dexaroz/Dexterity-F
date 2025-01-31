package software.dexterity.app.swing.topbar;

import software.dexterity.app.swing.support.DarkGoldPalette;
import software.dexterity.arquitecture.view.VisualComponent;

import javax.swing.*;
import java.awt.*;

public class SwingTopbarHorizontalMenu extends JPanel implements VisualComponent {

    private static final Color BACKGROUND_COLOR = DarkGoldPalette.Background.getColor();

    public SwingTopbarHorizontalMenu() {
        this.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 15));

        this.setOpaque(true);
        this.setBackground(BACKGROUND_COLOR);
    }

    @Override
    public JComponent getComponent() {
        return this;
    }
}
