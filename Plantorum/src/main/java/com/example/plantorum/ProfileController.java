//package com.example.plantorum;
//
//import com.mongodb.client.MongoClient;
//import com.mongodb.client.MongoClients;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoDatabase;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.PasswordField;
//
//import org.bson.Document;
//
//public class ProfileController {
//
//    @FXML
//    private PasswordField currentPasswordField;
//
//    @FXML
//    private PasswordField newPasswordField;
//
//    @FXML
//    private PasswordField confirmPasswordField;
//
//    @FXML
//    private Label firstNameField;
//
//    @FXML
//    private Label lastNameField;
//
//    @FXML
//    private Label emailField;
//
//    @FXML
//    private Button saveChangesButton;
//
//    @FXML
//    private Button cancelChangesButton;
//
//    private MongoClient mongoClient;
//    private MongoDatabase database;
//    private MongoCollection<Document> collection;
//private Document query;
//    public ProfileController(String email, String password) {
//        // Initialize MongoDB connection
//        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017/");
//          database = mongoClient.getDatabase("LoginDb");
//             collection = database.getCollection("userInfo");
//              query = new Document("email", email);
//    }
//
//    @FXML
//    void saveChanges(ActionEvent event) {
//        String currentPassword = currentPasswordField.getText();
//        String newPassword = newPasswordField.getText();
//        String confirmPassword = confirmPasswordField.getText();
//
//        // Check if new password matches the confirmation
//        if (!newPassword.equals(confirmPassword)) {
//            // Display error message or handle mismatch
//            return;
//        }
//
//        // Assuming you have a unique identifier for the user, e.g., email
//        String userEmail = emailField.getText();
//
//        // Query the user document
//        Document query = new Document("email", userEmail);
//        Document user = collection.find(query).first();
//
//        if (user == null) {
//            // User not found, handle error
//            return;
//        }
//
//        // Check if the current password matches the one in the database
//        String storedPassword = user.getString("password");
//
//        if (!currentPassword.equals(storedPassword)) {
//            // Display error message or handle incorrect password
//            return;
//        }
//
//        // Update the password field if new password is provided
//        if (!newPassword.isEmpty()) {
//            user.put("password", newPassword);
//        }
//
//        // Assuming you have other fields to update as well
//        // user.put("firstName", updatedFirstName);
//        // user.put("lastName", updatedLastName);
//
//        // Save the updated user document back to the database
//        collection.replaceOne(query, user);
//
//        // Optionally, update the UI to reflect changes
//    }
//
//    @FXML
//    void cancelChanges(ActionEvent event) {
//        // Handle cancel action, e.g., close the window or clear fields
//    }
//}
package com.example.plantorum;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.bson.Document;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

public class ProfileController {
    @FXML
    private AnchorPane ScenePane;

    @FXML
    private Button button_logout;

    @FXML
    private PasswordField currentPasswordField;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Label firstNameField;

    @FXML
    private Label lastNameField;

    @FXML
    private Label emailField;

    @FXML
    private Button saveChangesButton;
    @FXML
    private ImageView proficon;

    @FXML
    private ImageView sidebg;

    @FXML
    private Button backbtn;

    @FXML
    private Button cancelChangesButton;

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;
    private Document query;
    private String email;
    private  String pass;
@FXML
    public void initialize(String email, String password) {
        // Initialize MongoDB connection
        mongoClient = MongoClients.create("mongodb://localhost:27017/");
        database = mongoClient.getDatabase("LoginDb");
        collection = database.getCollection("userInfo");
        String email2="nai";
        try {
            Image image = new Image(new FileInputStream("D:\\4rth Sem Product\\JavaProject\\Plantorum_App_JavaProject\\Plantorum\\src\\image\\catagoryside.png"));
            sidebg.setImage(image);
            Image img = new Image(new FileInputStream("D:\\4rth Sem Product\\JavaProject\\Plantorum_App_JavaProject\\Plantorum\\src\\image\\myproicon.png"));
            proficon.setImage(img);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
@FXML public void setDAta(String email,String password){
       this.email=email;
       this.pass=password;
    System.out.println(email);
    query = new Document("email", email);
    Document user = collection.find(query).first();
    String name = user.getString("firstName");
    String lastName = user.getString("lastName");
    firstNameField.setText(name);
    lastNameField.setText(lastName);
    emailField.setText(email);
}

    @FXML
    void saveChanges(ActionEvent event) {
        String currentPassword = currentPasswordField.getText();
        String newPassword = newPasswordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        // Check if new password matches the confirmation
        if (!newPassword.equals(confirmPassword)) {
            showAlert("Error", "New password and confirm password do not match.");
            return;
        }

        // Query the user document
        Document user = collection.find(query).first();

        if (user == null) {
            showAlert("Error", "User not found.");
            return;
        }

        // Check if the current password matches the one in the database
        String storedPassword = user.getString("password");

        if (!currentPassword.equals(storedPassword)) {
            showAlert("Error", "Incorrect current password.");
            return;
        }

        // Update the password field if new password is provided
        if (!newPassword.isEmpty()) {
            user.put("password", newPassword);
        }

        // Save the updated user document back to the database
        collection.replaceOne(query, user);

        showAlert("Success", "Profile updated successfully.");

        // Optionally, update the UI to reflect changes
    }
    @FXML
    public void backClick(ActionEvent event) throws IOException {
        try {
            Parent pokedexList = FXMLLoader.load(getClass().getResource("Plant_Details.fxml"));
            Scene scene = new Scene(pokedexList);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Check if the current scene is already the Pokedex_list scene
            if (!stage.getScene().equals(scene)) {
                stage.setScene(scene);
                stage.show();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @FXML
    public void Logout(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("LOGOUT");
        alert.setHeaderText("Are you sure you want to log out?");
        alert.setContentText("Do you want to save your info?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            Stage stage = (Stage) ScenePane.getScene().getWindow();
            System.out.println("You're logged out :( ");
            stage.close();
        }
    }

    @FXML
    void cancelChanges(ActionEvent event) {
        // Handle cancel action, e.g., close the window or clear fields
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
