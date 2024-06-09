package com.example.plantorum;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.bson.Document;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NextbtnControl {

    @FXML
    private Label plantnamelabel;

    @FXML
    private Label infolabel;

    @FXML
    private Label scifiname;

    @FXML
    private Label infolabel1;

    @FXML
    private Label infolabel11;

    @FXML
    private Label typelabel;

    @FXML
    private Label familylabel;

    @FXML
    private Label seasonlabel;

    @FXML
    private Label infolabel2;
    @FXML
    private Button backbtn;

    @FXML
    private Button favbtn;

    @FXML
    private ImageView favicon;

    @FXML
    private Label originlabel;

    @FXML
    private Label originlabel1;
    @FXML
    private ImageView imagebg;
    @FXML
    private Button nextbtn;

    @FXML
    private Button prevbtn;

    @FXML
    private Label careinfo;
    private MongoDatabase database;
    private MongoCollection<Document> plantCollection;
    private MongoCollection<Document> favoriteCollection;
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
        String family = plantsdoc.getString("family");
        String description = plantsdoc.getString("description");
        String light = plantsdoc.getEmbedded(List.of("care_instructions", "light"), String.class);
        String watering = plantsdoc.getEmbedded(List.of("care_instructions", "watering"), String.class);
        String soil = plantsdoc.getEmbedded(List.of("care_instructions", "soil"), String.class);
        String temperature = plantsdoc.getEmbedded(List.of("care_instructions", "temperature"), String.class);
        String humidity = plantsdoc.getEmbedded(List.of("care_instructions", "humidity"), String.class);
        List<String> origin = plantsdoc.getList("origin", String.class);
        List<String> bloomingSeason = plantsdoc.getList("blooming_season", String.class);
        String id = plantsdoc.getString("id");
        // Concatenate arrays into single strings
        String originStr = String.join("\n", origin);
        String bloomingSeasonStr = String.join(", ", bloomingSeason);
        String imageUrl = plantsdoc.getString("image_url");

        plantnamelabel.setText("Name: " + name);
        scifiname.setText( scientificName);
        typelabel.setText( type);
        familylabel.setText("family: " + family);
        infolabel.setText(description);
//        lightButton.setText("Light: " + light);
//        wateringButton.setText("Watering: " + watering);
//        soilButton.setText("Soil: " + soil);
//        temperatureButton.setText("Temperature: " + temperature);
//        humidityButton.setText("Humidity: " + humidity);
        originlabel.setText( originStr);
        seasonlabel.setText( bloomingSeasonStr);
        // Load the image
        try {
            Image image = new Image(new FileInputStream(imageUrl));
            imagebg.setImage(image);
            imagebg.setPreserveRatio(false); // Preserve the aspect ratio
        } catch (Exception e) {
            System.out.println("Failed to load image: " + e.getMessage());
        }
        try {
            Image image = new Image(new FileInputStream("D:\\4rth Sem Product\\JavaProject\\Plantorum_App_JavaProject\\Plantorum\\src\\image\\favicon.png"));
            favicon .setImage(image);
            favicon.setPreserveRatio(false); // Preserve the aspect ratio
        } catch (Exception e) {
            System.out.println("Failed to load image: " + e.getMessage());
        }
        //nextbtn.setId(name);
//        if (nextbtn.setOnAction();)
//            //then now write function for nextbtn when it will be clicked it will be go to the next plants with for showing like take a document where all the collection will be there and then find the id of the plants and for nextbtn button go to the next id for fetching data and it will be done untill i click the next button of it and if it go to the last plants of the collections then go the first plants also .
//       // write a code based on that


    }
    @FXML
    public void  click()
    {
        String name =nextbtn.getId();
        System.out.println(name);
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("anodb");
        plantCollection = database.getCollection("Plants");
        //String name =plantnamelabel.getText();
        Document query = new Document("name", name);
        Document plantsdoc = plantCollection.find(query).first();
        String id = plantsdoc.getString("_id");
        System.out.println(id);
        int currentIndex;
        int ids=Integer.parseInt(id);
        currentIndex = (ids + 1) ;
        System.out.println(currentIndex);
        String currentIndexAsString = String.valueOf(currentIndex); // Convert currentIndex to string
// Alternatively:
// String currentIndexAsString = currentIndex + ""; // Convert currentIndex to string using concatenation
        System.out.println(currentIndexAsString);
        Document query2 = new Document("_id", currentIndexAsString); // Use currentIndexAsString in the query
        //  Document plantsdoc2 = plantCollection.find(query2).first();
//        String name2 = plantsdoc2.getString("name");
//        System.out.println(name2);

        nextbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Call the method or perform the action when the button is clicked
                try {
                    loader (query2,event);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });
    }
    @FXML
    public  void loader (Document query,ActionEvent event) throws IOException {
        FXMLLoader welcomeLoader = new FXMLLoader(getClass().getResource("DetailsShow.fxml"));
        Parent welcomeRoot = welcomeLoader.load();
        //Document query = new Document("name", name);
        DetailsShow detailsControl = welcomeLoader.getController();
        detailsControl.initialize(query);

        Scene scene = new Scene(welcomeRoot);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//Stage stage= new Stage();

        stage.setScene(scene);
        stage.setTitle("Plant NExt Details");
        stage.show();
    }
    @FXML public void addToFavorites(ActionEvent event) {
        // Assuming you have a reference to the favorite icon button

        //ImageView favoriteButton = (ImageView) event.getSource();
        //String pokemonId = favoriteButton.getId(); // Get the ID of the Pokémon associated with the favorite button
        // Button favbtn2 = (Button) event.getSource();
//       String replace = id.replace(id.substring(0,3),"a");
        String name = favbtn.getId();
//        String modifiedId = "a" + id.substring(3);

        String plantname= name;
        System.out.println(plantname);

        try {
            // Establish connection to the databases
            MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
            database = mongoClient.getDatabase("anodb");
            plantCollection = database.getCollection("CollectionsFilter");


            favoriteCollection = database.getCollection("Favorite");

            // Query the Pokemon_Details database to get the full information of the Pokémon based on its ID
            // Document pokemonQuery = PokemonCollection.find(Filters.eq("iden", pokemonId)).first();
            Document query = new Document("name", plantname);
            Document plantdoc = plantCollection.find(query).first();
            Document plantsearch = favoriteCollection.find(query).first();

//            if (pokedocs != null) { // Check if the Pokémon information is found
//                // Save the retrieved Pokémon information into the favorites database
//                favoriteCollection.insertOne(pokedocs);
//                showAlert("Favorite!!!!","Yeahh!! You added the pokedex to your list...");
//                System.out.println("Pokemon added to favorites!");
//            }  if(pockesearch!=null){
//                System.out.println("Pokemon not found in the Pokemon_Details database.");
//                showAlert("Favorite!!!","you have already added your pokedex to your list...");
//            }
            if (plantdoc != null) { // Check if the Pokémon information is found
                if (plantsearch == null) { // Check if the Pokémon is not already in favorites
                    // Save the retrieved Pokémon information into the favorites database
                    favoriteCollection.insertOne(plantdoc);
                    showAlert("Favorite!!!!", "Yeahh!! added.️", "😁😁");
                    System.out.println("Pokemon added to favorites!");
                } else {
                    // Pokémon is already in favorites
                    System.out.println("Pokemon already in favorites.");
                    showAlert("Favorite!!!", "You have already added your pokedex to your list...😒😒", "😉😉");

                }
            } else {
                // Pokémon not found in the Pokemon_Details database
                System.out.println("Pokemon not found in the Pokemon_Details database.");
                showAlert("Error", "Pokemon not found.", "🤷‍♀️🤷‍♀️");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    private Optional<ButtonType> showAlert(String title, String message, String emoji) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(emoji);
        alert.setContentText(message);
        alert.showAndWait();
        return null;
    }


}
