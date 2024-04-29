package com.example.plantorum;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

public class logout {
    @FXML
    private Button logout;
    @FXML
    private AnchorPane ScenePane;

    Stage stage;

    public void Logout(ActionEvent event)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("LOGOUT");
        alert.setHeaderText("Are you sure you want to log out?");
        alert.setContentText("Do you want to save your info?");
        if(alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) ScenePane.getScene().getWindow();
            System.out.println("You're logged out :( ");
            stage.close();
        }
    }
}
