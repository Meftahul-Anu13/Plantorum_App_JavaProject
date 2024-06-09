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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
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
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.bson.Document;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class PlantController {

    @FXML
    private ImageView filterimg;

    @FXML
    private ImageView myprofimg;

    @FXML
    private ImageView favicon;
    @FXML
    private ImageView barsimg;
    @FXML
    private Label favLabel;
    @FXML
    private VBox vBut;
    @FXML
    private ImageView image;
    @FXML
    private ImageView bgimage;
    @FXML
    private ImageView plantorumimg;
   // @FXML private  Stage stage;

    // Define MongoDB variables
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> favoriteCollection;
    private MongoCollection<Document> PlantCollection;
private String email;
private  String pass;

    @FXML
    private GridPane postGrid;
    @FXML
    public void initialize() {
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("anodb");
        PlantCollection = database.getCollection("CollectionsFilter");
        List<String> PlantsNames = new ArrayList<>();
        List<String> PlantsImage = new ArrayList<>();

        int columns = 0;
        int rows = 1;

        try {

            Image image = new Image(new FileInputStream("D:\\4rth Sem Product\\JavaProject\\Plantorum_App_JavaProject\\Plantorum\\src\\image\\PlantsDetailsBg.jpg"));
            Image img=new Image(new FileInputStream("D:\\4rth Sem Product\\JavaProject\\Plantorum_App_JavaProject\\Plantorum\\src\\image\\plantorumwritthnbg.png"));
            Image baricon=new Image(new FileInputStream("D:\\4rth Sem Product\\JavaProject\\Plantorum_App_JavaProject\\Plantorum\\src\\image\\3bars.png"));
            Image fav=new Image(new FileInputStream("D:\\4rth Sem Product\\JavaProject\\Plantorum_App_JavaProject\\Plantorum\\src\\image\\favicon.png"));
            Image myprof=new Image(new FileInputStream("D:\\4rth Sem Product\\JavaProject\\Plantorum_App_JavaProject\\Plantorum\\src\\image\\myprofileimg.png"));
            Image filter=new Image(new FileInputStream("D:\\4rth Sem Product\\JavaProject\\Plantorum_App_JavaProject\\Plantorum\\src\\image\\filter.png"));
           filterimg.setImage(filter);
           favicon.setImage(fav);
           myprofimg.setImage(myprof);
           barsimg.setImage(baricon);
            bgimage.setImage(image);
            plantorumimg.setImage(img);


        } catch (FileNotFoundException e) {
            // System.out.println("Unable to locate image file: " + item.toLowerCase() + ".jpg");
        }
        for (Document document : PlantCollection.find()) {
            PlantsNames.add(document.getString("name"));
        }
        for (Document document : PlantCollection.find()) {
            PlantsImage.add(document.getString("image_url"));
        }
        for (int i = 0; i < 6; i++) {
            System.out.println("poko" + PlantsNames.get(i));
        }
                int plantsSize=PlantsNames.size();

        for (int i = 0; i <6; i++) {
            String name = PlantsNames.get(i);
            String imageUrl = PlantsImage.get(i);

            // Create AnchorPane for each Pokemon
            AnchorPane anchorPane = new AnchorPane();
            anchorPane.setPrefHeight(150);
            anchorPane.setPrefWidth(250);
//            anchorPane.setStyle("-fx-background-color: orange");
            anchorPane.getStyleClass().add("screen3");
            anchorPane.getStyleClass().add("ash");

            ImageView imageView = new ImageView();
            imageView.setFitWidth(250);
            imageView.setFitHeight(150);
            imageView.setStyle("-fx-background-radius: 30;");
//            imageView.setStyle("-fx-border-radius: 30;");
//            imageView.setStyle("-fx-background-radius: 30 30 30 30 ");
            imageView.setPreserveRatio(false);


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
                            "-fx-background-radius: 30 30 30 30;" +
                            "-fx-border-radius: 30 30 30 30;" +
                            "-fx-border-color: rgba(255, 255, 255, 0.66);"
            );



            StackPane stackPane = new StackPane();
            stackPane.setPrefHeight(143);
            stackPane.setPrefWidth(253);
            stackPane.setStyle("-fx-background-radius: 30; -fx-background-color: transparent;");
            StackPane.setAlignment(plantNameLabel, Pos.BOTTOM_CENTER);
            StackPane.setMargin(plantNameLabel, new Insets(0, 0, 0, 0));

            stackPane.getChildren().addAll( imageView,plantNameLabel);
            anchorPane.getChildren().add(stackPane);


            // Position the label at the bottom of the anchor pane
//            AnchorPane.setBottomAnchor(plantNameLabel, 5.0);
//            AnchorPane.setLeftAnchor(plantNameLabel, 0.0);
//            AnchorPane.setRightAnchor(plantNameLabel, 0.0);
//
//            anchorPane.getChildren().add(plantNameLabel);
//            pokemonNameLabel.getStyleClass().add("glass");
//
//            pokemonNameLabel.setStyle("-fx-font-family: 'Arial Rounded MT Bold'");
//
//            pokemonNameLabel.setTextFill(Color.BLACK);
//            pokemonNameLabel.setUnderline(true);
//            pokemonNameLabel.setPrefHeight(30);
//            pokemonNameLabel.setPrefWidth(100);
////            pokemonNameLabel.setLayoutX(71);
////            pokemonNameLabel.setLayoutY(14);
//            pokemonNameLabel.setAlignment(Pos.CENTER);
//            anchorPane.getChildren().add(pokemonNameLabel);
            //how to set the level in the anchorpane centre and the image also

            // Add ImageView for Pokemon image




//            // Add Button
//            Button button = new Button();
//            button.setPrefHeight(23);
//            button.setPrefWidth(99);
//            button.setLayoutX(237);
//            button.setLayoutY(219);
//            button.setText("Remove");
          //  button.setOnAction(new EventHandler<ActionEvent>() {
////                @Override
////                public void handle(ActionEvent event) {
////                    // Call the method or perform the action when the button is clicked
////                    buttonClickforRemove(name);
////                }
////            });
////
//            anchorPane.getChildren().add(button);
//
//            // Add the AnchorPane to the GridPane
            anchorPane.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                try {
                    PlantsDetailsShow(event,name, imageUrl);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            });
            postGrid.add(anchorPane, columns, rows);
            GridPane.setMargin(anchorPane, new Insets(10));

            // Increment column count
            columns++;

            // Check if the columns exceed a threshold (e.g., 3), then reset columns and increment rows
            if (columns == 6) {
                columns = 0;
                //rows++;
                break;
            }
        }
