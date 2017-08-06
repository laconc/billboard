package billboard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author vita
 */
public class LoginController implements Initializable {
    
    @FXML
    private Label status;
    
    @FXML
    void handleLoginButton(ActionEvent event) throws IOException {
        Parent homeParent = FXMLLoader.load(getClass().getResource("/fxml/Home.fxml"));
        Scene homeScene = new Scene(homeParent);
        Stage homeStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        homeStage.setScene(homeScene);
        homeStage.show();
        
        status.setTextFill(Color.GREEN);
        status.setText("Success");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {}       
}