package billboard;

import billboard.models.AreaChart;
import billboard.models.Entry;
import billboard.models.BarGraph;
import billboard.models.PieGraph;
import billboard.models.LineGraph;
import billboard.models.ScatterPlot;
import com.opencsv.CSVReader;
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

    @FXML
    private RadioButton lineChartRB;

    @FXML
    private RadioButton pieChartRB;

    @FXML
    private RadioButton scatterChartRB;

    @FXML
    private RadioButton areaChartRB;
    
    HashMap<String,String> tableSettings;

    @FXML
    void handleHomeButton(ActionEvent event) {
        try{
            Parent homeParent = FXMLLoader.load(getClass().getResource("/fxml/HomePage.fxml"));
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
        if (pieChartRB.isSelected()) {
            try{
                PieGraph pc = new PieGraph(tableSettings, tableView.getItems());
                Stage pieChartStage = new Stage();
                pieChartStage.setWidth(1000);
                pieChartStage.setHeight(600);
                pieChartStage.setTitle("Billboard: Pie Chart");
                pieChartStage.setScene(new Scene(pc.getChart()));
                pieChartStage.show();
            } catch(Exception e) {
               System.out.println("Error: " + e);
            }
        }
        else if (lineChartRB.isSelected()) {
            try{
                LineGraph lg = new LineGraph(tableSettings, tableView.getItems());
                Stage lineChartStage = new Stage();
                lineChartStage.setWidth(1000);
                lineChartStage.setHeight(600);
                lineChartStage.setTitle("Billboard: Line Chart");
                lineChartStage.setScene(new Scene(lg.getChart()));
                lineChartStage.show();
            } catch(Exception e) {
               System.out.println("Error: " + e);
            }
        }
        else if (scatterChartRB.isSelected()) {
            try{
                ScatterPlot sp = new ScatterPlot(tableSettings, tableView.getItems());
                Stage scatterPlotStage = new Stage();
                scatterPlotStage.setHeight(600);
                scatterPlotStage.setWidth(1000);
                scatterPlotStage.setTitle("Billboard: Scatter Plot");
                scatterPlotStage.setScene(new Scene(sp.getChart()));
                scatterPlotStage.show();
            } catch(Exception e) {
               System.out.println("Error: " + e);
            }
        }
        else if (areaChartRB.isSelected()) {
            try{
                AreaChart areaChart = new AreaChart(tableSettings, tableView.getItems());
                Stage areaChartStage = new Stage();
                areaChartStage.setHeight(600);
                areaChartStage.setWidth(1000);
                areaChartStage.setTitle("Billboard: Area Plot");
                areaChartStage.setScene(new Scene(areaChart.getChart()));
                areaChartStage.show();
            } catch(Exception e) {
               System.out.println("Error: " + e);
            }
        }
        else if (barChartRB.isSelected()) {
            try{
                BarGraph bg = new BarGraph(tableSettings, tableView.getItems());
                Stage barChartStage = new Stage();
                barChartStage.setHeight(600);
                barChartStage.setWidth(1000);
                barChartStage.setTitle("Billboard: Bar Graph");
                barChartStage.setScene(new Scene(bg.getChart()));
                barChartStage.show();
            } catch(Exception e) {
               System.out.println("Error: " + e);
            }
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
    
    void loadCsv(Toggle toggle, HashMap tableSettings) {
        csvRB.setSelected(true);
        setChartType(toggle);
        this.tableSettings = tableSettings;
        
        String testFile = "src/main/resources/test_files/BrandsData.csv";
        
        try {
            CSVReader reader = new CSVReader(new FileReader(testFile));
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
}
