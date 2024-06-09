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
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.bson.Document;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SymptompControl {
    @FXML
    private ImageView sideimg;
    @FXML
    private Button leaves;

    @FXML
    private ImageView leavesimg;



    @FXML
    private Button flowering;

    @FXML
    private ImageView flowersimg;

    @FXML
    private AnchorPane root;

    @FXML
    private ImageView rootimg;

    @FXML
    private AnchorPane flower;

    @FXML
    private Button Stem;
    @FXML
    private Button roots;

    @FXML
    private ImageView stemimg;

    private MongoDatabase database;
    private MongoCollection<Document> plantCollection;

    public void initialize() {
        try {
            Image leavesImage = new Image(new FileInputStream("D:\\4rth Sem Product\\JavaProject\\Plantorum_App_JavaProject\\Plantorum\\src\\image\\leavesimg.png"));
            leavesimg.setImage(leavesImage);

            Image flowersImage = new Image(new FileInputStream("D:\\4rth Sem Product\\JavaProject\\Plantorum_App_JavaProject\\Plantorum\\src\\image\\flowersimg.png"));
            flowersimg.setImage(flowersImage);

            Image rootImage = new Image(new FileInputStream("D:\\4rth Sem Product\\JavaProject\\Plantorum_App_JavaProject\\Plantorum\\src\\image\\rootsimg.png"));
            rootimg.setImage(rootImage);

            Image stemImage = new Image(new FileInputStream("D:\\4rth Sem Product\\JavaProject\\Plantorum_App_JavaProject\\Plantorum\\src\\image\\stemimg.png"));
            stemimg.setImage(stemImage);
            Image sideimgg = new Image(new FileInputStream("D:\\4rth Sem Product\\JavaProject\\Plantorum_App_JavaProject\\Plantorum\\src\\image\\BasharGach.jpg"));
            sideimg.setImage(sideimgg);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        flowering.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Call the method or perform the action when the button is clicked
                loaderflower("flowers",event);

            }
        });
        leaves.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Call the method or perform the action when the button is clicked
                loadleaves("leaves",event);

            }
        });
        roots.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Call the method or perform the action when the button is clicked
                loadroot("roots",event);

            }
        });

        Stem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Call the method or perform the action when the button is clicked
                loadstem("stem",event);

            }
        });
    }

    private void loadleaves(String leaves, ActionEvent event) {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("anodb");
        plantCollection = database.getCollection("Symptomps");
        Document query = new Document("part", leaves);
        List<String> plantcatagory = new ArrayList<>();

        for (Document document : plantCollection.find(query)) {
            plantcatagory.add(document.getString("issue"));
        }
        for (String plantName : plantcatagory) {
            System.out.println("poko " + plantName);
        }
        try {
            fxloader(query,event,leaves);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadroot(String roots, ActionEvent event) {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("anodb");
        plantCollection = database.getCollection("Symptomps");
        Document query = new Document("part", roots);
        List<String> plantcatagory = new ArrayList<>();

        for (Document document : plantCollection.find(query)) {
            plantcatagory.add(document.getString("issue"));
        }
        for (String plantName : plantcatagory) {
            System.out.println("poko " + plantName);
        }
        try {
            fxloader(query,event,roots);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void loadstem(String stem, ActionEvent event) {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("anodb");
        plantCollection = database.getCollection("Symptomps");
        Document query = new Document("part", stem);
        List<String> plantcatagory = new ArrayList<>();

        for (Document document : plantCollection.find(query)) {
            plantcatagory.add(document.getString("issue"));
        }
        for (String plantName : plantcatagory) {
            System.out.println("poko " + plantName);
        }
        try {
            fxloader(query,event,stem);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loaderflower(String flower, ActionEvent event) {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("anodb");
        plantCollection = database.getCollection("Symptomps");
        Document query = new Document("part", flower);
        List<String> plantcatagory = new ArrayList<>();

        for (Document document : plantCollection.find(query)) {
            plantcatagory.add(document.getString("issue"));
        }
        for (String plantName : plantcatagory) {
            System.out.println("poko " + plantName);
        }
        try {
            fxloader(query,event,flower);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void ShowFavorites(ActionEvent event) {
        try {
            // Load the FXML file
            Parent root = FXMLLoader.load(getClass().getResource("FavScene.fxml"));

            // Create a new scene
            Scene scene = new Scene(root);

            // Get the stage information
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the scene and show the stage
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public  void remainder (ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
//         Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage primaryStage= new Stage();
        primaryStage.setTitle("Calendar with Tasks");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    @FXML
    public  void Search (ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("Search.fxml"));
        SearchController searchController = new SearchController();
        // Create a new scene
        Scene scene = new Scene(root);

        // Get the stage information
        scene.getStylesheets().add(getClass().getResource("stylecss.css").toExternalForm());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Set the scene and show the stage
        stage.setScene(scene);
        stage.show();


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
    public void symptomps (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SymptomChecker.fxml"));
        // Parent root = FXMLLoader.load(getClass().getResource("IssuesShow.fxml"));
        SymptompControl symptompControl = new SymptompControl();
        // Create a new scene
        Scene scene = new Scene(root);

        scene.getStylesheets().add(getClass().getResource("stylecss.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("sylesheet.css").toExternalForm());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void fxloader(Document query,ActionEvent event,String str) throws IOException {
        FXMLLoader welcomeLoader = new FXMLLoader(getClass().getResource("IssuesShow.fxml"));
        Parent welcomeRoot = welcomeLoader.load();

        CatagoryControl detailsControl = welcomeLoader.getController();
        detailsControl.initialize(query,str);

        Scene scene = new Scene(welcomeRoot);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//Stage stage= new Stage();

        stage.setScene(scene);
        stage.setTitle("Plant Details");
        stage.show();
    }

}
