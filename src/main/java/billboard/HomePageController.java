package billboard;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
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
    void handlePopulateTable(ActionEvent event) {
        if (sqlRB.isSelected()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SqlOptions.fxml"));
                Parent sqlParent = (Parent) loader.load();
                SqlOptionsController controller = loader.getController();
                controller.setRoot((Stage) ((Node) event.getSource()).getScene().getWindow());
                controller.setSelectedChart(chartTypeToggle.getSelectedToggle());
                
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
                controller.loadCsv(chartTypeToggle.getSelectedToggle());

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
