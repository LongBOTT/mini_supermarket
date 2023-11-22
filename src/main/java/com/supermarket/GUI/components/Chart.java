package com.supermarket.GUI.components;

import javafx.geometry.Bounds;
import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.Map;

public class Chart<X, Y> extends BarChart<X, Y> {

    private Map<XYChart.Data<X, Y>, Text> dataLabels;

    public Chart(Axis axis, Axis axis1) {
        super(axis, axis1);
        this.dataLabels = new HashMap<>();
    }

    protected void layoutPlotChildren() {
        super.layoutPlotChildren();

        for (XYChart.Series<X, Y> series : getData()) {
            for (XYChart.Data<X, Y> data : series.getData()) {
                if (data.getNode() != null) {
                    showLabelForData(data);
                }
            }
        }
    }

    private void showLabelForData(XYChart.Data<X, Y> data) {
        Bounds bounds = data.getNode().getBoundsInParent();
        double xPos = bounds.getMinX();
        double yPos = bounds.getMinY() - 15; // Adjust the position as needed

        if (dataLabels.containsKey(data)) {
            updateLabel(data, xPos, yPos);
        } else {
            createLabel(data, xPos, yPos);
        }
    }

    private void createLabel(XYChart.Data<X, Y> data, double xPos, double yPos) {
        Text dataText = new Text(data.getYValue() + "");
        dataText.setTextOrigin(javafx.geometry.VPos.TOP);
        dataText.setLayoutX(xPos);
        dataText.setLayoutY(yPos);

        dataLabels.put(data, dataText);
        getPlotChildren().add(dataText);
    }

    private void updateLabel(XYChart.Data<X, Y> data, double xPos, double yPos) {
        Text dataText = dataLabels.get(data);
        dataText.setLayoutX(xPos);
        dataText.setLayoutY(yPos);
    }
}
