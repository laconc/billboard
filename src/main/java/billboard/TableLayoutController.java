package billboard;

import java.io.IOException;
import java.net.URL;
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
            Parent main_page_parent = FXMLLoader.load(getClass().getResource("/fxml/HomePage.fxml"));
            Scene main_page_scene = new Scene(main_page_parent);
            Stage app_main = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_main.setScene(main_page_scene);
            app_main.show();
        } catch(Exception e){
            System.out.println("Error");
        }
    }
    @FXML
    void handleGenerateChartButton (ActionEvent events) throws IOException {
        if (barChartRB2.isSelected()){
            try{
                Parent barChart_parent = FXMLLoader.load(getClass().getResource("/fxml/BarGraph.fxml"));
                Stage barChart_stage = new Stage();
                barChart_stage.setTitle("Billboard: Bar Chart");
                barChart_stage.setScene(new Scene(barChart_parent));
                barChart_stage.show();
            } catch (Exception f){
               System.out.println("Error");
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //defines each column
//        firstCol.setCellValueFactory(new PropertyValueFactory<>("items"));
//        secondCol.setCellValueFactory(new PropertyValueFactory<>("x"));
//        thirdCol.setCellValueFactory(new PropertyValueFactory<>("y"));
//        fourthCol.setCellValueFactory(new PropertyValueFactory<>("percent"));
//        ObservableList<Items> data = getItems();
//        System.out.println(data);
        
    }
    //populates the list
    private ObservableList<Items> getItems() {
 
      Items row_1 = new Items(1L, "a", 1, 2, 3.0);
      Items row_2 = new Items(2L, "b", 4, 5, 6.0);
      Items row_3 = new Items(3L, "c", 7, 8, 9.0);
 
      ObservableList<Items> list = FXCollections.observableArrayList(row_1, row_2, row_3);
      return list;
  }

}

