package billboard.models;

import java.util.HashMap;
import javafx.collections.ObservableList;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 *
 * @author vita, dash
 */
public class LineGraph {
    private final LineChart<String, Number> lc;
    
    public LineGraph(HashMap settings, ObservableList<Entry> entries) {
        final CategoryAxis x_Axis = new CategoryAxis();
        final NumberAxis y_Axis = new NumberAxis();
        x_Axis.setLabel((String) settings.get("xAxis"));
        
        lc = new LineChart<>(x_Axis, y_Axis);
        lc.setTitle((String) settings.get("title"));
        
        XYChart.Series series = new XYChart.Series();
        series.setName((String) settings.get("series"));
        
        for (Entry entry : entries) {
            series.getData().add(new XYChart.Data(entry.getFirst(), Double.parseDouble(entry.getSecond())));
        }
        
        lc.getData().add(series); 
    }
    
    public LineChart getChart() {
        return lc;
    }
}
