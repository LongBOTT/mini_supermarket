package com.supermarket.GUI;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
public class JFXPanelExample {
    public static void main(String[] args) {
            JFrame frame = new JFrame("JFXPanel Example");
        SwingUtilities.invokeLater(() -> {
            // Create a JFrame to hold the JavaFX content
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);/*w   w  w .  d   e   m o 2    s  . c o   m */

            // Create a JPanel to hold the JFXPanel
            JPanel panel = new JPanel();

            // Create a JFXPanel
            JFXPanel jfxPanel = new JFXPanel();

            // Create a JavaFX Pane to hold JavaFX components
            Pane javafxPane = new Pane();

            // Create a JavaFX Button and add it to the Pane
            Button javafxButton = new Button("JavaFX Button");
            javafxButton.setLayoutX(50);
            javafxButton.setLayoutY(50);
            javafxPane.getChildren().add(javafxButton);

            // Create a JavaFX Scene and set the Pane as its root
            Scene scene = new Scene(javafxPane);

            // Set the JavaFX Scene to the JFXPanel
            jfxPanel.setScene(scene);

            // Add the JFXPanel to the JPanel
            panel.add(jfxPanel);

            // Add the JPanel to the JFrame
            frame.add(panel);

            // Make the JFrame visible
            frame.setVisible(true);
        });
        frame.removeAll();
        SwingUtilities.invokeLater(() -> {
            // Create a JFrame to hold the JavaFX content
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);/*w   w  w .  d   e   m o 2    s  . c o   m */

            // Create a JPanel to hold the JFXPanel
            JPanel panel = new JPanel();

            // Create a JFXPanel
            JFXPanel jfxPanel = new JFXPanel();

            // Create a JavaFX Pane to hold JavaFX components
            Pane javafxPane = new Pane();

            // Create a JavaFX Button and add it to the Pane
            Button javafxButton = new Button("s Button");
            javafxButton.setLayoutX(50);
            javafxButton.setLayoutY(50);
            javafxPane.getChildren().add(javafxButton);

            // Create a JavaFX Scene and set the Pane as its root
            Scene scene = new Scene(javafxPane);

            // Set the JavaFX Scene to the JFXPanel
            jfxPanel.setScene(scene);

            // Add the JFXPanel to the JPanel
            panel.add(jfxPanel);

            // Add the JPanel to the JFrame
            frame.add(panel);

            // Make the JFrame visible
            frame.setVisible(true);
        });
    }
}
