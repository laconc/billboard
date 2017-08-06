package billboard.models;

import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

/**
 *
 * @author vita, dash
 */
public class PieGraph {
    private final PieChart pc;
    
    public PieGraph(HashMap settings, ObservableList<Entry> entries) {
        pc = new PieChart();
        ObservableList<PieChart.Data> pieChartData;
        String title = (String) settings.get("title");
        final Label caption = new Label((String) settings.get("series"));
        caption.setTextFill(Color.DARKORANGE);
        
        pieChartData = FXCollections.observableArrayList();
        
        for (Entry entry : entries) {
            pieChartData.add(new PieChart.Data(entry.getFirst(), Double.parseDouble(entry.getSecond())));
        }
        
        pc.setData(pieChartData);
        pc.setTitle(title);
    }
    
    public PieChart getChart() {
        return pc;
    }
}