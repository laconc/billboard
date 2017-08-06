/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billboard;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author vita
 */
public class ScatterPlot {

   
    final ScatterChart<Number, Number> sc;

    public ScatterPlot() {

        final NumberAxis xAxis = new NumberAxis(0, 10, 1);
        final NumberAxis yAxis = new NumberAxis(-150, 600, 120);
        sc = new ScatterChart<>(xAxis, yAxis);
        xAxis.setLabel("As)");
        yAxis.setLabel("u");
        sc.setTitle("Y");

        XYChart.Series s1 = new XYChart.Series();
        s1.setName("T");
        
        
        s1.getData().addAll(new XYChart.Data(8.0, 11.8),
            new XYChart.Data(3, 12),
            new XYChart.Data(3, 40),
            new XYChart.Data(11, 270));
        
        XYChart.Series s2 = new XYChart.Series();
        s2.setName("Mutual funds");
        s2.getData().addAll(new XYChart.Data(6, 240),
            new XYChart.Data(35, 60),
            new XYChart.Data(5, 50));
            

        sc.getData().addAll(s1, s2);
    }

}
