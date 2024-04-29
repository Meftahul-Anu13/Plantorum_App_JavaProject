package com.example.plantorum;
//
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.Button;
//import javafx.scene.control.ScrollPane;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.VBox;
//
//import java.net.URL;
//import java.util.ResourceBundle;
//
//public class ExploreController implements Initializable {
//
//    @FXML
//    private TextField searchBar;
//
//    @FXML
//    private VBox plantsContainer;
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        // Initialize your scene here
//        populatePlantButtons(); // Example method to populate plant buttons
//    }
//
//    // Method to populate plant buttons in the scrollable container
//    private void populatePlantButtons() {
//        // In a real application, you would fetch plant information from your data source
//        // and dynamically create buttons for each plant
//        for (int i = 1; i <= 10; i++) {
//            Button plantButton = createPlantButton("Plant " + i);
//            plantsContainer.getChildren().add(plantButton);
//        }
//    }
//
//    // Method to create a plant button with a background image and action event handler
//    private Button createPlantButton(String plantName) {
//        Button button = new Button();
//        button.setText(plantName);
//        // Set background image for the button (replace "placeholder.jpg" with your image file path)
//       // button.setStyle("-fx-background-image: url('src/main/Image/lentenrose.jpg'); -fx-background-size: cover;");
//        button.setStyle("-fx-background-color: #7e0b82;"); // Green color
//
//        button.setOnAction(event -> {
//            // Handle button click event (navigate to detailed plant information scene)
//            // You can implement this according to your application's navigation logic
//            System.out.println("Clicked on: " + plantName);
//        });
//        return button;
//    }
//}
//

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ExploreController implements Initializable {

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private VBox plantsContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize your scene here
        loadBackgroundImages(); // Example method to load background images for buttons
    }

    // Method to load background images for buttons
    private void loadBackgroundImages() {
        // Load background images for buttons
//        button1.setStyle("-fx-background-image: url('path/to/lenten_rose.jpg');");
//        button2.setStyle("-fx-background-image: url('path/to/marigold.jpg');");
        button1.setStyle("-fx-background-color: #2e093d");
        button2.setStyle("-fx-background-color: #3d3609");
        // Add more buttons and set background images as needed
    }

    // Method to show plant information when a button is clicked
    @FXML
    private void showPlantInfo() {
        // Implement functionality to show plant information
    }
}
