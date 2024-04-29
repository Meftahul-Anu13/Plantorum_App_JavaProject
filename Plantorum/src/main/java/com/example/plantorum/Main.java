////package com.example.plantora;
////
////
////import javafx.application.Application;
////import javafx.beans.value.ChangeListener;
////import javafx.beans.value.ObservableValue;
////import javafx.collections.FXCollections;
////import javafx.collections.ObservableList;
////import javafx.event.ActionEvent;
////import javafx.event.EventHandler;
////import javafx.geometry.Orientation;
////import javafx.geometry.Pos;
////import javafx.scene.Scene;
////import javafx.scene.control.Button;
////import javafx.scene.control.ButtonBar;
////import javafx.scene.control.Label;
////import javafx.scene.control.ListView;
////import javafx.scene.control.SelectionMode;
////import javafx.scene.control.TextField;
////import javafx.scene.layout.BorderPane;
////import javafx.scene.layout.HBox;
////import javafx.scene.layout.Priority;
////import javafx.scene.layout.VBox;
////import javafx.scene.text.Font;
////import javafx.scene.text.FontWeight;
////import javafx.stage.Stage;
////
////public class Main extends Application {
////    @Override
////    public void start(Stage primaryStage) {
////        try {
////            primaryStage.setTitle("ListView Demo");
////            BorderPane root = new BorderPane();
////
////
////
////            ObservableList<String> names = FXCollections.observableArrayList("Jerry", "lentenrose", "George", "Kramer",
////                    "Newman", "Morty", "Helen", "Frank", "Estelle");
////
////            ListView<String> listView = new ListView<>(names);
////
////            //listView.setPrefWidth(150);
////            //listView.setPrefHeight(200);
////
////            //listView.getItems().remove("George");
////
////            // listView.getItems().remove(5);
////
////            listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
////                @Override
////                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
////                    System.out.println("Selected item: " + newValue);
////                }
////            });
////
////            root.setCenter(listView);
////
////            Scene scene = new Scene(root);
////            //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
////            primaryStage.setScene(scene);
////            primaryStage.show();
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////    }
////
////    public static void main(String[] args) {
////        launch(args);
////    }
////}
//package com.example.plantorum;
//
//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//
//import java.io.IOException;
//
//
//public class Main extends Application {
//
//    @Override
//    public void start(Stage primaryStage) throws IOException {
//       // FXMLLoader loader = new FXMLLoader(getClass().getResource("Explore.fxml"));
//       //FXMLLoader loader = new FXMLLoader(getClass().getResource("register.fxml"));
//       FXMLLoader loader = new FXMLLoader(getClass().getResource("loginControl.fxml"));
//        Parent root = loader.load();
//        Scene scene = new Scene(root);
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("ListView Demo");
//        primaryStage.show();
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}

//package com.example.plantora;
//
//
//import javafx.application.Application;
//import javafx.beans.value.ChangeListener;
//import javafx.beans.value.ObservableValue;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.geometry.Orientation;
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.ButtonBar;
//import javafx.scene.control.Label;
//import javafx.scene.control.ListView;
//import javafx.scene.control.SelectionMode;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.Priority;
//import javafx.scene.layout.VBox;
//import javafx.scene.text.Font;
//import javafx.scene.text.FontWeight;
//import javafx.stage.Stage;
//
//public class Main extends Application {
//    @Override
//    public void start(Stage primaryStage) {
//        try {
//            primaryStage.setTitle("ListView Demo");
//            BorderPane root = new BorderPane();
//
//
//
//            ObservableList<String> names = FXCollections.observableArrayList("Jerry", "lentenrose", "George", "Kramer",
//                    "Newman", "Morty", "Helen", "Frank", "Estelle");
//
//            ListView<String> listView = new ListView<>(names);
//
//            //listView.setPrefWidth(150);
//            //listView.setPrefHeight(200);
//
//            //listView.getItems().remove("George");
//
//            // listView.getItems().remove(5);
//
//            listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
//                @Override
//                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                    System.out.println("Selected item: " + newValue);
//                }
//            });
//
//            root.setCenter(listView);
//
//            Scene scene = new Scene(root);
//            //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//            primaryStage.setScene(scene);
//            primaryStage.show();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}
/**package com.example.plantorum;

 import javafx.application.Application;
 import javafx.fxml.FXMLLoader;
 import javafx.scene.Parent;
 import javafx.scene.Scene;
 import javafx.stage.Stage;

 import java.io.IOException;

 public class Main extends Application {

@Override
public void start(Stage primaryStage) throws IOException {
FXMLLoader loader = new FXMLLoader(getClass().getResource("Explore.fxml"));
Parent root = loader.load();
Scene scene = new Scene(root);
primaryStage.setScene(scene);
primaryStage.setTitle("ListView Demo");
primaryStage.show();
}

public static void main(String[] args) {
launch(args);
}
}
 */
