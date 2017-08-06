package billboard;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dash
 */
public class SqlOptionsController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {}
    
    @FXML
    void handleLoadButton(ActionEvent event) {
        close(event);
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
}
