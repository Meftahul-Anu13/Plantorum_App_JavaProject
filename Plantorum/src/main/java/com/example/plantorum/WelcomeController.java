package com.example.plantorum;
/*
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.function.Consumer;

public class WelcomeController {
    @FXML
    private ImageView myImageView;
    private Consumer<Void> onWelcomeButtonClickCallback;

    @FXML
    private Button my_button;

    private Scene loginScene;

    private Stage primaryStage;



    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setLoginScene(Scene loginScene) {
        this.loginScene = loginScene;
    }

    @FXML
    private void handleClick(ActionEvent event) {
        // Close the current stage (WelcomeController)
        primaryStage.close();

        // Show the login scene
        if (loginScene != null) {
            primaryStage.setScene(loginScene);
            primaryStage.show();
        } else {
            System.err.println("Login scene is not set.");
        }
    }

   // private Image myImage = new Image(getClass().getResourceAsStream("C:\\Users\\User\\IdeaProjects\\Plantorum\\src\\image\\Animegach.png"));

    public void initialize() {

        //myImageView.setImage(myImage);
    }

    // Method to handle button click event



    /*@FXML
    private void handleClick(ActionEvent event)
    {

        Platform.runLater(() -> {
            try {
                // Create a new instance of HelloController and start it
                loginController helloController = new loginController();
                helloController.start(new Stage());
                // Close the current stage (HelloQiuz)
                ((Stage) root.getScene().getWindow()).close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }*/



  /*  public void setOnWelcomeButtonClick(Consumer<Void> onWelcomeButtonClickCallback) {
        this.onWelcomeButtonClickCallback = onWelcomeButtonClickCallback;
    }
}
*/

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class WelcomeController {

    private static String username;
    private static String password;



    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

    // Other methods in the controller...


    private Stage loginStage; // Reference to the login stage

    @FXML
    private Button loginButton;
@FXML
        ImageView WelcomView ;

    public void setLoginStage(Stage loginStage) {
        this.loginStage = loginStage;
    }

    @FXML
    private void initialize() {
        // Set up action for the login button
        try {
            // Load the image
            //  Image image = new Image(new FileInputStream("D:\\4rth Sem Product\\JavaProject\\Plantorum\\src\\main\\java\\com\\example\\plantorum\\Cherry2.jpg"));
            // Image image = new Image(new FileInputStream("D:\\4rth Sem Product\\JavaProject\\Plantorum\\src\\image\\Parsle.jpg");
            Image image = new Image(new FileInputStream("D:\\4rth Sem Product\\JavaProject\\Plantorum_App_JavaProject\\Plantorum\\src\\image\\WelcomView.jpg"));

            WelcomView.setImage(image);
//            imageView.setFitWidth(70); // Set the width of the image
//            imageView.setPreserveRatio(true); // Preserve the aspect ratio of the image
//            imageView.setFitHeight(100);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to locate image file: " + ".jpg");
        }
        loginButton.setOnAction(event -> {
            openLoginPage(); // Method to open the login page
        });
    }

    // Method to open the login page
    private void openLoginPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loader.load();


            loginController controller = loader.getController();
            controller.setWelcomeStage(loginStage);

            Scene scene = new Scene(root);
            loginStage.setScene(scene);
            loginStage.setTitle("Login");
            loginStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Handle button click event
    @FXML
    private void handleClick(ActionEvent event) {
        // Add your logic here for handling button clicks
        System.out.println("Button clicked!");
    }
}

