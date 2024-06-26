//package com.example.plantorum;
//
//import com.mongodb.client.MongoClients;
//import com.mongodb.client.MongoClient;
//import com.mongodb.client.MongoDatabase;
//import com.mongodb.client.MongoCollection;
//import javafx.application.Application;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.Node;
//import javafx.scene.control.*;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import org.bson.Document;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Arrays;
//import java.util.Objects;
//import java.util.Optional;
//import java.util.function.Consumer;
//
//public class loginController  {
//    private static final String DB_NAME = "LoginDb";
//    private static final String COLLECTION_NAME = "userInfo";
//
//    private MongoClient mongoClient;
//    private MongoDatabase database;
//    private MongoCollection<Document> collection;
//
//    @FXML
//    private TextField tf_username;
//
//    @FXML
//    private PasswordField tf_password;
//
//    @FXML
//    private Button button_login;
//
//    @FXML
//    private Button button_register;
//
//    private Stage welcomeStage;
//    private Stage PlantsStage;
//    private Consumer<Void> onLoginSuccessCallback;
//    @FXML
//    ImageView loginView;
//
//    public void LoginController() {
//        initMongoDB();
//
//
//    }
//
//
//    public void initialize() {
//        try {
////            // Load the image
//                //Image image = new Image(new FileInputStream("D:\\4rth Sem Product\\JavaProject\\Plantorum\\src\\image\\Animegach.png"));
//            //  Image image = new Image(new FileInputStream("D:\\4rth Sem Product\\JavaProject\\Plantorum\\src\\main\\java\\com\\example\\plantorum\\Cherry2.jpg"));
//            // Image image = new Image(new FileInputStream("D:\\4rth Sem Product\\JavaProject\\Plantorum\\src\\image\\Parsle.jpg");
//            Image image = new Image(new FileInputStream("D:\\4rth Sem Product\\JavaProject\\Plantorum_App_JavaProject\\Plantorum\\src\\image\\PlantorumLogin.jpg"));
//
//            loginView.setImage(image);
////            loginView.setFitWidth(70); // Set the width of the image
////            loginView.setPreserveRatio(true); // Preserve the aspect ratio of the image
////            loginView.setFitHeight(100);
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to locate image file: " + ".jpg");
//        }
////
//        //loginView.setImage(image);
//        button_login.setOnAction(this::handleLogin);
//        button_register.setOnAction(this::handleRegister);
//        initMongoDB();
//        // Set the loaded image to the ImageView
//    }
//
//
//    private void initMongoDB() {
//        try {
//            mongoClient = MongoClients.create("mongodb://localhost:27017/");
//            database = mongoClient.getDatabase("LoginDb");
//            collection = database.getCollection("userInfo");
//
//            if (collection == null) {
//                throw new IllegalStateException("Collection is null. Ensure the database and collection exist.");
//            }
//        } catch (Exception e) {
//            System.err.println("Error initializing MongoDB: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//
//    @FXML
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
//                Optional<ButtonType> result = showAlert("Login Successful", "Welcome, " + username + "!");
//                    switchScene(event);
//                    PlantController profileController = new PlantController();
//                    profileController.getEmail(username,password);
//
//               // }
//            } else {
//                showAlert("Login Failed", "Invalid username or password. Please try again.");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            //showAlert("Error", "An error occurred while processing your request.");
//        }
//    }
//
//
////@FXML
////public void handleLogin(ActionEvent event) {
////    String usernameOrEmail = tf_username.getText();
////    String password = tf_password.getText();
////
////    try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017/")) {
////        MongoDatabase database = mongoClient.getDatabase("LoginDb");
////        MongoCollection<Document> collection = database.getCollection("userInfo");
////
////        // Query MongoDB for user credentials
////        Document query = new Document("$or", Arrays.asList(
////                new Document("username", usernameOrEmail),
////                new Document("email", usernameOrEmail)
////        )).append("password", password);
////
////        Document user = collection.find(query).first();
////
////        if (user != null) {
////            Optional<ButtonType> result = showAlert("Login Successful", "Welcome, " + user.getString("username") + "!");
////
////            if (result.isPresent() && result.get() == ButtonType.OK) {
////                ProfileController profileController = new ProfileController();
////                profileController.initialize(usernameOrEmail,password);
////                switchScene(event);
////            }
////        } else {
////            showAlert("Login Failed", "Invalid username/email or password. Please try again.");
////        }
////    } catch (Exception e) {
////        e.printStackTrace();
////        showAlert("Error", "An error occurred while processing your request.");
////    }
////}
//@FXML
//    private void switchScene(ActionEvent event) {
//
//        try {
//            FXMLLoader welcomeLoader = new FXMLLoader(getClass().getResource("Plant_Details.fxml"));
//            Parent welcomeRoot = welcomeLoader.load();
//
//
//            Scene welcomeScene = new Scene(welcomeRoot);
//            welcomeScene.getStylesheets().add(getClass().getResource("sylesheet.css").toExternalForm());
//            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            stage.sizeToScene();
//            stage.setResizable(false);
//            stage.setTitle("Plant Details");
//            stage.setScene(welcomeScene);
//            stage.show();
////            PlantsStage.setScene(welcomeScene);
////            PlantsStage.setTitle("Welcome Page");
////            PlantsStage.show();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//    @FXML
//    private void handleRegister(ActionEvent event) {
//        try {
//            // Load the FXML file of the registration page
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("register.fxml"));
//            Parent root = loader.load();
//
//            // Get the stage from the current scene
//            welcomeStage.setScene(new Scene(root));
//            welcomeStage.setTitle("Anonna");
//        } catch (IOException e) {
//            e.printStackTrace();
//            showAlert("Error", "Failed to switch to the registration page.");
//        }
//    }
//
//
//    private Optional<ButtonType> showAlert(String title, String message) {
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle(title);
//        alert.setHeaderText(null);
//        alert.setContentText(message);
//        alert.showAndWait();
//        return null;
//    }
//    private void switchToListViewDemoScene() {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("ListViewDemo.fxml"));
//            Parent root = loader.load();
//
//            // Get the stage from the current scene
//            Scene currentScene = tf_username.getScene();
//            Stage currentStage = (Stage) currentScene.getWindow();
//
//            // Set the new scene
//            currentStage.setScene(new Scene(root));
//
//            // Show the new stage
//            currentStage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//            showAlert("Error", "Failed to switch to the ListViewDemo scene.");
//        }
//    }
//
//    public void setWelcomeStage(Stage stage) {
//        this.welcomeStage = stage;
//    }
//
//    public void setOnLoginSuccess(Consumer<Void> onLoginSuccessCallback) {
//        this.onLoginSuccessCallback = onLoginSuccessCallback;
//    }
//}
package com.example.plantorum;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.bson.Document;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
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
    @FXML
    ImageView loginView;

    public void LoginController() {
        initMongoDB();


    }


    public void initialize() {
        try {
//            // Load the image
            //Image image = new Image(new FileInputStream("D:\\4rth Sem Product\\JavaProject\\Plantorum\\src\\image\\Animegach.png"));
            //  Image image = new Image(new FileInputStream("D:\\4rth Sem Product\\JavaProject\\Plantorum\\src\\main\\java\\com\\example\\plantorum\\Cherry2.jpg"));
            // Image image = new Image(new FileInputStream("D:\\4rth Sem Product\\JavaProject\\Plantorum\\src\\image\\Parsle.jpg");
            Image image = new Image(new FileInputStream("D:\\4rth Sem Product\\JavaProject\\Plantorum_App_JavaProject\\Plantorum\\src\\image\\PlantorumLogin.jpg"));

            loginView.setImage(image);
//            loginView.setFitWidth(70); // Set the width of the image
//            loginView.setPreserveRatio(true); // Preserve the aspect ratio of the image
//            loginView.setFitHeight(100);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to locate image file: " + ".jpg");
        }
//
        //loginView.setImage(image);
        button_login.setOnAction(this::handleLogin);
        button_register.setOnAction(this::handleRegister);
        initMongoDB();
        // Set the loaded image to the ImageView
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
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Explore.fxml"));
//            Parent exploreView = fxmlLoader.load();
//            ListViewDemoController listcontroller= fxmlLoader.getController();
//            Stage stage = new Stage();
//            stage.setTitle("list Details");
//            stage.setScene(new Scene(fxmlLoader.getRoot()));
//            stage.show();

            Parent root = FXMLLoader.load(getClass().getResource("Plant_Details.fxml"));
            // ListViewDemoController listcontroller= root.getController();

            // Create a new scene
            Scene scene = new Scene(root);

            // Get the stage information
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the scene and show the stage
            stage.setScene(scene);
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
