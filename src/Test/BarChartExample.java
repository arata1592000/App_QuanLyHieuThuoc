package test;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.*;

public class BarChartExample extends ChartPanel {
    public BarChartExample() {
        super(null); // Gọi constructor của lớp cơ sở ChartPanel với tham số là null

        DefaultCategoryDataset dataset = createDataset();

        // Create chart
        JFreeChart chart = ChartFactory.createBarChart(
                "Bar Chart Example",  // Chart title
                "Category",           // X-axis label
                "Value",              // Y-axis label
                dataset,              // Dataset
                PlotOrientation.VERTICAL,  // Orientation: VERTICAL
                true,                 // Show legend
                true,                 // Show tooltips
                false                 // Generate URLs
        );

        // Set chart to this ChartPanel
        setChart(chart);
        setPreferredSize(new Dimension(500, 300));
    }

    private DefaultCategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Add data to the dataset
        dataset.addValue(1.0, "Series 1", "Category 1");
        dataset.addValue(4.0, "Series 1", "Category 2");
        dataset.addValue(3.0, "Series 1", "Category 3");
        dataset.addValue(5.0, "Series 1", "Category 4");
        

        return dataset;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame();
            f.setSize(1000, 1200);
            f.setLayout(new FlowLayout(FlowLayout.LEFT));
            f.add(new BarChartExample());
            f.setVisible(true);
        });
    }
}
