//package com.example.plantora;
//
//import com.mongodb.client.MongoClient;
//import com.mongodb.client.MongoClients;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoDatabase;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Label;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.stage.Stage;
//import org.bson.Document;
//
//import java.io.FileInputStream;
//import java.util.List;
//
//import java.io.IOException;
//import java.net.URL;
//import java.util.ResourceBundle;
//
//public class PlantsDetailsController   {
//
//    @FXML
//    private ImageView imageView;
//
//    @FXML
//    private Label nameLabel;
//
//    @FXML
//    private Label scientificNameLabel;
//
//    @FXML
//    private Label typeLabel;
//
//    @FXML
//    private Label familyLabel;
//
//    @FXML
//    private Label originLabel;
//
//    @FXML
//    private Label descriptionLabel;
//
//    @FXML
//    private Label bloomingSeasonLabel;
//
//    @FXML
//    private Label lightLabel;
//
//    @FXML
//    private Label wateringLabel;
//
//    @FXML
//    private Label soilLabel;
//
//    @FXML
//    private Label temperatureLabel;
//
//    @FXML
//    private Label humidityLabel;
//
//
//    private MongoDatabase database;
//    private MongoCollection<Document> plantCollection;
//
//@FXML
//
////    public  void initialize(Document query) {
////        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
////        database = mongoClient.getDatabase("anodb");
////        plantCollection = database.getCollection("Plants");
////        Document plantsdoc=plantCollection.find(query).first();
////
////
////
//////        for (Document doc : plantDocuments) {
////        String name = plantsdoc.getString("name");
////        String scientificName = plantsdoc.getString("scientific_name");
////        String type = plantsdoc.getString("type");
////        //String imageUrl = plantsdoc.getString("imageUrl");
////
////
////        // Set plant details
////       // imageView.setImage(new Image(plant.getImageUrl()));
////        nameLabel.setText("name: "+name);
////        scientificNameLabel.setText("Scientific Name: " +scientificName);
////        typeLabel.setText("Type: "+type );
////        familyLabel.setText("Family: " + plantsdoc.getString("family"));
////       originLabel.setText("Origin: " +plantsdoc.getString("origin"));
////        descriptionLabel.setText("Description: " +plantsdoc.getString("description"));
////        bloomingSeasonLabel.setText("Blooming Season: " + plantsdoc.getString("Blooming Season"));
////        lightLabel.setText("Light: " + plantsdoc.getString("light"));
////        wateringLabel.setText("Watering: " + plantsdoc.getString("watering"));
////        soilLabel.setText("Soil: " + plantsdoc.getString("soil"));
////        temperatureLabel.setText("Temperature: " + plantsdoc.getString("temperature"));
////        humidityLabel.setText("Humidity: " + plantsdoc.getString("humidity"));
////    }
//public void initialize(Document query) {
//    MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
//    database = mongoClient.getDatabase("anodb");
//    plantCollection = database.getCollection("Plants");
//    Document plantsdoc = plantCollection.find(query).first();
//
//    String name = plantsdoc.getString("name");
//    String scientificName = plantsdoc.getString("scientific_name");
//    String type = plantsdoc.getString("type");
//    String description = plantsdoc.getString("description");
//    String light = plantsdoc.getEmbedded(List.of("care_instructions", "light"), String.class);
//    String watering = plantsdoc.getEmbedded(List.of("care_instructions", "watering"), String.class);
//    String soil = plantsdoc.getEmbedded(List.of("care_instructions", "soil"), String.class);
//    String temperature = plantsdoc.getEmbedded(List.of("care_instructions", "temperature"), String.class);
//    String humidity = plantsdoc.getEmbedded(List.of("care_instructions", "humidity"), String.class);
//    List<String> origin = plantsdoc.getList("origin", String.class);
//    List<String> bloomingSeason = plantsdoc.getList("blooming_season", String.class);
//
//    // Concatenate arrays into single strings
//    String originStr = String.join(", ", origin);
//    String bloomingSeasonStr = String.join(", ", bloomingSeason);
//    String imageUrl = plantsdoc.getString("image_url");
//
//    nameLabel.setText("Name: " + name);
//    scientificNameLabel.setText("Scientific Name: " + scientificName);
//    typeLabel.setText("Type: " + type);
//    descriptionLabel.setText("Description: " + description);
//    lightLabel.setText("Light: " + light);
//    wateringLabel.setText("Watering: " + watering);
//    soilLabel.setText("Soil: " + soil);
//    temperatureLabel.setText("Temperature: " + temperature);
//    humidityLabel.setText("Humidity: " + humidity);
//    originLabel.setText("Origin: " + originStr);
//    bloomingSeasonLabel.setText("Blooming Season: " + bloomingSeasonStr);
//    // Load the image
//    try {
//        Image image = new Image(new FileInputStream(imageUrl));
//        imageView.setImage(image);
//       // imageView.setFitWidth(200); // Set the width of the image view
//        imageView.setPreserveRatio(false); // Preserve the aspect ratio
//    } catch (Exception e) {
//        System.out.println("Failed to load image: " + e.getMessage());
//    }
//}
//
//}
package com.example.plantorum;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.bson.Document;

