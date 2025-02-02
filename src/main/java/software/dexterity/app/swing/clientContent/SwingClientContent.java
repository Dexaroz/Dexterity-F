package software.dexterity.app.swing.clientContent;

import software.dexterity.app.swing.support.DarkGoldPalette;
import software.dexterity.app.swing.support.SwingDesignButton;
import software.dexterity.app.swing.support.SwingDesignInputField;
import software.dexterity.app.swing.support.SwingDesingTable;
import software.dexterity.arquitecture.control.Command;
import software.dexterity.arquitecture.model.managers.ClientManager;
import software.dexterity.arquitecture.view.VisualComponent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Map;

public class SwingClientContent extends JPanel implements VisualComponent {

    private static final Color BACKGROUND_COLOR = DarkGoldPalette.Background.getColor();
    private static final Color TEXT_COLOR = DarkGoldPalette.TextColor.getColor();
    private static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 32);
    private static final Font SEARCH_FONT = new Font("Arial", Font.PLAIN, 18);

    private final ClientManager clientManager;
    private final Map<String, Command> commands;

    public SwingClientContent(ClientManager clientManager, Map<String, Command> commands) {
        this.commands = commands;
        this.setBackground(BACKGROUND_COLOR);
        this.setLayout(new BorderLayout());

        this.clientManager = clientManager;

        this.setBorder(new EmptyBorder(20,90,50,90));
        this.add(createTitlePanel(), BorderLayout.NORTH);
        this.add(createTablePanel(), BorderLayout.CENTER);
        this.add(createButtonPabel(), BorderLayout.SOUTH);
    }

    private Component createTitlePanel() {
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setOpaque(false);
        titlePanel.setBorder(new EmptyBorder(0, 0, 20,0));

        JLabel titleLabel = new JLabel("Clients", SwingConstants.LEFT);
        titleLabel.setForeground(TEXT_COLOR);
        titleLabel.setFont(TITLE_FONT);

        SwingDesignInputField searchField = (SwingDesignInputField) createSearchField();
        searchField.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));

        titlePanel.add(titleLabel, BorderLayout.NORTH);
        titlePanel.add(searchField, BorderLayout.SOUTH);

        return titlePanel;
    }

    private Component createSearchField() {
        return new SwingDesignInputField("Search clients...");
    }

    private Component createTablePanel() {
        SwingDesingTable table = new SwingDesingTable(new String[]{"Name", "Email", "Phone", "Edit", "Delete"});

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setBackground(BACKGROUND_COLOR);

        return scrollPane;
    }

    private Component createButtonPabel(){
        SwingDesignButton button = new SwingDesignButton("Add Client");
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