package billboard;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author vita, dash
 */
public class TableLayoutController implements Initializable {
    
    @FXML
    private TableView<Entry> tableView;
    
    @FXML
    private TableColumn<Entry,String> firstCol;

    @FXML
    private TableColumn<Entry,String> secondCol;

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
    public void initialize(URL location, ResourceBundle resources) {}
    
    void loadSql(HashMap settings) {
        
    }
    
    void loadCsv() {
        String testFile = "src/main/resources/test_files/BrandsData.csv";
        
        try {
            CSVReader reader = new CSVReader(new FileReader(testFile));
            List<String[]> entries = reader.readAll();
            populateTable(entries);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
    
    void populateTable(List<String[]> entries) {
        ObservableList<Entry> tableEntries = FXCollections.observableArrayList();
        
        firstCol.setText(entries.get(0)[0]);
        secondCol.setText(entries.get(0)[1]);
        
        for (int i = 1; i < entries.size(); i++) {
            tableEntries.add(new Entry(entries.get(i)[0], entries.get(i)[1]));
        }
        
        firstCol.setCellValueFactory(new PropertyValueFactory<Entry,String>("first"));
        secondCol.setCellValueFactory(new PropertyValueFactory<Entry,String>("second"));
        
        tableView.setItems(tableEntries);
    }
}
