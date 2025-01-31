package software.dexterity.app.swing.support;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import software.dexterity.arquitecture.model.support.LineChart;
import software.dexterity.arquitecture.view.LineChartDisplay;

import javax.swing.*;
import java.awt.*;

public class SwingJFreeLineChartDisplay extends JPanel implements LineChartDisplay {

    public SwingJFreeLineChartDisplay() {
        this.setLayout(new BorderLayout());
    }

    @Override
    public void show(LineChart lineChart) {
        removeAll();
        add(new ChartPanel(adapt(lineChart)), BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private JFreeChart adapt(LineChart lineChart) {
        return JFreeChartLineAdapter.adapt(lineChart);
    }
}
