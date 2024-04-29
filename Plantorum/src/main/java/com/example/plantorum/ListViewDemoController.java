package com.example.plantorum;
import com.mongodb.client.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HorizontalDirection;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import org.bson.Document;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import  javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ListViewDemoController implements Initializable {

    @FXML
    private ListView<String> listView;
    @FXML
    private TextField searchBar;
    @FXML
    private Button seeAll;
    private MongoDatabase database;
    private MongoCollection<Document> plantCollection;
    private Label label;
    public void listView()
    {
        listView.getItems().addAll("Cherry", "Lenten Rose",
                "Basil","Parsley","Mint","Zinnia");

        listView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> param) {
                return new ListCell<String>() {



                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setGraphic(null);
                        } else {
                            // Create a horizontal box to hold the button and the item name
                            HBox hbox = new HBox();
                            hbox.setAlignment(Pos.CENTER_RIGHT); // Align items to the right
                            hbox.setPrefHeight(10);
                            hbox.setSpacing(10); // Add spacing between the button and the item name

                            // Create a button
                            Button button = new Button(item);
                            button.setAlignment(Pos.CENTER);
                            button.setPrefWidth(100);
                            button.setPrefHeight(45);
                            button.setOnAction(event -> {
                                //System.out.println("Clicked on: " + getItem());
                                loadPlants(item);

                            });
//                            seeAll.setOnAction(event -> {
//                                handleSearch(event);
//                            });

                            // Create an image view
                           String img_url= gettingImage(item);
                            ImageView imageView = new ImageView();
                            try {
                                // Load the image
                              //  Image image = new Image(new FileInputStream("D:\\4rth Sem Product\\JavaProject\\Plantorum\\src\\main\\java\\com\\example\\plantorum\\Cherry2.jpg"));
                             // Image image = new Image(new FileInputStream("D:\\4rth Sem Product\\JavaProject\\Plantorum\\src\\image\\Parsle.jpg");
                                Image image = new Image(new FileInputStream(img_url));

                                imageView.setImage(image);
                                imageView.setFitWidth(70); // Set the width of the image
                                imageView.setPreserveRatio(true); // Preserve the aspect ratio of the image
                                imageView.setFitHeight(100);
                            } catch (FileNotFoundException e) {
                                System.out.println("Unable to locate image file: " + item.toLowerCase() + ".jpg");
                            }

                            // Add the button and the image view to the horizontal box
                            hbox.getChildren().addAll(button, imageView);

                            // Set the horizontal box as the graphic of the list cell
                            setGraphic(hbox);
                        }
                    }

                };
            }
        });
    }
    public String gettingImage(String item){
        String imageUrl = "Cherry2.jpg"; // default image path in case item is not found
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("anodb");
            MongoCollection<Document> collection = database.getCollection("Plants");

            Document query = new Document("name", item);
//write code so that i can fetch image url from the database and collection and return the string to give image path to show
        Document found = collection.find(query).first();

        if (found != null) {
            imageUrl = found.getString("image_url"); // Assuming your document has a field "image_url"
        } else {
            System.out.println("No matching document found for item: " + item);
        }
    } catch (Exception e) {
        System.out.println("Error connecting to MongoDB: " + e.getMessage());
    }
    return imageUrl;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("anodb");
        plantCollection = database.getCollection("Plants");
        listView();

    }
    @FXML
    private void handleSearch(ActionEvent event) {
        String searchText = searchBar.getText();
        // Implement search functionality here
        System.out.println("Search for: " + searchText);
        try {
            Document query = new Document("name", searchText);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PlantsDetails.fxml"));
            // Load the FXML file and create a new stage for the plant details
            fxmlLoader.load();
            PlantsDetailsController controller = fxmlLoader.getController();
            controller.initialize(query);// Initialize the controller with the query

            // Show the new stage
            Stage stage = new Stage();
            stage.setTitle("Plant Details");
            stage.setScene(new Scene(fxmlLoader.getRoot()));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error! ","Your Name is Wrong . Write the Correct Plants name");

        }

    }

    @FXML

    private void loadPlants(String itemName){
        try {
            Document query = new Document("name", itemName);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PlantsDetails.fxml"));
            // Load the FXML file and create a new stage for the plant details
            fxmlLoader.load();
            PlantsDetailsController controller = fxmlLoader.getController();
            controller.initialize(query);// Initialize the controller with the query

            // Show the new stage
            Stage stage = new Stage();
            stage.setTitle("Plant Details");
            stage.setScene(new Scene(fxmlLoader.getRoot()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void handleSeeAllButton(ActionEvent event) {

        try {
            //Document query = new Document("name", searchText);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SeeAllPlants.fxml"));
            // Load the FXML file and create a new stage for the plant details
            fxmlLoader.load();
            ListViewDemoController controller = fxmlLoader.getController();
            //controller.listView();// Initialize the controller with the query

            // Show the new stage
            Stage stage = new Stage();
            stage.setTitle("Plant All");
            stage.setScene(new Scene(fxmlLoader.getRoot()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Implement functionality to show all plants in another scene or stage
        System.out.println("See All button clicked");
    }
    private Optional<ButtonType> showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
        return null;
    }
}

