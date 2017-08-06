package billboard.models;

import java.util.HashMap;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 *
 * @author vita, dash
 */
public class BarGraph  {
    private final BarChart<String, Number> bc;
    
    public BarGraph(HashMap settings, ObservableList<Entry> entries) {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        bc = new BarChart<>(xAxis, yAxis);
        bc.setTitle((String) settings.get("title"));
        xAxis.setLabel((String) settings.get("xAxis"));       
        yAxis.setLabel((String) settings.get("yAxis"));
        
        XYChart.Series series = new XYChart.Series ();
        series.setName((String) settings.get("series"));
        
        for (Entry entry : entries) {
            series.getData().add(new XYChart.Data(entry.getFirst(), Integer.parseInt(entry.getSecond())));
        }
        
        bc.getData().addAll(series);
    }
    
    public BarChart getChart() {
        return bc;
    }
}
