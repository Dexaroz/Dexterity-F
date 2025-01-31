package software.dexterity.arquitecture.model.support;

import java.util.HashMap;
import java.util.Map;

public record LineChart(String title, String xAxis, String yAxis, Map<String, Double> data) {

    public LineChart(String title, String xAxis, String yAxis){
        this(title, xAxis, yAxis, new HashMap<>());
    }

    public void put(String category, double value){
        data.put(category, value);
    }
}
