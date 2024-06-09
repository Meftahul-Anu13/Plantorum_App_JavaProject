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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CatagoryControl {
    @FXML
    private ImageView catbg;

    @FXML
    private Label catagorylabel;
    @FXML
    private ImageView bilaiimg;
    @FXML
    private ImageView bilaierimag;
    @FXML
    private Button backbtn;
    @FXML
    private GridPane postgrid;
    private MongoDatabase database;
    private MongoCollection<Document> plantCollection;



    public void initialize(Document query,String str)
    {
//

        try {
            Image image = new Image(new FileInputStream("D:\\4rth Sem Product\\JavaProject\\Plantorum_App_JavaProject\\Plantorum\\src\\image\\catgoryuporerimg'.png"));
//         Image img = new Image(new FileInputStream());
            catbg .setImage(image);
            catbg.setPreserveRatio(false); // Preserve the aspect ratio
        } catch (Exception e) {
            System.out.println("Failed to load image: " + e.getMessage());
        }

catagorylabel.setText(str);
        catagorylabel.getStyleClass().add("glass");
        catagorylabel.setStyle(
                "-fx-font-family: 'Arial Rounded MT Bold';" +
                        "-fx-text-fill: black;" + // Note: Color.BLACK is replaced with CSS color
                        "-fx-alignment: center;" +
                        "-fx-border-width: 4;" +

                        "-fx-font-size: 24;"
        );

        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("anodb");
        plantCollection = database.getCollection("CollectionsFilter");
        List<String> plantcatagory = new ArrayList<>();
        List<String> PlantsImage = new ArrayList<>();
        for (Document document : plantCollection.find(query)) {

            plantcatagory.add(document.getString("name"));
        }
        for (Document document : plantCollection.find(query)) {
            PlantsImage.add(document.getString("image_url"));
        }

        int columns = 0;
        int rows = 1;

        for (int i = 0; i <plantcatagory.size(); i++) {
            String name = plantcatagory.get(i);
            String imageUrl = PlantsImage.get(i);


            // Create AnchorPane for each Pokemon
            AnchorPane anchorPane = new AnchorPane();
            anchorPane.setPrefHeight(150);
            anchorPane.setPrefWidth(250
            );
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

            Document query1 = new Document("name", name);


            if (imageUrl != null && !imageUrl.isEmpty()) {
                try {
                    Image image = new Image(new FileInputStream(imageUrl));
                    imageView.setImage(image);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            anchorPane.getChildren().add(imageView);

            Label pokemonNameLabel = new Label(name);
            Label plantNameLabel = new Label(name);
            plantNameLabel.getStyleClass().add("glass");
            plantNameLabel.setStyle("-fx-font-family: 'Arial Rounded MT Bold'");
            plantNameLabel.setTextFill(Color.BLACK);
            // plantNameLabel.setUnderline(true);
            plantNameLabel.setAlignment(Pos.CENTER);
            plantNameLabel.setMaxWidth(Double.MAX_VALUE);

            StackPane stackPane = new StackPane();
            stackPane.setPrefHeight(150);
            stackPane.setPrefWidth(250);
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
//            anchorPane.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
//                try {
//                    PlantsDetailsShow(event,name, imageUrl);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//
//            });
            postgrid.add(anchorPane, columns, rows);
            GridPane.setMargin(anchorPane, new Insets(15));

            // Increment column count
            columns++;

            // Check if the columns exceed a threshold (e.g., 3), then reset columns and increment rows
            if (columns == 3) {
                columns = 0;
                rows++;

            }
            anchorPane.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                try {
                    PlantsDetailsShow(event,name, imageUrl);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            });
        }
    }
    @FXML
    public void backClick(ActionEvent event) throws IOException {
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

//    private void backtoprev(ActionEvent event) throws IOException {
//        FXMLLoader welcomeLoader = new FXMLLoader(getClass().getResource("ViewAllCatagory.fxml"));
//        Parent welcomeRoot = welcomeLoader.load();
//
//        ViewAllCatagory detailsControl = welcomeLoader.getController();
//        //etailsControl.initialize(query,str);
//
//        Scene scene = new Scene(welcomeRoot);
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
////Stage stage= new Stage();
//
//        stage.setScene(scene);
//        stage.setTitle("Plant Details");
//        stage.show();
//    }
}