//
    }
    @FXML
    public void Catagory(ActionEvent event) throws IOException
    {
        try {
            Parent pokedexList = FXMLLoader.load(getClass().getResource("ViewAllCatagory.fxml"));
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
//    @FXML
//    private void showPlantDetails(String name, String imageUrl) {
//        Stage stage = new Stage();
//        AnchorPane anchorPane = new AnchorPane();
//        anchorPane.setPrefSize(400, 300);
//
//        // Add ImageView for plant image
//        ImageView imageView = new ImageView();
//        imageView.setFitWidth(200);
//        imageView.setFitHeight(200);
//        imageView.setPreserveRatio(true);
//        imageView.setLayoutX(100);
//        imageView.setLayoutY(50);
//
//        if (imageUrl != null && !imageUrl.isEmpty()) {
//            try {
//                Image image = new Image(new FileInputStream(imageUrl));
//                imageView.setImage(image);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//
//        // Add Label for plant name
//        Label plantNameLabel = new Label(name);
//        plantNameLabel.setLayoutX(150);
//        plantNameLabel.setLayoutY(10);
//        plantNameLabel.setStyle("-fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 20px;");
//        plantNameLabel.setTextFill(Color.BLACK);
//
//        anchorPane.getChildren().addAll(imageView, plantNameLabel);
//
//        Scene scene = new Scene(anchorPane);
//        stage.setScene(scene);
//        stage.setTitle("Plant Details");
//        stage.show();
//    }
    @FXML
    public  void PlantsDetailsShow(MouseEvent event,String name,String imagUrl) throws IOException {
        FXMLLoader welcomeLoader = new FXMLLoader(getClass().getResource("DetailsShow.fxml"));
        Parent welcomeRoot = welcomeLoader.load();
        Document query = new Document("name", name);
        DetailsShow detailsControl = welcomeLoader.getController();
        detailsControl.initialize(query);

        Scene scene = new Scene(welcomeRoot);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//Stage stage= new Stage();

        stage.setScene(scene);
        stage.setTitle("Plant Details");
        stage.show();
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
    @FXML
    public void getEmail(String email2,String password)
    {
        this.email=email2;
        this.pass=password;
                ProfileController symptompControl = new ProfileController();
        symptompControl.setDAta(email,pass);
        System.out.println("nul"+email);
        System.out.println(pass);
    }
    @FXML
    public void setMyprofile(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MyProfile.fxml"));
        // Parent root = FXMLLoader.load(getClass().getResource("IssuesShow.fxml"));
//        ProfileController symptompControl = new ProfileController();
//        symptompControl.setDAta(email,pass);

        System.out.println(email);
        System.out.println(pass);

        // Create a new scene
        Scene scene = new Scene(root);

        scene.getStylesheets().add(getClass().getResource("stylecss.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("sylesheet.css").toExternalForm());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

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
}
