package com.example.plantorum;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;

import java.util.List;
import java.util.Map;

public class IssuesShow {

    @FXML
    private TreeView<String> treeView;

    @FXML
    private Label detailsLabel;

    private List<Map<String, Object>> data;  // Load your JSON data here

    public void setPart(String part) {
        TreeItem<String> rootItem = new TreeItem<>(part);
        for (Map<String, Object> partData : data) {
            if (partData.get("part").equals(part)) {
                List<Map<String, String>> issues = (List<Map<String, String>>) partData.get("issues");
                for (Map<String, String> issue : issues) {
                    TreeItem<String> issueItem = new TreeItem<>(issue.get("issue"));
                    rootItem.getChildren().add(issueItem);
                }
                break;
            }
        }
        treeView.setRoot(rootItem);

        treeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                String selectedText = newValue.getValue();
                String details = getDetails(part, selectedText);
                detailsLabel.setText(details);
            }
        });
    }

    private String getDetails(String part, String issue) {
        for (Map<String, Object> partData : data) {
            if (partData.get("part").equals(part)) {
                List<Map<String, String>> issues = (List<Map<String, String>>) partData.get("issues");
                for (Map<String, String> issueData : issues) {
                    if (issueData.get("issue").equals(issue)) {
                        return "Affected Area: " + issueData.get("affected_area") +
                                "\nDisease Name: " + issueData.get("disease_name") +
                                "\nInfo: " + issueData.get("info") +
                                "\nTreatment: " + issueData.get("treatment");
                    }
                }
            }
        }
        return "No details available.";
    }
}