import java.io.FileInputStream;
import java.util.List;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PlantsDetailsController   {

    @FXML
    private ImageView imageView;

    @FXML
    private Button nameButton;

    @FXML
    private Button scientificNameButton;

    @FXML
    private Button typeButton;

    @FXML
    private Button familyButton;

    @FXML
    private Button originButton;

    @FXML
    private Button descriptionButton;

    @FXML
    private Button bloomingSeasonButton;

    @FXML
    private Button lightButton;

    @FXML
    private Button wateringButton;

    @FXML
    private Button soilButton;

    @FXML
    private Button temperatureButton;

    @FXML
    private Button humidityButton;


    private MongoDatabase database;
    private MongoCollection<Document> plantCollection;
    private Stage detailsStage;

    @FXML


    public void initialize(Document query) {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("anodb");
        plantCollection = database.getCollection("Plants");
        Document plantsdoc = plantCollection.find(query).first();

        String name = plantsdoc.getString("name");
        String scientificName = plantsdoc.getString("scientific_name");
        String type = plantsdoc.getString("type");
        String description = plantsdoc.getString("description");
        String light = plantsdoc.getEmbedded(List.of("care_instructions", "light"), String.class);
        String watering = plantsdoc.getEmbedded(List.of("care_instructions", "watering"), String.class);
        String soil = plantsdoc.getEmbedded(List.of("care_instructions", "soil"), String.class);
        String temperature = plantsdoc.getEmbedded(List.of("care_instructions", "temperature"), String.class);
        String humidity = plantsdoc.getEmbedded(List.of("care_instructions", "humidity"), String.class);
        List<String> origin = plantsdoc.getList("origin", String.class);
        List<String> bloomingSeason = plantsdoc.getList("blooming_season", String.class);

        // Concatenate arrays into single strings
        String originStr = String.join(", ", origin);
        String bloomingSeasonStr = String.join(", ", bloomingSeason);
        String imageUrl = plantsdoc.getString("image_url");

        nameButton.setText("Name: " + name);
        scientificNameButton.setText("Scientific Name: " + scientificName);
        typeButton.setText("Type: " + type);
        descriptionButton.setText("Description: " + description);
        lightButton.setText("Light: " + light);
        wateringButton.setText("Watering: " + watering);
        soilButton.setText("Soil: " + soil);
        temperatureButton.setText("Temperature: " + temperature);
        humidityButton.setText("Humidity: " + humidity);
        originButton.setText("Origin: " + originStr);
        bloomingSeasonButton.setText("Blooming Season: " + bloomingSeasonStr);
        // Load the image
        try {
            Image image = new Image(new FileInputStream(imageUrl));
            imageView.setImage(image);
            // imageView.setFitWidth(200); // Set the width of the image view
            imageView.setPreserveRatio(false); // Preserve the aspect ratio
        } catch (Exception e) {
            System.out.println("Failed to load image: " + e.getMessage());
        }

    }
    public void setWelcomeStage(Stage stage) {
        this.detailsStage = stage;
    }


}
