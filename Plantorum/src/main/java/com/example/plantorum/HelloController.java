package com.example.plantorum;

import com.mongodb.client.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.bson.Document;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Type;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Label Thanks;
    @FXML
            private ImageView bgimage;
    @FXML
    private ImageView plantorumimg;
    MongoClient client= MongoClients.create("mongodb://localhost:27017");
    //MongoDatabase db=client.getDatabase("PlantorumDB");
//        MongoDatabase db=client.getDatabase("anodb");
//
//        MongoCollection col= db.getCollection("Plants");
//        //col.countDocuments()
//        Document doc=new Document("_id","1").
//        col.insertOne(doc);
//        System.out.println( col.countDocuments());
    MongoDatabase database = client.getDatabase("anodb");

    // Access the collection
    MongoCollection<Document> collection = database.getCollection("Plants");

    @FXML
    public void initialize() {
//        ImageView imageView = new ImageView();
        try {
            // Load the image
            // Image image = new Image(new FileInputStream("D:\\4rth Sem Product\\JavaProject\\Pokedex\\src\\main\\java\\com\\example\\pokedex\\Image\\bulbasaur.png"));
            // Image image = new Image(new FileInputStream("D:\\4rth Sem Product\\JavaProject\\Plantorum\\src\\image\\Parsle.jpg");
            Image image = new Image(new FileInputStream("D:\\4rth Sem Product\\JavaProject\\Plantorum_App_JavaProject\\Plantorum\\src\\image\\PlantsDetailsBg.jpg"));
Image img=new Image(new FileInputStream("D:\\4rth Sem Product\\JavaProject\\Plantorum_App_JavaProject\\Plantorum\\src\\image\\plantorumwritthnbg.png"));
//            imageView.setImage(image);
//            imageView.setFitWidth(70); // Set the width of the image
//            imageView.setPreserveRatio(true); // Preserve the aspect ratio of the image
//            imageView.setFitHeight(100);
            bgimage.setImage(image);
            plantorumimg.setImage(img);


        } catch (FileNotFoundException e) {
           // System.out.println("Unable to locate image file: " + item.toLowerCase() + ".jpg");
        }
    }



    @FXML
    protected void onHelloButtonClick() {
//        welcomeText.setText(scientificName);
//        Thanks.setText(type);

    }
}