package com.example.plantorum;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.bson.Document;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        System.out.println("Resource URL: " + getClass().getResource("Welcome.fxml"));
        FXMLLoader welcomeLoader = new FXMLLoader(getClass().getResource("Welcome.fxml"));
        Parent welcomeRoot = welcomeLoader.load();
        System.out.println("Welcome Root: " + welcomeRoot);


        // Set up the controller for the welcome page
        WelcomeController welcomeController = welcomeLoader.getController();
        welcomeController.setLoginStage(primaryStage);

        // Set the scene for the welcome page
        Scene welcomeScene = new Scene(welcomeRoot);
        primaryStage.setScene(welcomeScene);
        primaryStage.setTitle("Welcome Page");
        primaryStage.show();

        if (isUserLoggedIn()) {
            switchToListViewDemoScene(primaryStage);
        }
    }

    // Method to check if a user is already logged in
    public static boolean isUserLoggedIn(String username, String password) {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017/")) {
            MongoDatabase database = mongoClient.getDatabase("LoginDb");
            MongoCollection<Document> collection = database.getCollection("userInfo");

            // Create a query to find the user with the given username and password
            Document query = new Document("username", username)
                    .append("password", password);

            // Find the user document matching the query
            Document userDocument = collection.find(query).first();

            if (userDocument != null) {
                System.out.println("Login successful. Welcome, " + username + "!");
                return true;
            } else {
                System.out.println("Incorrect username or password. Please try again.");
                return false;
            }
        } catch (Exception e) {
            System.err.println("Error during login: " + e);
            return false;
        }
    }


    // Method to switch to the ListViewDemo scene
    private void switchToListViewDemoScene(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SeeAll.fxml"));
            Parent root = loader.load();

            // Set the scene to the ListViewDemo.fxml scene
            Scene listViewScene = new Scene(root);
            primaryStage.setScene(listViewScene);
            primaryStage.setTitle("ListView Demo");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to switch to the ListViewDemo scene.");
        }
    }
    private boolean isUserLoggedIn() {
        // Assuming you have access to the username and password fields in the WelcomeController
        String username = WelcomeController.getUsername();
        String password = WelcomeController.getPassword();

        // Perform database query to check credentials
        boolean loggedIn = false;

        // Perform database query here
        try {
            // Example: Assuming you are using MongoDB
            MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
            MongoDatabase database = mongoClient.getDatabase("loginerjonno");
            MongoCollection<Document> usersCollection = database.getCollection("userInfo");

            Document query = new Document("username", username);
            Document user = usersCollection.find(query).first();

            if (user != null) {
                String storedPassword = user.getString("password");
                // Compare stored password with provided password
                loggedIn = storedPassword.equals(password);
            }
        } catch (MongoException e) {
            e.printStackTrace();
            // Handle database exception
        }

        return loggedIn;
    }

    // Method to show an alert
    private void showAlert(String title, String message) {
        // Implement your alert logic here
    }
}
