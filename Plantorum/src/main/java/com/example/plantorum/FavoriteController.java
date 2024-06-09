package com.example.plantorum;



import com.mongodb.client.model.Filters;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.bson.Document;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;


import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.security.cert.PolicyNode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class FavoriteController {

    @FXML
    private Label favLabel;
    @FXML
    private VBox vBut;
    @FXML
    private ImageView image;

    // Define MongoDB variables
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> favoriteCollection;
    private MongoCollection<Document> plantCollections;


    @FXML
    private GridPane postGrid;


    List<String> pokemonNames = new ArrayList<>();
    List<String> pokemonImageUrls = new ArrayList<>();
    @FXML private AnchorPane rootPane;
    @FXML private ImageView image2;

    @FXML
    public void initialize() {
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("anodb");
        favoriteCollection = database.getCollection("Favorite");
        List<String> pokemonNames = new ArrayList<>();
        List<String> pokemonImage = new ArrayList<>();

        int columns = 0;
        int rows = 1;

        for (Document document : favoriteCollection.find()) {
            pokemonNames.add(document.getString("name"));
        }
        for (Document document : favoriteCollection.find()) {
            pokemonImage.add(document.getString("image_url"));
        }
        for (int i = 0; i < pokemonNames.size(); i++) {
            System.out.println("poko" + pokemonNames.get(i));
        }


        for (int i = 0; i < pokemonNames.size(); i++) {
            String name = pokemonNames.get(i);
            String imageUrl = pokemonImage.get(i);

            // Create AnchorPane for each Pokemon
            AnchorPane anchorPane = new AnchorPane();
            anchorPane.setPrefHeight(150);
            anchorPane.setPrefWidth(250);
            anchorPane.getStyleClass().add("screen3");
            anchorPane.getStyleClass().add("grass-background");

            // Add Label for Pokemon name
            Label pokemonNameLabel = new Label(name);
//            pokemonNameLabel.setStyle("-fx-font-weight: bold; " +
//                    "-fx-text-fill: black; " +
//                    "-fx-background-color: rgba(255, 255, 255, 0.6); " +
//                    "-fx-background-radius: 15 15 15 15; " +
//                    "-fx-font-family: 'Arial Rounded MT Bold'; " +
//                    "-fx-font-size: 20;");
            pokemonNameLabel.getStyleClass().add("glass");
//            AnchorPane.setTopAnchor(pokemonNameLabel, 40.0);
//            AnchorPane.setLeftAnchor(pokemonNameLabel, 15.0);
            pokemonNameLabel.setStyle("-fx-font-family: 'Arial Rounded MT Bold'");

            pokemonNameLabel.setTextFill(Color.BLACK);
            pokemonNameLabel.setUnderline(true);
            pokemonNameLabel.setPrefHeight(34);
            pokemonNameLabel.setPrefWidth(250);
            pokemonNameLabel.setLayoutX(71);
            pokemonNameLabel.setLayoutY(14);
            pokemonNameLabel.setAlignment(Pos.CENTER);
            anchorPane.getChildren().add(pokemonNameLabel);

            // Add ImageView for Pokemon image
            ImageView imageView = new ImageView();
            imageView.setLayoutX(81);
            imageView.setLayoutY(61);
            imageView.setFitWidth(250);
            imageView.setFitHeight(150);

            imageView.setPreserveRatio(true);
            if (imageUrl != null && !imageUrl.isEmpty()) {
                try {
                    Image image = new Image(new FileInputStream(imageUrl));
                    imageView.setImage(image);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            anchorPane.getChildren().add(imageView);

            // Add Button
            Button button = new Button();
            button.setPrefHeight(23);
            button.setPrefWidth(99);
            button.setLayoutX(237);
            button.setLayoutY(219);
            button.setText("X");
            button.getStyleClass().add("button-forward");
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    // Call the method or perform the action when the button is clicked
                    buttonClickforRemove(name);
                }
            });

            anchorPane.getChildren().add(button);

            // Add the AnchorPane to the GridPane
            postGrid.add(anchorPane, columns, rows);
            GridPane.setMargin(anchorPane, new Insets(10));

            // Increment column count
            columns++;

            // Check if the columns exceed a threshold (e.g., 3), then reset columns and increment rows
            if (columns == 3) {
                columns = 0;
                rows++;
            }
        }

    }

    private void buttonClickforRemove(String name) {
        try {
            // Establish connection to the databases
            MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
            database = mongoClient.getDatabase("anodb");
            //private MongoCollection<Document> PokemonCollection;
            plantCollections= database.getCollection("CollectionsFilter");


            favoriteCollection = database.getCollection("Favorite");

            // Query the Pokemon_Details database to get the full information of the Pokémon based on its ID

            Document query = new Document("name", name);
            Document pokedocs = plantCollections.find(query).first();
            Document pockesearch = favoriteCollection.find(query).first();

//            if (pokedocs != null) { // Check if the Pokémon information is found
//                // Save the retrieved Pokémon information into the favorites database
//                favoriteCollection.insertOne(pokedocs);
//                showAlert("Favorite!!!!","Yeahh!! You added the pokedex to your list...");
//                System.out.println("Pokemon added to favorites!");
//            }  if(pockesearch!=null){
//                System.out.println("Pokemon not found in the Pokemon_Details database.");
//                showAlert("Favorite!!!","you have already added your pokedex to your list...");
//            }
            if (pokedocs != null) { // Check if the Pokémon information is found
                if (pockesearch != null) { // Check if the Pokémon is not already in favorites
                    // Save the retrieved Pokémon information into the favorites database
                    favoriteCollection.deleteOne(pokedocs);
                    showAlert("Favorite!!!!", "Yeahh!! You have deleted the plants from your list...️", "😁😁");
                    System.out.println("Plants removed from favorites!");
                    ObservableList<Node> anchorPanes = postGrid.getChildren();
                    for (
                            Node node : anchorPanes) {
                        if (node instanceof AnchorPane) {
                            AnchorPane anchorPane = (AnchorPane) node;
                            Label nameLabel = (Label) anchorPane.getChildren().get(0); // Assuming the Label is the first child
                            if (nameLabel.getText().equals(name)) {
                                postGrid.getChildren().remove(anchorPane);
                                break;
                            }
                        }
                    }
                } else {
                    // Pokémon is already in favorites
                    System.out.println("Pokemon already in favorites.");
                    showAlert("Favorite!!!", "You have already added your Plants to your list...😒😒", "😉😉");

                }
            } else {
                // Pokémon not found in the Pokemon_Details database
                System.out.println("Plants not found in the Plants database.");
                showAlert("Error", "Plants not found.", "🤷‍♀️🤷‍♀️");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Optional<ButtonType> showAlert(String title, String message, String emoji) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(emoji);
        alert.setContentText(message);
        alert.showAndWait();
        return null;
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


}




