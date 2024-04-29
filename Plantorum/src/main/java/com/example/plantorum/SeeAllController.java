package com.example.plantorum;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import org.bson.Document;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class SeeAllController {
    @FXML
    private ListView<String> listView;
    @FXML
    private Button seeAll;
    private MongoDatabase database;
    private MongoCollection<Document> plantCollection;
    public void initialize(URL location, ResourceBundle resources) {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("anodb");
        plantCollection = database.getCollection("Plants");



        listView.getItems().addAll("Cherry", "lentenrose", "George", "Kramer",
                "Newman", "Morty", "Helen", "Frank", "Estelle");

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
                            hbox.setSpacing(10); // Add spacing between the button and the item name

                            // Create a button
                            Button button = new Button(item);
                            button.setAlignment(Pos.CENTER);
                            button.setPrefWidth(100);
//                            button.setOnAction(event -> {
//                                //System.out.println("Clicked on: " + getItem());
//                                loadPlants(item);
//
//                            });
//                            seeAll.setOnAction(event -> {
//                                handleSearch(event);
//                            });

                            // Create an image view
                            ImageView imageView = new ImageView();
                            try {
                                // Load the image
                                //Image image = new Image(new FileInputStream("D:\\4rth Sem Product\\JavaProject\\Plantora\\src\\main\\Image\\Cherry2.jpg"));
                                Image image=new Image(new FileInputStream("D:\\4rth Sem Product\\JavaProject\\Plantorum\\src\\main\\java\\com\\example\\plantorum\\lentenrose.jpg"));
                                imageView.setImage(image);
                                imageView.setFitWidth(70); // Set the width of the image
                                imageView.setPreserveRatio(true); // Preserve the aspect ratio of the image
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
}
