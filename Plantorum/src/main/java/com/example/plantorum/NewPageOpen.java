//package com.example.plantorum;
//
//import javafx.scene.Scene;
//import javafx.scene.control.Label;
//import javafx.scene.layout.StackPane;
//import javafx.stage.Stage;
//
//public class NewPageOpen{
//
//    private String userData;
//
//    // Method to initialize data for the new page
//    public void initData(String userData) {
//        this.userData = userData;
//    }
//    public void show(Stage primaryStage) {
//
//        Label userDataLabel = new Label("User Data: " + userData);
//
//        // Create a layout (StackPane) to arrange the elements
//        StackPane root = new StackPane();
//        root.getChildren().add(userDataLabel);
//
//        // Create the scene and set it in the stage
//        Scene scene = new Scene(root, 300, 200);
//        primaryStage.setScene(scene);
//
//        // Set the title of the window
//        primaryStage.setTitle("New Page");
//
//        // Show the window
//        primaryStage.show();
//    }
//}
//
