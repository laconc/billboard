package billboard;

import java.util.HashMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
    private ToggleGroup chartTypeToggle;
    
    @FXML
    private TextField xField;
    
    @FXML
    private TextField yField;
    
    @FXML
    private TextField xAxisField;
    
    @FXML
    private TextField yAxisField;
    
    @FXML
    private TextField seriesField;
    
    @FXML
    private TextField titleField;
    
    HashMap<String,String> tableSettings = new HashMap<>();
    
    @FXML
    void handlePopulateTable(ActionEvent event) {
        tableSettings.put("x", xField.getText());
        tableSettings.put("y", yField.getText());
        tableSettings.put("xAxis", xAxisField.getText());
        tableSettings.put("yAxis", yAxisField.getText());
        tableSettings.put("series", seriesField.getText());
        tableSettings.put("title", titleField.getText());
        
        if (sqlRB.isSelected()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SqlOptions.fxml"));
                Parent sqlParent = (Parent) loader.load();
                SqlOptionsController controller = loader.getController();
                controller.setRoot((Stage) ((Node) event.getSource()).getScene().getWindow());
                controller.setSelectedChart(chartTypeToggle.getSelectedToggle());
                controller.setTableSettings(tableSettings);
                
                Scene sqlScene = new Scene(sqlParent);
                Stage sqlStage = new Stage();
                sqlStage.setScene(sqlScene);
                sqlStage.show();
            } catch(Exception e) {
                   System.out.println("Error" + e);
            }
        }
        else if (csvRB.isSelected()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TableLayout.fxml"));
                Parent tableParent = (Parent) loader.load();
                TableLayoutController controller = loader.getController();
                controller.loadCsv(chartTypeToggle.getSelectedToggle(), tableSettings);

                Scene tableScene = new Scene(tableParent);
                Stage tableStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                tableStage.setScene(tableScene);
                tableStage.show();
            } catch(Exception e) {
                   System.out.println("Error" + e);
            }
        }
    }
}
