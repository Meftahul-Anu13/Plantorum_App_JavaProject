package com.example.plantorum;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import org.bson.Document;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;
import java.util.function.Consumer;

public class loginController {
    private static final String DB_NAME = "LoginDb";
    private static final String COLLECTION_NAME = "userInfo";

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    @FXML
    private TextField tf_username;

    @FXML
    private PasswordField tf_password;

    @FXML
    private Button button_login;

    @FXML
    private Button button_register;

    private Stage welcomeStage;
    private Consumer<Void> onLoginSuccessCallback;

    public void LoginController() {
        initMongoDB();
    }

    @FXML
    public void initialize() {
        button_login.setOnAction(this::handleLogin);
        button_register.setOnAction(this::handleRegister);
        initMongoDB();
    }

    private void initMongoDB() {
        try {
            mongoClient = MongoClients.create("mongodb://localhost:27017/");
            database = mongoClient.getDatabase("LoginDb");
            collection = database.getCollection("userInfo");

            if (collection == null) {
                throw new IllegalStateException("Collection is null. Ensure the database and collection exist.");
            }
        } catch (Exception e) {
            System.err.println("Error initializing MongoDB: " + e.getMessage());
            e.printStackTrace();
        }
    }


    @FXML
//    private void handleLogin(ActionEvent event) {
//        String username = tf_username.getText();
//        String password = tf_password.getText();
//
//        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017/")) {
//            MongoDatabase database = mongoClient.getDatabase("LoginDb");
//            MongoCollection<Document> collection = database.getCollection("userInfo");
//
//            // Query MongoDB for user credentials
//            Document query = new Document("username", username).append("password", password);
//            Document user = collection.find(query).first();
//
//            if (user != null) {
//                showAlert("Login Successful", "Welcome, " + username + "!");
//
//            } else {
//                showAlert("Login Failed", "Invalid username or password. Please try again.");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            showAlert("Error", "An error occurred while processing your request.");
//        }
//    }
    private void handleLogin(ActionEvent event) {
        String username = tf_username.getText();
        String password = tf_password.getText();

        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017/")) {
            MongoDatabase database = mongoClient.getDatabase("LoginDb");
            MongoCollection<Document> collection = database.getCollection("userInfo");

            // Query MongoDB for user credentials
            Document query = new Document("username", username).append("password", password);
            Document user = collection.find(query).first();

            if (user != null) {
                Optional<ButtonType> result = showAlert("Login Successful", "Welcome, " + username + "!");

                // Check if OK button is pressed
//                if (result.isPresent() && result.get() == ButtonType.OK) {
//                    switchScene(event);
//                }
                //if (result.get() == ButtonType.OK) {
                    switchScene(event);
               // }
            } else {
                showAlert("Login Failed", "Invalid username or password. Please try again.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "An error occurred while processing your request.");
        }
    }

    private void switchScene(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Explore.fxml"));
            Parent exploreView = fxmlLoader.load();
            ListViewDemoController listcontroller= fxmlLoader.getController();
            Stage stage = new Stage();
            stage.setTitle("list Details");
            stage.setScene(new Scene(fxmlLoader.getRoot()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void handleRegister(ActionEvent event) {
        try {
            // Load the FXML file of the registration page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("register.fxml"));
            Parent root = loader.load();

            // Get the stage from the current scene
            welcomeStage.setScene(new Scene(root));
            welcomeStage.setTitle("Anonna");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to switch to the registration page.");
        }
    }


    private Optional<ButtonType> showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
        return null;
    }
    private void switchToListViewDemoScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ListViewDemo.fxml"));
            Parent root = loader.load();

            // Get the stage from the current scene
            Scene currentScene = tf_username.getScene();
            Stage currentStage = (Stage) currentScene.getWindow();

            // Set the new scene
            currentStage.setScene(new Scene(root));

            // Show the new stage
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to switch to the ListViewDemo scene.");
        }
    }

    public void setWelcomeStage(Stage stage) {
        this.welcomeStage = stage;
    }

    public void setOnLoginSuccess(Consumer<Void> onLoginSuccessCallback) {
        this.onLoginSuccessCallback = onLoginSuccessCallback;
    }
}
