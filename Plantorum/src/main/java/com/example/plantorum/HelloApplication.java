//package com.example.plantorum;
//
//import com.mongodb.client.*;
//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//import org.bson.Document;
//
//import java.io.IOException;
//
//public class HelloApplication extends Application {
//
//
//    @Override
//    public void start(Stage stage) throws IOException {
//
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        // FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Explore.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
//
//
//
//
////                // Load the FXML file
////                FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
////                Parent root = loader.load();
////
////                // Set the controller for the FXML file
////                ExploreController controller = loader.getController();
////
////                // Create a Scene with the FXML file as its root
////                Scene scene = new Scene(root, 800, 600);
//
//        // Set the Scene for the Stage
//
//        stage.setScene(scene);
//        stage.setTitle("Plantora - Explore");
//        stage.show();
//    }
//
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}
//
//
//
//
//
package com.example.plantorum;

import com.example.plantorum.loginController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
//        FXMLLoader welcomeLoader = new FXMLLoader(getClass().getResource("Plant_Details.fxml"));
        FXMLLoader welcomeLoader = new FXMLLoader(getClass().getResource("MyProfile.fxml"));
        Parent welcomeRoot = welcomeLoader.load();
        Scene welcomeScene = new Scene(welcomeRoot);
        welcomeScene.getStylesheets().add(getClass().getResource("Profile.css").toExternalForm());
        primaryStage.setScene(welcomeScene);
        primaryStage.setTitle("Welcome Page");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
