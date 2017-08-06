package billboard;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

/**
 *
 * @author vita
 */
public class PieGraph {
    
    final PieChart pc;
    
    public PieGraph() {
        pc = new PieChart();
        ObservableList<PieChart.Data> pieChartData;
        String title = "hello";
        final Label caption = new Label("");
        
        pieChartData = FXCollections.observableArrayList(
            new PieChart.Data("2", 25),
            new PieChart.Data("u", 25),
            new PieChart.Data("5", 15),
            new PieChart.Data("3", 8),
            new PieChart.Data("r", 27));
        caption.setTextFill(Color.DARKORANGE);
        
        pc.setData(pieChartData);
        pc.setTitle(title);
    }
}