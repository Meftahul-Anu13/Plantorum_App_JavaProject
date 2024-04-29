package com.example.plantorum;

import com.mongodb.client.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.bson.Document;

import java.lang.reflect.Type;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Label Thanks;
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

    // Find all documents in the collection
    FindIterable<Document> documents = collection.find();
    Document doc=collection.find().first();
    String scientificName = doc.getString("scientific_name");
    String type = doc.getString("type");

//        System.out.println("Scientific Name: " + scientificName);
//        System.out.println("Type: " + type);

    // Iterate over the documents and print them
//        for (Document document : documents) {
//        System.out.println(document.toJson());
//
//    }

    // Close the MongoDB client
    // client.close();

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText(scientificName);
        Thanks.setText(type);

    }
}