package software.dexterity.app.swing.support;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.SymbolAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import software.dexterity.arquitecture.model.support.LineChart;

import java.awt.*;

public class JFreeChartLineAdapter {

    private static final Color BACKGROUND_COLOR = DarkGoldPalette.Background.getColor();
    private static final Color TEXT_COLOR = DarkGoldPalette.TextColor.getColor();
    private static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 12);

    public static JFreeChart adapt(LineChart lineChart) {
        XYSeries series = new XYSeries("Incomes");
        for (String month : lineChart.data().keySet()) {
            series.add(monthToIndex(month), lineChart.data().get(month));
        }

        XYSeriesCollection dataset = new XYSeriesCollection(series);

        JFreeChart chart = ChartFactory.createXYLineChart(
                lineChart.title(),
                null,
                lineChart.yAxis(),
                dataset,
                PlotOrientation.VERTICAL,
                false,
                true,
                false
        );

        customizeChart(chart);

        return chart;
    }

    private static void customizeChart(JFreeChart chart) {
        XYPlot plot = chart.getXYPlot();

        plot.setBackgroundPaint(null);
        plot.setDomainGridlinesVisible(false);
        plot.setRangeGridlinesVisible(false);

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, false);
        renderer.setSeriesPaint(0, new Color(240, 230, 140));  // Color de la línea
        renderer.setSeriesStroke(0, new BasicStroke(3.0f));    // Grosor de la línea
        plot.setRenderer(renderer);

        SymbolAxis xAxis = new SymbolAxis(null, new String[]{
                "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
        });
        xAxis.setTickLabelFont(TITLE_FONT);
        xAxis.setTickLabelPaint(TEXT_COLOR);
        xAxis.setAxisLineVisible(false);
        xAxis.setTickMarksVisible(false);

        plot.setDomainAxis(xAxis);
        plot.setOutlineVisible(false);

        chart.setBackgroundPaint(BACKGROUND_COLOR);
    }

    private static int monthToIndex(String month) {
        return switch (month) {
            case "JANUARY" -> 0;
            case "FEBRUARY" -> 1;
            case "MARCH" -> 2;
            case "APRIL" -> 3;
            case "MAY" -> 4;
            case "JUNE" -> 5;
            case "JULY" -> 6;
            case "AUGUST" -> 7;
            case "SEPTEMBER" -> 8;
            case "OCTOBER" -> 9;
            case "NOVEMBER" -> 10;
            case "DECEMBER" -> 11;
            default -> 0;
        };
    }
}
