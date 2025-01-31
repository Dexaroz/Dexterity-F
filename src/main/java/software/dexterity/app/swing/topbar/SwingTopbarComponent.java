package software.dexterity.app.swing.topbar;

import software.dexterity.app.swing.support.DarkGoldPalette;
import software.dexterity.app.swing.support.SwingButtonText;
import software.dexterity.arquitecture.control.Command;
import software.dexterity.arquitecture.view.Topbar;
import software.dexterity.arquitecture.view.VisualComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SwingTopbarComponent extends JPanel implements VisualComponent, Topbar {

    private static final Color BACKGROUND_COLOR = DarkGoldPalette.Background.getColor();

    private final SwingTopbarLogoName logoName;
    private final SwingTopbarHorizontalMenu horizontalMenu;
    private final Map<String, JButton> buttons;

    public SwingTopbarComponent(String title, URL imageUrl) {
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(44, 44, 46)));
        this.setBackground(BACKGROUND_COLOR);

        this.buttons = new HashMap<>();

        logoName = new SwingTopbarLogoName(title, imageUrl);
        horizontalMenu = new SwingTopbarHorizontalMenu();

        this.add(horizontalMenu.getComponent(), BorderLayout.CENTER);
        this.add(logoName.getComponent(), BorderLayout.WEST);
    }

    private JButton createButton(String name){
        return new SwingButtonText(name);
    }

    @Override
    public JComponent getComponent() {
        return this;
    }

    @Override
    public void addButton(String label, Command command) {
        if (!buttons.containsKey(label)){
            JButton button = createButton(label);
            button.addActionListener(e -> command.execute());
            buttons.put(label, button);
            horizontalMenu.add(button);
        }
    }

    @Override
    public void setButtonAction(String label, Command command) {
        JButton button = buttons.get(label);
        if (button != null) {
            for (ActionListener al : button.getActionListeners()) {
                button.removeActionListener(al);
            }
            button.addActionListener(e -> command.execute());
        }
    }
}
