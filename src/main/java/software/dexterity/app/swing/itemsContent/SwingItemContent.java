package software.dexterity.app.swing.itemsContent;

import software.dexterity.app.swing.support.DarkGoldPalette;
import software.dexterity.app.swing.support.SwingDesignButton;
import software.dexterity.app.swing.support.SwingDesignInputField;
import software.dexterity.app.swing.support.SwingDesingTable;
import software.dexterity.arquitecture.control.Command;
import software.dexterity.arquitecture.model.managers.ItemManager;
import software.dexterity.arquitecture.view.VisualComponent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Map;

public class SwingItemContent extends JPanel implements VisualComponent {

    private static final Color BACKGROUND_COLOR = DarkGoldPalette.Background.getColor();
    private static final Color TEXT_COLOR = DarkGoldPalette.TextColor.getColor();
    private static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 32);

    private final ItemManager itemManager;
    private final Map<String, Command> commands;

    public SwingItemContent(ItemManager itemManager, Map<String, Command> commands) {
        this.commands = commands;
        this.setBackground(BACKGROUND_COLOR);
        this.setLayout(new BorderLayout());

        this.itemManager = itemManager;

        this.setBorder(new EmptyBorder(20,90,50,90));
        this.add(createTitlePanel(), BorderLayout.NORTH);
        this.add(createTablePanel(), BorderLayout.CENTER);
        this.add(createButtonPanel(), BorderLayout.SOUTH);
    }

    private Component createTitlePanel() {
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setOpaque(false);
        titlePanel.setBorder(new EmptyBorder(0, 0, 20,0));

        JLabel titleLabel = new JLabel("Items", SwingConstants.LEFT);
        titleLabel.setForeground(TEXT_COLOR);
        titleLabel.setFont(TITLE_FONT);

        SwingDesignInputField searchField = (SwingDesignInputField) createSearchField();
        searchField.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));

        titlePanel.add(titleLabel, BorderLayout.NORTH);
        titlePanel.add(searchField, BorderLayout.SOUTH);

        return titlePanel;
    }

    private Component createSearchField() {
        return new SwingDesignInputField("Search items...");
    }

    private Component createTablePanel() {
        SwingDesingTable table = new SwingDesingTable(new String[]{"Name", "Price", "Edit", "Delete"});

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setBackground(BACKGROUND_COLOR);

        return scrollPane;
    }

    private Component createButtonPanel(){
        SwingDesignButton button = new SwingDesignButton("Add Item");
        JButton component = button.getButton();
        component.addActionListener(e -> {
            commands.get("Add").execute();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);
        buttonPanel.add(button);
        buttonPanel.add(Box.createVerticalStrut(10));

        return buttonPanel;
    }

    @Override
    public Object getComponent() {
        return this;
    }
}