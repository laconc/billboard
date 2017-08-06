package billboard;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dash
 */
public class SqlOptionsController implements Initializable {
    
    private Stage rootStage;
    
    @FXML
    public TextField serverField;
    
    @FXML
    public TextField portField;
    
    @FXML
    public TextField dbField;
    
    @FXML
    public TextField tableField;
    
    @FXML
    public TextField userField;
    
    @FXML
    public TextField passField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {}
    
    @FXML
    void handleLoadButton(ActionEvent event) {
        HashMap<String,String> settings = new HashMap<>();
        settings.put("server", serverField.getText());
        settings.put("port", portField.getText());
        settings.put("db", dbField.getText());
        settings.put("table", tableField.getText());
        settings.put("user", userField.getText());
        settings.put("pass", passField.getText());
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TableLayout.fxml"));
            Parent tableParent = (Parent) loader.load();
            TableLayoutController controller = loader.getController();
            controller.loadSql(settings);
            
            Scene tableScene = new Scene(tableParent);
            rootStage.setScene(tableScene);
            rootStage.show();
            close(event);
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }
    
    @FXML
    void handleCancelButton(ActionEvent event) {
        close(event);
    }
    
    void close(ActionEvent event) {
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    
    void setRoot(Stage stage) {
        rootStage = stage;
    }
}
