package billboard;

import billboard.models.AreaChart;
import billboard.models.Entry;
import billboard.models.BarGraph;
import billboard.models.Graph;
import billboard.models.PieGraph;
import billboard.models.LineGraph;
import billboard.models.ScatterPlot;
import com.opencsv.CSVReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
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
    private RadioButton sqlRB;
    
    @FXML
    private ToggleGroup chartTypeToggle;

    @FXML
    private RadioButton csvRB;

    @FXML
    private RadioButton barChartRB;
    
    HashMap<String,String> tableSettings;

    @FXML
    void handleHomeButton(ActionEvent event) {
        try{
            Parent homeParent = FXMLLoader.load(getClass().getResource("/fxml/Home.fxml"));
            Scene homeScene = new Scene(homeParent);
            Stage homeStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            homeStage.setScene(homeScene);
            homeStage.show();
        } catch(IOException e) {
            System.out.println("Error: " + e);
        }
    }

    @FXML
    void handleGenerateChartButton (ActionEvent event) throws IOException {
        switch(((RadioButton) chartTypeToggle.getSelectedToggle()).getId()) {
            case "pieChartRB":
                displayChart(new PieGraph(tableSettings, tableView.getItems()));
                break;
            case "lineChartRB":
                displayChart(new LineGraph(tableSettings, tableView.getItems()));
                break;
            case "scatterChartRB":
                displayChart(new ScatterPlot(tableSettings, tableView.getItems()));
                break;
            case "areaChartRB":
                displayChart(new AreaChart(tableSettings, tableView.getItems()));
                break;
            case "barChartRB":
                displayChart(new BarGraph(tableSettings, tableView.getItems()));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {}
    
    void loadSql(HashMap settings, Toggle toggle, HashMap tableSettings) {
        sqlRB.setSelected(true);
        setChartType(toggle);
        this.tableSettings = tableSettings;
        
        Connection c = null;
        String connString = "jdbc:postgresql://" + settings.get("server") + ":" + settings.get("port") + "/" + settings.get("db");
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection(connString, (String) settings.get("user"), (String) settings.get("pass"));
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        
        Statement stmt;
        List<String[]> entries = new ArrayList<>();
        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM " + (String) settings.get("table") + ";" );
            entries.add(new String[] {
                rs.getMetaData().getColumnName(2),
                rs.getMetaData().getColumnName(3)
            });
            
            while ( rs.next() ) {
                entries.add(new String[] {
                    rs.getString(2),
                    rs.getString(3)
                });
            }
         rs.close();
         stmt.close();
         c.close();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        
        populateTable(entries);
    }
    
    void loadCsv(File file, Toggle toggle, HashMap tableSettings) {
        csvRB.setSelected(true);
        setChartType(toggle);
        this.tableSettings = tableSettings;
        
        try {
            CSVReader reader = new CSVReader(new FileReader(file));
            List<String[]> entries = reader.readAll();
            populateTable(entries);
        } catch (IOException e) {
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
    
    void setChartType(Toggle toggle) {
        Toggle selectedToggle = barChartRB;
        for (Toggle t : chartTypeToggle.getToggles()) {
            if (((RadioButton) t).getId().equals(((RadioButton) toggle).getId())) {
                selectedToggle = t;
            }
        }
        chartTypeToggle.selectToggle(selectedToggle);
    }
    
    void displayChart(Graph chart) {
        Stage chartStage = new Stage();
        chartStage.setHeight(600);
        chartStage.setWidth(1000);
        chartStage.setTitle("Billboard: Graph");
        chartStage.setScene(new Scene(chart.getChart()));
        chartStage.show();
    }
}
