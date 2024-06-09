package com.example.plantorum;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.util.Map;

public class TaskAlertController {

    @FXML
    private Label taskLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label timeLabel;

    @FXML
    private VBox checkboxContainer;

    private TimerController calendarController;
    private LocalDate alertDate;

    public void setTaskDetails(String task, String date, String time, Map<String, Boolean> checkboxes) {
        taskLabel.setText(task);
        dateLabel.setText(date);
        timeLabel.setText(time);

        checkboxes.forEach((name, checked) -> {
            CheckBox checkBox = new CheckBox(name);
            checkBox.setSelected(checked);
            checkBox.setDisable(true); // Disable the checkbox to indicate it's just for viewing
            checkboxContainer.getChildren().add(checkBox);
        });

        // Add event listener for window close after the scene has been set
        checkboxContainer.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.windowProperty().addListener((obs, oldWindow, newWindow) -> {
                    if (newWindow != null) {
                        newWindow.setOnHidden(event -> {
                            if (calendarController != null && alertDate != null) {
                                calendarController.resetDateStyle(alertDate);
                            }
                        });
                    }
                });
            }
        });
    }

    public void setCalendarController(TimerController calendarController) {
        this.calendarController = calendarController;
        this.alertDate = LocalDate.parse(dateLabel.getText());
    }

    @FXML
    private void initialize() {
        // Initialization logic if needed
    }
}
