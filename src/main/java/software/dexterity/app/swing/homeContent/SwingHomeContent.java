package software.dexterity.app.swing.homeContent;

import software.dexterity.app.swing.support.DarkGoldPalette;
import software.dexterity.app.swing.support.SwingJFreeLineChartDisplay;
import software.dexterity.arquitecture.control.Command;
import software.dexterity.arquitecture.model.Bill;
import software.dexterity.arquitecture.model.BillItem;
import software.dexterity.arquitecture.model.Client;
import software.dexterity.arquitecture.model.Item;
import software.dexterity.arquitecture.model.managers.BillManager;
import software.dexterity.arquitecture.model.support.LineChart;
import software.dexterity.arquitecture.view.VisualComponent;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class SwingHomeContent extends JPanel implements VisualComponent {

    private static final Color BACKGROUND_COLOR = DarkGoldPalette.Background.getColor();
    private static final Color TEXT_COLOR = DarkGoldPalette.TextColor.getColor();
    private static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 24);

    private final BillManager billManager;
    //private final SwingJFreeLineChartDisplay invoicesChart;
    private final Map<String, Command> commands;

    public SwingHomeContent(BillManager billManager, Map<String, Command> commands) {
        this.commands = commands;
        this.setBackground(BACKGROUND_COLOR);
        this.setLayout(new BorderLayout());

        this.billManager = billManager;
        this.add(createWelcomeLabel(), BorderLayout.NORTH);
        //this.add(this.invoicesChart = (SwingJFreeLineChartDisplay) getInvoicesChart(), BorderLayout.CENTER);
        //this.invoicesChart.show(createLineChartWithData());
    }

    private Component createWelcomeLabel() {
        JPanel panel = new JPanel();

        JLabel label = new JLabel("Welcome Tester!", SwingConstants.CENTER);
        label.setForeground(TEXT_COLOR);
        label.setFont(TITLE_FONT);
        label.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));

        panel.add(label);
        panel.setOpaque(false);
        return panel;
    }

    private Component getInvoicesChart() {
        SwingJFreeLineChartDisplay chartDisplay = new SwingJFreeLineChartDisplay();
        return new SwingJFreeLineChartDisplay();
    }

    private LineChart createLineChartWithData() {
        LineChart lineChart = new LineChart("Incomes Current Year", "Months", "Incomes");

        Map<String, Double> monthlyTotals = new HashMap<>();

        for (Bill bill : billManager.getBills()) {
            String month = bill.getDate().getMonth().toString();
            monthlyTotals.put(month, monthlyTotals.getOrDefault(month, 0.0) + bill.getTotal());
        }

        for (String month : getOrderedMonths()) {
            if (monthlyTotals.containsKey(month)) {
                lineChart.put(month, monthlyTotals.get(month));
            }
        }

        return lineChart;
    }

    private String[] getOrderedMonths() {
        return new String[]{
                "JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE",
                "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER"
        };
    }

    @Override
    public Object getComponent() {
        return this;
    }
}
