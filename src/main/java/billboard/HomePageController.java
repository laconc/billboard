package billboard;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

/**
 *
 * @author vita, dash
 */
public class HomePageController {

    @FXML
    private RadioButton sqlRB;

    @FXML
    private RadioButton csvRB;

    @FXML
    private RadioButton barChartRB;

    @FXML
    private RadioButton lineChartRB;

    @FXML
    private RadioButton pieChartRB;

    @FXML
    private RadioButton scatterChartRB;

    @FXML
    private RadioButton areaChartRB;
    
    @FXML
    void handleButtonAction(ActionEvent event) throws IOException {
        if (pieChartRB.isSelected()) {
            try{
                PieGraph pc = new PieGraph();
                Stage pieChartStage = new Stage();
                pieChartStage.setWidth(1000);
                pieChartStage.setHeight(600);
                pieChartStage.setTitle("Billboard: Pie Chart");
                pieChartStage.setScene(new Scene(pc.pc));
                pieChartStage.show();
            } catch(Exception e) {
               System.out.println("Error");
            }
        }
        else if (lineChartRB.isSelected()) {
            try{
                LineGraph lg = new LineGraph();
                Stage lineChartStage = new Stage();
                lineChartStage.setWidth(1000);
                lineChartStage.setHeight(600);
                lineChartStage.setTitle("Billboard: Line Chart");
                lineChartStage.setScene(new Scene(lg.lc));
                lineChartStage.show();
            } catch(Exception e) {
               System.out.println("Error");
            }
        }
        else if (scatterChartRB.isSelected()) {
            try{
                ScatterPlot sp = new ScatterPlot();
                Stage scatterPlotStage = new Stage();
                scatterPlotStage.setHeight(600);
                scatterPlotStage.setWidth(1000);
                scatterPlotStage.setTitle("Billboard: Scatter Plot");
                scatterPlotStage.setScene(new Scene(sp.sc));
                scatterPlotStage.show();
            } catch(Exception e) {
               System.out.println("Error");
            }
        }
        else if (areaChartRB.isSelected()) {
            try{
                AreaChart areaChart = new AreaChart();
                Stage areaChartStage = new Stage();
                areaChartStage.setHeight(600);
                areaChartStage.setWidth(1000);
                areaChartStage.setTitle("Billboard: Area Plot");
                areaChartStage.setScene(new Scene(areaChart.ac));
                areaChartStage.show();
            } catch(Exception e) {
               System.out.println("Error");
            }
        }
        else if (barChartRB.isSelected()) {
            try{
                BarGraph bg = new BarGraph();
                Stage barChartStage = new Stage();
                barChartStage.setHeight(600);
                barChartStage.setWidth(1000);
                barChartStage.setTitle("Billboard: Bar Graph");
                barChartStage.setScene(new Scene(bg.bc));
                barChartStage.show();
            } catch(Exception e) {
               System.out.println("Error");
            }
        }
    }
    
    @FXML
    void handlePopulateTable(ActionEvent event) {
        if (sqlRB.isSelected()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SqlOptions.fxml"));
                Parent sqlParent = (Parent) loader.load();
                SqlOptionsController controller = loader.getController();
                controller.setRoot((Stage) ((Node) event.getSource()).getScene().getWindow());
                
                Scene sqlScene = new Scene(sqlParent);
                Stage sqlStage = new Stage();
                sqlStage.setScene(sqlScene);
                sqlStage.show();
            } catch(Exception e) {
                   System.out.println("Error");
            }
        }
        else if (csvRB.isSelected()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TableLayout.fxml"));
                Parent tableParent = (Parent) loader.load();
                TableLayoutController controller = loader.getController();
                controller.loadCsv();

                Scene tableScene = new Scene(tableParent);
                Stage tableStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                tableStage.setScene(tableScene);
                tableStage.show();
            } catch(Exception e) {
                   System.out.println("Error");
            }
        }
    }
}
