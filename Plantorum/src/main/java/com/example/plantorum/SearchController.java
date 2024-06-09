package com.example.plantorum;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.bson.Document;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SearchController {
    @FXML
    private TextField searchField;

    @FXML
    private GridPane resultGrid;

    @FXML
    private ImageView mainbg;

    @FXML
    private CheckBox floweringCheckBox;
    @FXML
    private CheckBox herbCheckBox;
    @FXML
    private CheckBox shrubCheckBox;
    @FXML
    private CheckBox cactusCheckBox;
    @FXML
    private CheckBox aquaticCheckBox;
    @FXML
    private CheckBox indoorCheckBox;
    @FXML
    private CheckBox outdoorCheckBox;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private AnchorPane bgimage;

    private MongoCollection<Document> collection;

    @FXML
    public void initialize() {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017/");
        MongoDatabase database = mongoClient.getDatabase("anodb");
        collection = database.getCollection("CollectionsFilter");
        try {
            Image image = new Image(new FileInputStream("D:\\4rth Sem Product\\JavaProject\\Plantorum_App_JavaProject\\Plantorum\\src\\image\\Searchbg.png"));
            mainbg .setImage(image);
            mainbg.setPreserveRatio(false); // Preserve the aspect ratio
        } catch (Exception e) {
            System.out.println("Failed to load image: " + e.getMessage());
        }

//        ImageView imageView = new ImageView();
//        imageView.setFitWidth(1207);
//        imageView.setFitHeight(762);
//
////        imageView.setStyle("-fx-background-radius: 30;");
////            imageView.setStyle("-fx-border-radius: 30;");
////            imageView.setStyle("-fx-background-radius: 30 30 30 30 ");
////        imageView.setPreserveRatio(true);
//
//
//        // imageView.setPreserveRatio(true);
//
//            try {
//                Image image = new Image(new FileInputStream("D:\\4rth Sem Product\\JavaProject\\Plantorum_App_JavaProject\\Plantorum\\src\\image\\catagorybgmain.png"));
//                imageView.setImage(image);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        bgimage.getChildren().add(imageView);


        // Configure the ScrollPane
        // Load the background image
        try {
            Image backgroundImage = new Image(new FileInputStream("D:\\4rth Sem Product\\JavaProject\\Plantorum_App_JavaProject\\Plantorum\\src\\image\\catagorybgmain.png"));

            // Create a new ImageView for the background image
            ImageView backgroundImageView = new ImageView(backgroundImage);
            backgroundImageView.setFitWidth(1207); // Set the width of the background image
            backgroundImageView.setFitHeight(762); // Set the height of the background image

            // Set the preserve ratio to false to stretch the image to fill the ImageView
            backgroundImageView.setPreserveRatio(false);

            // Add the ImageView to the bgimage AnchorPane
            bgimage.getChildren().add(backgroundImageView);
        } catch (Exception e) {
            e.printStackTrace();
        }

        scrollPane.setFitToWidth(true);
        scrollPane.setContent(resultGrid);

        // Adding event handlers
        searchField.addEventHandler(KeyEvent.KEY_RELEASED, event -> searchPlants());
        floweringCheckBox.setOnAction(event -> searchPlants());
        herbCheckBox.setOnAction(event -> searchPlants());
        shrubCheckBox.setOnAction(event -> searchPlants());
        cactusCheckBox.setOnAction(event -> searchPlants());
        aquaticCheckBox.setOnAction(event -> searchPlants());
        indoorCheckBox.setOnAction(event -> searchPlants());
        outdoorCheckBox.setOnAction(event -> searchPlants());
    }

    String lastSearchText="";
@FXML
    private void searchPlants() {
        String searchText = searchField.getText().trim();
        List<String> categories = getSelectedCategories();
        List<String> places = getSelectedPlaces();
        ObservableList<String> results = FXCollections.observableArrayList();
    if (!searchText.equals(lastSearchText)) {
        resultGrid.getChildren().clear();
        lastSearchText = searchText;
    }
        List<Document> documents;
        StringBuilder queryBuilder = new StringBuilder(String.format("{name: {$regex: /.*%s.*/i}", Pattern.quote(searchText)));

        if (!categories.isEmpty()) {
            queryBuilder.append(String.format(", catagory: {$in: [%s]}", categories.stream().map(c -> String.format("\"%s\"", c)).collect(Collectors.joining(","))));
        }
        if (!places.isEmpty()) {
            queryBuilder.append(String.format(", place: {$in: [%s]}", places.stream().map(p -> String.format("\"%s\"", p)).collect(Collectors.joining(","))));
        }
        queryBuilder.append("}");

        String query = queryBuilder.toString();

        documents = collection.find(Document.parse(query)).into(new ArrayList<>());

        resultGrid.getChildren().clear();
        int row = 1;
        int col = 0;
    for (Document doc : documents) {
        String name = doc.getString("name");
        String imageUrl = doc.getString("image_url");

        addPlantToGrid(name, imageUrl, col, row);

        col++;
        if (col ==4) { // Assuming 3 columns per row
            col = 0;
            row++;
        }
    }
    }

    @FXML
    private void addPlantToGrid(String name, String imageUrl, int columns, int rows)
    {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefHeight(150);
        anchorPane.setPrefWidth(250);
//            anchorPane.setStyle("-fx-background-color: orange");
        anchorPane.getStyleClass().add("anchor-pane2");
       // anchorPane.getStyleClass().add("ash");

        ImageView imageView = new ImageView();
        imageView.setFitWidth(250);
        imageView.setFitHeight(150);

//        imageView.setStyle("-fx-background-radius: 30;");
//            imageView.setStyle("-fx-border-radius: 30;");
//            imageView.setStyle("-fx-background-radius: 30 30 30 30 ");
//        imageView.setPreserveRatio(true);


        // imageView.setPreserveRatio(true);
        if (imageUrl != null && !imageUrl.isEmpty()) {
            try {
                Image image = new Image(new FileInputStream(imageUrl));
                imageView.setImage(image);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        anchorPane.getChildren().add(imageView);
        // Add Label for Pokemon name
        Label pokemonNameLabel = new Label(name);
        Label plantNameLabel = new Label(name);
        plantNameLabel.getStyleClass().add("glass");
        plantNameLabel.setStyle(
                "-fx-font-family: 'Arial Rounded MT Bold';" +
                        "-fx-text-fill: black;" +
                        "-fx-alignment: center;" +
                        "-fx-max-width: Infinity;" +
                        "-fx-background-color: rgba(255, 255, 255, 0.6);" +
                        "-fx-background-radius: 30;" +
                        "-fx-border-radius: 30 30 30 30;" +
                        "-fx-border-color: rgba(255, 255, 255, 0.66);"
        );
        StackPane stackPane = new StackPane();
        stackPane.setPrefHeight(150);
        stackPane.setPrefWidth(250);
        stackPane.setStyle("-fx-background-radius: 30; -fx-background-color: transparent;");
        StackPane.setAlignment(plantNameLabel, Pos.BOTTOM_CENTER);
        StackPane.setMargin(plantNameLabel, new Insets(0, 0, 0, 0));

        stackPane.getChildren().addAll( imageView,plantNameLabel);
        anchorPane.getChildren().add(stackPane);



//            // Add the AnchorPane to the GridPane
        anchorPane.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            PlantsDetailsShow(event,name, imageUrl);

        });
        resultGrid.add(anchorPane, columns, rows);
        GridPane.setMargin(anchorPane, new Insets(10));

        // Increment column count
        columns++;

        // Check if the columns exceed a threshold (e.g., 3), then reset columns and increment rows
        if (columns == 4) {
            columns = 0;
            rows++;
           // break;
        }
    }


    private void PlantsDetailsShow(MouseEvent event, String name, String imageUrl) {
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


    private List<String> getSelectedCategories() {
        List<String> categories = new ArrayList<>();
        if (floweringCheckBox.isSelected()) categories.add("Flowering");
        if (herbCheckBox.isSelected()) categories.add("Herb");
        if (shrubCheckBox.isSelected()) categories.add("Shrub");
        if (cactusCheckBox.isSelected()) categories.add("Cactus");
        if (aquaticCheckBox.isSelected()) categories.add("Aquatic");
        return categories;
    }

    private List<String> getSelectedPlaces() {
        List<String> places = new ArrayList<>();
        if (indoorCheckBox.isSelected()) places.add("Indoor");
        if (outdoorCheckBox.isSelected()) places.add("Outdoor");
        return places;
    }
}
