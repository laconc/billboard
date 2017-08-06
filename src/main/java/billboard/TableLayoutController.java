package billboard;

import com.opencsv.CSVReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 *
 * @author vita, dash
 */
public class TableLayoutController implements Initializable {
    
    @FXML
    private TableView<Items> tableView;
    
    @FXML
    private TableColumn<Items, String> firstCol;

    @FXML
    private TableColumn<Items, Integer> secondCol;

    @FXML
    private TableColumn<Items, Integer> thirdCol;

    @FXML
    private TableColumn<Items, Integer> fourthCol;

    @FXML
    private Button homeButton;

    @FXML
    private Button generateChartButton;
    
    @FXML
    private RadioButton barChartRB2;

    @FXML
    void handleHomeButton(ActionEvent event) {
        try{
            Parent homeParent = FXMLLoader.load(getClass().getResource("/fxml/HomePage.fxml"));
            Scene homeScene = new Scene(homeParent);
            Stage homeStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            homeStage.setScene(homeScene);
            homeStage.show();
        } catch(Exception e){
            System.out.println("Error" + e);
        }
    }

    @FXML
    void handleGenerateChartButton (ActionEvent events) throws IOException {
        if (barChartRB2.isSelected()){
            try{
                Parent barChartParent = FXMLLoader.load(getClass().getResource("/fxml/BarGraph.fxml"));
                Stage barChartStage = new Stage();
                barChartStage.setTitle("Billboard: Bar Chart");
                barChartStage.setScene(new Scene(barChartParent));
                barChartStage.show();
            } catch (Exception e){
               System.out.println("Error: " + e);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadCsv();
    }
    
    void loadCsv() {
        String testFile = "src/main/resources/test_files/BrandsData.csv";
        
        try {
            CSVReader reader = new CSVReader(new FileReader(testFile));
            List<String[]> entries = reader.readAll();
//            System.out.println(entries.get(0)[0]);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
