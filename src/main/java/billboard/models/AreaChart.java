package billboard.models;

import java.util.HashMap;
import javafx.collections.ObservableList;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.XYChart;

/**
 *
 * @author vita, dash
 */
public class AreaChart extends Graph {
    private final StackedAreaChart<String, Number> ac;
    
    public AreaChart(HashMap settings, ObservableList<Entry> entries) {
        final CategoryAxis x_Axis = new CategoryAxis();
        final NumberAxis y_Axis = new NumberAxis(0, 1000, 100);
        ac = 
            new StackedAreaChart<>(x_Axis,y_Axis);
        ac.setTitle((String) settings.get("title"));
 
        XYChart.Series series = new XYChart.Series();
        series.setName((String) settings.get("series"));
        
        for (Entry entry : entries) {
            series.getData().add(new XYChart.Data(entry.getFirst(), Double.parseDouble(entry.getSecond())));
        }
        
        ac.getData().addAll(series);
    }
    
    @Override
    public StackedAreaChart getChart() {
        return ac;
    }
}
