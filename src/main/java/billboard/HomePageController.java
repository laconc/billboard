package billboard;

import java.io.IOException;
import java.util.HashMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class HomePageController {

    @FXML
    private SplitPane mainPageView;
    
     @FXML
    private GridPane gridOptions;

    @FXML
    private RadioButton sqlRB;

    @FXML
    private ToggleGroup dataBaseToggle;

    @FXML
    private RadioButton csvRB;

    @FXML
    private RadioButton barChartRB;

    @FXML
    private ToggleGroup chartTypeToggle;

    @FXML
    private RadioButton lineChartRB;

    @FXML
    private RadioButton pieChartRB;

    @FXML
    private RadioButton scatterChartRB;

    @FXML
    private RadioButton areaChartRB;

    @FXML
    private Button createGraphButton;
    
    @FXML
    private Button populateTable;
    
    @FXML
    void handleButtonAction(ActionEvent event) throws IOException {
        
        if (pieChartRB.isSelected()) {
            try{
                PieGraph pc = new PieGraph();
                Stage lineChart_stage = new Stage();
                lineChart_stage.setWidth(1000);
                lineChart_stage.setHeight(600);
                lineChart_stage.setTitle("Billboard: Pie Chart");
                lineChart_stage.setScene(new Scene(pc.pc));
                lineChart_stage.show();
            } catch(Exception e) {
               System.out.println("Error");
            }
        }
        else if (lineChartRB.isSelected()) {
            try{
                LineGraph lg = new LineGraph();
                Stage lineChart_stage = new Stage();
                lineChart_stage.setWidth(1000);
                lineChart_stage.setHeight(600);
                lineChart_stage.setTitle("Billboard: Line Chart");
                lineChart_stage.setScene(new Scene(lg.lc));
                lineChart_stage.show();
            } catch(Exception e) {
               System.out.println("Error");
            }
        }
        else if (scatterChartRB.isSelected()) {
            try{
                ScatterPlot sp = new ScatterPlot();
                Stage scatterPlot_stage = new Stage();
                scatterPlot_stage.setHeight(600);
                scatterPlot_stage.setWidth(1000);
                scatterPlot_stage.setTitle("Billboard: Scatter Plot");
                scatterPlot_stage.setScene(new Scene(sp.sc));
                scatterPlot_stage.show();
            } catch(Exception e) {
               System.out.println("Error");
            }
        }
        else if (areaChartRB.isSelected()) {
            try{
                AreaChart areaChart = new AreaChart();
                Stage areaChar_stage = new Stage();
                areaChar_stage.setHeight(600);
                areaChar_stage.setWidth(1000);
                areaChar_stage.setTitle("Billboard: Area Plot");
                areaChar_stage.setScene(new Scene(areaChart.ac));
                areaChar_stage.show();
            } catch(Exception e) {
               System.out.println("Error");
            }
        }
        else if (barChartRB.isSelected()) {
            try{
                BarGraph bg = new BarGraph();
                Stage barChar_stage = new Stage();
                barChar_stage.setHeight(600);
                barChar_stage.setWidth(1000);
                barChar_stage.setTitle("Billboard: Bar Graph");
                barChar_stage.setScene(new Scene(bg.bc));
                barChar_stage.show();
            } catch(Exception e) {
               System.out.println("Error");
            }
        }
    }
    
    @FXML
    void handlePopulateTable(ActionEvent event) {
        if (sqlRB.isSelected()) {
            try {
                Parent sqlPage = FXMLLoader.load(getClass().getResource("/fxml/SqlOptions.fxml"));
                Scene sqlScene = new Scene(sqlPage);
                Stage sqlStage = new Stage();
                sqlStage.setScene(sqlScene);
                sqlStage.show();
            } catch(Exception e) {
                   System.out.println("Error");
            }
        }
        else if (csvRB.isSelected()) {
            loadTable(event, null);
        }
    }
    
    void loadTable(ActionEvent event, HashMap options) {
        try {
            Parent table_page_parent = FXMLLoader.load(getClass().getResource("/fxml/TableLayout.fxml"));
            Scene table_page_scene = new Scene(table_page_parent);
            Stage app_table = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_table.setScene(table_page_scene);
            app_table.show();
        } catch(Exception e) {
               System.out.println("Error");
        }
    }
}
