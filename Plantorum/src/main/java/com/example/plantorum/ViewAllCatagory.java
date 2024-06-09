package com.example.plantorum;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.bson.Document;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ViewAllCatagory {
    @FXML
    private ImageView plantorambg;
    @FXML
    private ImageView rightimg;

    @FXML
    private Button flowers1211;

    @FXML
    private ImageView sideimg;


    @FXML
    private ImageView acquatic;

    @FXML
    private Button cactus;

    @FXML
    private Button Herbs;

    @FXML
    private Button shrubs;

    @FXML
    private Button flowers;

    @FXML
    private ImageView hurbsimg;

    @FXML
    private ImageView cactusimg;

    @FXML
    private ImageView shrubsimg;

    @FXML
    private ImageView acquaticimg;

    @FXML
    private ImageView flowersimg;

    @FXML
    private Button acquaticbtn;

    @FXML
    private Button shrubsbtn;

    @FXML
    private Button flowersbtn;

    @FXML
    private Button hurbsbtn;
    @FXML
    private GridPane postgrid;

    @FXML
    private Button cactusbtn;
    private MongoDatabase database;
    private MongoCollection<Document> plantCollection;

    public void initialize(){
        // Load the image

        try {
            Image image = new Image(new FileInputStream("D:\\4rth Sem Product\\JavaProject\\Plantorum_App_JavaProject\\Plantorum\\src\\image\\acquatic.png"));
            acquaticimg.setImage(image);
            acquaticimg.setPreserveRatio(false); // Preserve the aspect ratio
        } catch (Exception e) {
            System.out.println("Failed to load image: " + e.getMessage());
        }
        //sideimg
        try {
            Image image = new Image(new FileInputStream("D:\\4rth Sem Product\\JavaProject\\Plantorum_App_JavaProject\\Plantorum\\src\\image\\catagoryside.png"));
            sideimg.setImage(image);
            sideimg.setPreserveRatio(false); // Preserve the aspect ratio
        } catch (Exception e) {
            System.out.println("Failed to load image: " + e.getMessage());
        }
        //rightimg
        try {
            Image image = new Image(new FileInputStream("D:\\4rth Sem Product\\JavaProject\\Plantorum_App_JavaProject\\Plantorum\\src\\image\\catagorybgmain.png"));
            rightimg.setImage(image);
            rightimg.setPreserveRatio(false); // Preserve the aspect ratio
        } catch (Exception e) {
            System.out.println("Failed to load image: " + e.getMessage());
        }
        //plantorumbg
        try {
            Image image = new Image(new FileInputStream("D:\\4rth Sem Product\\JavaProject\\Plantorum_App_JavaProject\\Plantorum\\src\\image\\plantorumwritthnbg.png"));
           plantorambg .setImage(image);
            plantorambg.setPreserveRatio(false); // Preserve the aspect ratio
        } catch (Exception e) {
            System.out.println("Failed to load image: " + e.getMessage());
        }
        //shrubs
        try {
            Image image = new Image(new FileInputStream("D:\\4rth Sem Product\\JavaProject\\Plantorum_App_JavaProject\\Plantorum\\src\\image\\shrubs.png"));
            shrubsimg .setImage(image);
            shrubsimg.setPreserveRatio(false); // Preserve the aspect ratio
        } catch (Exception e) {
            System.out.println("Failed to load image: " + e.getMessage());
        }//herbs\
        try {
            Image image = new Image(new FileInputStream("D:\\4rth Sem Product\\JavaProject\\Plantorum_App_JavaProject\\Plantorum\\src\\image\\hurbs.png"));
            hurbsimg .setImage(image);
            hurbsimg.setPreserveRatio(false); // Preserve the aspect ratio
        } catch (Exception e) {
            System.out.println("Failed to load image: " + e.getMessage());
        }
        //flowers

        try {
            Image image = new Image(new FileInputStream("D:\\4rth Sem Product\\JavaProject\\Plantorum_App_JavaProject\\Plantorum\\src\\image\\flowering.png"));
            flowersimg.setImage(image);
            flowersimg.setPreserveRatio(true); // Preserve the aspect ratio
        } catch (Exception e) {
            System.out.println("Failed to load image: " + e.getMessage());
        }
        //cactus
        try {
            Image image = new Image(new FileInputStream("D:\\4rth Sem Product\\JavaProject\\Plantorum_App_JavaProject\\Plantorum\\src\\image\\cactus.png"));
            cactusimg .setImage(image);
            cactusimg.setPreserveRatio(false); // Preserve the aspect ratio
        } catch (Exception e) {
            System.out.println("Failed to load image: " + e.getMessage());
        }
        acquaticbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Call the method or perform the action when the button is clicked
                try {
                    loaderacquatic("Aquatic",event);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });
        shrubsbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Call the method or perform the action when the button is clicked
                loadershrubs("Shrub",event);

            }
        });
        flowersbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Call the method or perform the action when the button is clicked
                try {
                    loaderflower("Flowering",event);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });
        hurbsbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Call the method or perform the action when the button is clicked
                loaderHerb("Herb",event);

            }
        });
        cactusbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Call the method or perform the action when the button is clicked
                loaderCactus("Cactus",event);

            }
        });
        
    }

    private void loaderCactus(String cactus,ActionEvent event) {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("anodb");
        plantCollection = database.getCollection("CollectionsFilter");
        Document query = new Document("catagory", cactus);
        List<String> plantcatagory = new ArrayList<>();

        for (Document document : plantCollection.find(query)) {
            plantcatagory.add(document.getString("name"));
        }
        for (String plantName : plantcatagory) {
            System.out.println("poko " + plantName);
        }
        try {
            fxloader(query,event,cactus);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loaderHerb(String herb,ActionEvent event)  {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("anodb");
        plantCollection = database.getCollection("CollectionsFilter");
        Document query = new Document("catagory", herb);
        List<String> plantcatagory = new ArrayList<>();

        for (Document document : plantCollection.find(query)) {
            plantcatagory.add(document.getString("name"));
        }
        for (String plantName : plantcatagory) {
            System.out.println("poko " + plantName);
        }
        try {
            fxloader(query,event,herb);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loaderflower(String flower,ActionEvent event) throws IOException {

        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("anodb");
        plantCollection = database.getCollection("CollectionsFilter");
        Document query = new Document("catagory", flower);
        List<String> plantcatagory = new ArrayList<>();

        for (Document document : plantCollection.find(query)) {

            plantcatagory.add(document.getString("name"));
        }

        for (int i = 0; i < plantcatagory.size(); i++) {
            System.out.println("poko" + plantcatagory.get(i));
        }

        try {
            fxloader(query,event,flower);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        CatagoryControl catagoryControl = new CatagoryControl();
//        catagoryControl.initialize(query);

    }
    @FXML
    private void fxloader(Document query,ActionEvent event,String str) throws IOException {
        FXMLLoader welcomeLoader = new FXMLLoader(getClass().getResource("CatagoryPlats.fxml"));
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

    private void loadershrubs(String shrub,ActionEvent event) {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("anodb");
        plantCollection = database.getCollection("CollectionsFilter");
        Document query = new Document("catagory", shrub);
        List<String> plantcatagory = new ArrayList<>();

        for (Document document : plantCollection.find(query)) {
            plantcatagory.add(document.getString("name"));
        }
        for (String plantName : plantcatagory) {
            System.out.println("poko " + plantName);
        }
        try {
            fxloader(query,event,shrub);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loaderacquatic(String acquatic,ActionEvent event) throws IOException {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("anodb");
        plantCollection = database.getCollection("CollectionsFilter");
        Document query = new Document("catagory", acquatic);
        List<String> plantcatagory = new ArrayList<>();

        for (Document document : plantCollection.find(query)) {
            plantcatagory.add(document.getString("name"));
        }
        for (String plantName : plantcatagory) {
            System.out.println("poko " + plantName);
        }
        try {
            fxloader(query,event,acquatic);
        } catch (IOException e) {
            throw new RuntimeException(e);
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

}
