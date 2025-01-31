package software.dexterity.app.swing.support;

import software.dexterity.arquitecture.view.VisualComponent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SwingDesingTable extends JPanel implements VisualComponent {

    private static final Color BACKGROUND_COLOR = DarkGoldPalette.Background.getColor();
    private static final Color TEXT_COLOR = DarkGoldPalette.TextColor.getColor();
    private static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 15);
    private static final Color HEADER_TEXT_COLOR = DarkGoldPalette.TextInput.getColor();;
    private static final Color LINE_TABLE_COLOR = DarkGoldPalette.BorderLineTable.getColor();
    private static final Color HEADER_BACKGROUND_COLOR = DarkGoldPalette.BackgroundHeaderBackgroundTable.getColor();
    private static final Font HEADER_TITLE_FONT = new Font("Arial", Font.BOLD, 15);

    private JTable table;
    private DefaultTableModel tableModel;

    public SwingDesingTable(String[] fields) {
        this.setLayout(new BorderLayout());
        this.setBackground(BACKGROUND_COLOR);

        tableModel = createTableModel(fields);
        table = createTable();

        JScrollPane jScrollPane = new JScrollPane(table);
        jScrollPane.setBorder(BorderFactory.createEmptyBorder());

        this.add(jScrollPane, BorderLayout.CENTER);
    }

    private DefaultTableModel createTableModel(String[] fields){
        return new DefaultTableModel(fields, 0){
            @Override
            public boolean isCellEditable(int col, int row){
                return col >= 3;
            }
        };
    }

    private JTable createTable(){
        JTable table = new JTable(tableModel);
        table.setBackground(BACKGROUND_COLOR);
        table.setBorder(new SwingRoundedBorder(1));
        table.setFillsViewportHeight(true);
        table.setRowHeight(30);
        table.setGridColor(BACKGROUND_COLOR);
        table.setFont(TITLE_FONT);
        table.getTableHeader().setFont(HEADER_TITLE_FONT);
        table.getTableHeader().setBackground(HEADER_BACKGROUND_COLOR);
        table.getTableHeader().setForeground(Color.WHITE);
        table.setShowVerticalLines(true);
        table.setSelectionBackground(LINE_TABLE_COLOR);
        table.setSelectionForeground(TEXT_COLOR);
        return table;
    }

    @Override
    public Object getComponent() {
        return this;
    }
}
