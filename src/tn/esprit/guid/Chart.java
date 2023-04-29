/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.guid;

import java.awt.Color;
import java.util.List;
import javax.swing.JFrame;
import tn.esprit.entity.notifications;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import tn.esprit.service.Servicenotifications;

public class Chart {
    private List<notifications> notifications;
    private Servicenotifications Servicebanquedesang;

    public Chart(List<notifications> notify) {
        this.notifications = notify;
    }

    public Chart(Servicenotifications serviceDons) {
        this.Servicebanquedesang = serviceDons;
    }

    public void displayChart() {
        // Create a dataset
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        notifications.forEach((notif) -> {
            int count = 1;
            if (dataset.getValue(notif.getTypesang(), "Type Sang") != null) {
                count = ((Number) dataset.getValue(notif.getTypesang(), "Type Sang")).intValue() + 1;
            }
            dataset.setValue(count, "Type Sang", notif.getTypesang());
        });

        // Create a bar chart
        JFreeChart chart = ChartFactory.createBarChart(
                "Number of Notifications by Type Sang",
                "Type Sang",
                "Number of Notifications",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                true,
                false
        );
        chart.setBackgroundPaint(Color.WHITE);

        // Display the chart in a JFrame
        ChartPanel chartPanel = new ChartPanel(chart);
        JFrame frame = new JFrame("Notifications Chart");
        frame.getContentPane().add(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }

}
