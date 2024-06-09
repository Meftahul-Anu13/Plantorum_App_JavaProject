package com.example.plantorum;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.bson.Document;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Window;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class registerController implements Initializable {

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> usersCollection;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField email;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private DatePicker DateOfBirth;

    @FXML
    private TextField confirmPassword;

    @FXML
    private Button registerButton;

    private Window window;
    @FXML
    private ImageView regibg;

    @FXML
    private Button backbtn;


    @Override
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        RegisterController(); // Call the constructor to initialize usersCollection
        registerButton.setOnAction(this::handleRegisterButtonAction);
       // Image image = new Image(new FileInputStream("D:\\4rth Sem Product\\JavaProject\\Plantorum_App_JavaProject\\Plantorum\\src\\image\\registerbg.jpeg"));

    }

    @FXML
    public void handleRegisterButtonAction(ActionEvent event) {
        String firstNameText = firstName.getText();
        String lastNameText = lastName.getText();
        String dob = DateOfBirth.getValue().toString();
        String emailText = email.getText();
        String pass = password.getText();

        if (firstNameText.isEmpty() || lastNameText.isEmpty() || dob.isEmpty() || emailText.isEmpty() || pass.isEmpty()) {
            showAlert("Warning", "Please fill in all fields.");
            return;
        }

        if (firstNameText.length() > 20) {
            showAlert("Warning", "First name cannot exceed 20 characters.");
            return;
        }

        if (pass.length() < 6) {
            showAlert("Warning", "Password must be more than 6 characters");
            return;
        }

        // Inserting user information into MongoDB collection
        Document userDocument = new Document("firstName", firstNameText)
                .append("lastName", lastNameText)
                .append("dateOfBirth", dob)
                .append("email", emailText)
                .append("username", username.getText())
                .append("password", pass);

        usersCollection.insertOne(userDocument);

        switchToLoginPage();
    }

    private void switchToLoginPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/plantorum/login.fxml"));
            Parent root = loader.load();
            if (root == null) {
                showAlert("Error", "Failed to load the login page: FXML file is null");
                return;
            }
            username.getScene().setRoot(root);
            // Update here to use the username TextField

        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load the login page: " + e.getMessage());
        }

    }

    // Method to show alert message
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    public void backClick(ActionEvent event) throws IOException {
        try {
            Parent pokedexList = FXMLLoader.load(getClass().getResource("login.fxml"));
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

    public void RegisterController() {
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("LoginDb");
        usersCollection = database.getCollection("userInfo");
    }
}
