package tn.esprit.guid;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.jfree.chart.ChartPanel;
import tn.esprit.entity.notifications;
import tn.esprit.service.Servicenotifications;

public class ChartFXMLController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Pane chartContainer;

    private Servicenotifications servicenotifications;
    private ObservableList<notifications> donorData;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        servicenotifications = new Servicenotifications();
        donorData = FXCollections.observableArrayList(servicenotifications.afficher());

        displayPieChart();
    }

    private void displayPieChart() {
        // Create a map to store the count of each typesang
        Map<String, Integer> typesangCount = new HashMap<>();
        for (notifications donor : donorData) {
            String typesang = donor.getTypesang();
            typesangCount.put(typesang, typesangCount.getOrDefault(typesang, 0) + 1);
        }

        // Create a dataset with the typesang counts
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (Map.Entry<String, Integer> entry : typesangCount.entrySet()) {
            dataset.setValue(entry.getKey(), entry.getValue());
        }

        // Create the pie chart
        JFreeChart chart = ChartFactory.createPieChart(
                "Typesang Count",
                dataset,
                true,
                true,
                false
        );
        ChartPanel chartPanel = new ChartPanel(chart);

        // Set the size of the chart container and add the chart panel to it
        chartContainer.setPrefSize(500, 500);

    }

}
