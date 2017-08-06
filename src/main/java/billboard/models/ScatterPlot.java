package billboard.models;

import java.util.HashMap;
import javafx.collections.ObservableList;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;

/**
 *
 * @author vita, dash
 */
public class ScatterPlot {
    private final ScatterChart<Number, Number> sc;

    public ScatterPlot(HashMap settings, ObservableList<Entry> entries) {
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        sc = new ScatterChart<>(xAxis, yAxis);
        xAxis.setLabel((String) settings.get("xAxis"));
        yAxis.setLabel((String) settings.get("yAxis"));
        sc.setTitle((String) settings.get("title"));

        XYChart.Series series = new XYChart.Series();
        series.setName((String) settings.get("series"));
        
        for (Entry entry : entries) {
            series.getData().add(new XYChart.Data(
                    Double.parseDouble(entry.getFirst()),
                    Double.parseDouble(entry.getSecond())
            ));
        }

        sc.getData().addAll(series);
    }
    
    public ScatterChart getChart() {
        return sc;
    }
}
