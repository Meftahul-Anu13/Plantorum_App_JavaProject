package com.example.plantorum;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.bson.Document;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.IntStream;

public class TimerController {

    @FXML
    private GridPane calendarGrid;

    @FXML
    private ComboBox<Integer> yearComboBox;

    @FXML
    private ComboBox<String> monthComboBox;

    private YearMonth currentYearMonth;
    private Map<LocalDateTime, TaskDetails> tasks; // Use LocalDateTime for tasks with time
    private Map<LocalDate, Label> dateLabels; // Map to keep track of date labels

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> userCollection;
    private String currentUserId;

    public void initialize() {
        tasks = new HashMap<>();
        dateLabels = new HashMap<>(); // Initialize the dateLabels map
        currentYearMonth = YearMonth.now();

        // Initialize MongoDB connection
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("anodb");
        userCollection = database.getCollection("users");

        IntStream.rangeClosed(1900, 2100).forEach(year -> yearComboBox.getItems().add(year));
        yearComboBox.setValue(currentYearMonth.getYear());

        monthComboBox.getItems().addAll("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
        monthComboBox.setValue(currentYearMonth.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH));

        yearComboBox.setOnAction(event -> updateCalendar());
        monthComboBox.setOnAction(event -> updateCalendar());

        populateCalendar();
        startAlarmChecker();
    }

    public void setCurrentUserId(String userId) {
        this.currentUserId = userId;
        loadUserTasks(userId);
    }

    private void loadUserTasks(String userId) {
        tasks.clear();
        Document user = userCollection.find(Filters.eq("userId", userId)).first();
        if (user != null) {
            List<Document> taskList = (List<Document>) user.get("tasks");
            for (Document taskDoc : taskList) {
                LocalDateTime dateTime = LocalDateTime.parse(taskDoc.getString("dateTime"));
                String taskDetails = taskDoc.getString("taskDetails");
                Map<String, Boolean> checkboxes = new HashMap<>();
                Document checkboxDoc = (Document) taskDoc.get("checkboxes");
                for (Map.Entry<String, Object> entry : checkboxDoc.entrySet()) {
                    checkboxes.put(entry.getKey(), (Boolean) entry.getValue());
                }
                tasks.put(dateTime, new TaskDetails(taskDetails, checkboxes));
            }
        }
        populateCalendar();
    }

    private void saveUserTask(String userId, LocalDateTime dateTime, String taskDetails, Map<String,Boolean > checkboxes) {
        // Create the task document with the required fields
        Document taskDoc = new Document("dateTime", dateTime.toString())
                .append("taskDetails", taskDetails);
              //  .append("checkboxes", new Document(checkboxes));

        // Update the user document by pushing the new task to the tasks array
        userCollection.updateOne(
                Filters.eq("userId", userId),
                Updates.combine(
                        Updates.push("tasks", taskDoc),
                        Updates.setOnInsert("userId", userId)
                ),
                new UpdateOptions().upsert(true)
        );
    }

    private void removeUserTask(String userId, LocalDateTime dateTime) {
        // Remove the specific task document from the user's tasks array
        userCollection.updateOne(
                Filters.eq("userId", userId),
                Updates.pull("tasks", Filters.eq("dateTime", dateTime.toString()))
        );
    }

    public void createNewUser(String userId) {
        Document newUser = new Document("userId", userId)
                .append("tasks", new ArrayList<Document>());
        userCollection.insertOne(newUser);
    }

    private void updateCalendar() {
        int selectedYear = yearComboBox.getValue();
        String selectedMonth = monthComboBox.getValue();
        currentYearMonth = YearMonth.of(selectedYear, monthComboBox.getItems().indexOf(selectedMonth) + 1);
        populateCalendar();
    }

    private void populateCalendar() {
        calendarGrid.getChildren().clear(); // Clear previous calendar entries
        dateLabels.clear(); // Clear the dateLabels map

        // Add day headers
        String[] dayNames = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        for (int i = 0; i < dayNames.length; i++) {
            Label dayLabel = new Label(dayNames[i]);
            dayLabel.setStyle("-fx-font-weight: bold; -fx-alignment: center;");
            calendarGrid.add(dayLabel, i, 0);
        }

        LocalDate firstDayOfMonth = currentYearMonth.atDay(1);
        int dayOfWeek = firstDayOfMonth.getDayOfWeek().getValue() % 7; // Adjust to start week on Sunday

        for (int day = 1; day <= currentYearMonth.lengthOfMonth(); day++) {
            int row = (day + dayOfWeek - 1) / 7 + 1; // Start from the second row
            int col = (day + dayOfWeek - 1) % 7;
            LocalDate date = LocalDate.of(currentYearMonth.getYear(), currentYearMonth.getMonth(), day);
            Label dayLabel = new Label(String.valueOf(day));
            dayLabel.getStyleClass().add("date-label"); // Apply custom CSS class for styling

            // Check if there are any tasks for this date
            boolean hasTask = tasks.entrySet().stream().anyMatch(entry -> entry.getKey().toLocalDate().equals(date));
            if (hasTask) {
                dayLabel.setStyle("-fx-background-color: #ffcc80; -fx-font-weight: bold; -fx-padding: 10px 15px; -fx-background-radius: 5px; -fx-alignment: center;"); // Orange background with bold font
            }

            dayLabel.setOnMouseClicked(event -> showTaskDialog(date));
            calendarGrid.add(dayLabel, col, row);
            dateLabels.put(date, dayLabel); // Keep track of the label for this date
        }
    }

    private void showTaskDialog(LocalDate date) {
        Stage dialog = new Stage();
        VBox dialogVbox = new VBox(25);
        dialogVbox.setStyle("-fx-background-color: #f0f4c3; -fx-padding: 15; -fx-border-radius: 10; -fx-background-radius: 10;");

        Label dialogTitle = new Label("Tasks for " + date.toString());
        dialogTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #00796b;");
        dialogVbox.getChildren().add(dialogTitle);

        TextArea taskArea = new TextArea();
        taskArea.setPrefHeight(100);
        tasks.entrySet().stream()
                .filter(entry -> entry.getKey().toLocalDate().equals(date))
                .forEach(entry -> {
                    taskArea.appendText(entry.getKey().toLocalTime() + ": " + entry.getValue().taskDetails + "\n");
                    entry.getValue().checkboxes.forEach((name, checked) -> taskArea.appendText(name + ": " + (checked ? "Checked" : "Unchecked") + "\n"));
                });
        taskArea.setStyle("-fx-background-color: #ffffff; -fx-border-color: #b2ebf2; -fx-border-width: 2; -fx-border-radius: 5;");
        dialogVbox.getChildren().add(taskArea);

        HBox timeBox = new HBox(10);
        ComboBox<Integer> hourComboBox = new ComboBox<>();
        ComboBox<Integer> minuteComboBox = new ComboBox<>();
        ComboBox<String> amPmComboBox = new ComboBox<>();
        IntStream.rangeClosed(1, 12).forEach(hour -> hourComboBox.getItems().add(hour));
        IntStream.range(0, 60).forEach(minute -> minuteComboBox.getItems().add(minute));
        amPmComboBox.getItems().addAll("AM", "PM");
        timeBox.getChildren().addAll(new Label("Hour:"), hourComboBox, new Label("Minute:"), minuteComboBox, amPmComboBox);
        timeBox.setStyle("-fx-background-color: #ffffff; -fx-border-color: #b2ebf2; -fx-border-width: 2; -fx-border-radius: 5; -fx-padding: 5;");
        dialogVbox.getChildren().add(timeBox);

        TextField taskField = new TextField();
        taskField.setStyle("-fx-background-color: #ffffff; -fx-border-color: #b2ebf2; -fx-border-width: 2; -fx-border-radius: 5;");
        dialogVbox.getChildren().add(taskField);

        // Add checkboxes
        VBox checkboxContainer = new VBox(5);
        checkboxContainer.setStyle("-fx-background-color: #ffffff; -fx-border-color: #b2ebf2; -fx-border-width: 2; -fx-border-radius: 5; -fx-padding: 5;");
        List<CheckBox> checkBoxes = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            if(i==1)
            {
                CheckBox checkBox = new CheckBox("Sunlight " );
                checkBox.setStyle("-fx-text-fill: #00796b;");
                checkBoxes.add(checkBox);
                checkboxContainer.getChildren().add(checkBox);
            }
            if(i==2)
            {
                CheckBox checkBox = new CheckBox("Watering");
                checkBox.setStyle("-fx-text-fill: #00796b;");
                checkBoxes.add(checkBox);
                checkboxContainer.getChildren().add(checkBox);
            }
            if(i==3)
            {
                CheckBox checkBox = new CheckBox("Pot changing ");
                checkBox.setStyle("-fx-text-fill: #00796b;");
                checkBoxes.add(checkBox);
                checkboxContainer.getChildren().add(checkBox);
            }
        }
        dialogVbox.getChildren().add(checkboxContainer);

        Button saveButton = new Button("Save Task");
        saveButton.setStyle("-fx-background-color: #00796b; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-border-radius: 5;");
        saveButton.setOnAction(event -> {
            int hour = hourComboBox.getValue();
            if (hour == 12) {
                hour = 0;
            }
            if ("PM".equals(amPmComboBox.getValue())) {
                hour += 12;
            }
            LocalDateTime dateTime = LocalDateTime.of(date, LocalTime.of(hour, minuteComboBox.getValue()));
            Map<String, Boolean> checkboxStates = new HashMap<>();
            for (CheckBox checkBox : checkBoxes) {
                checkboxStates.put(checkBox.getText(), checkBox.isSelected());
            }
            tasks.put(dateTime, new TaskDetails(taskField.getText(), checkboxStates));
            saveUserTask(currentUserId, dateTime, taskField.getText(), checkboxStates); // Save the task to MongoDB
            dialog.close();
            populateCalendar(); // Refresh the calendar to reflect updated tasks
        });
        dialogVbox.getChildren().add(saveButton);

        Scene dialogScene = new Scene(dialogVbox, 400, 500);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    private void startAlarmChecker() {
        Timer timer = new Timer(true); // Daemon thread
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                LocalDateTime now = LocalDateTime.now();
                tasks.entrySet().stream()
                        .filter(entry -> entry.getKey().isBefore(now.plusMinutes(1)) && entry.getKey().isAfter(now.minusMinutes(1)))
                        .forEach(entry -> showAlert(entry.getValue().taskDetails, entry.getKey().toLocalDate(), entry.getKey().toLocalTime(), entry.getValue().checkboxes));
            }
        }, 0, 60000); // Check every minute
    }

    private void showAlert(String task, LocalDate date, LocalTime time, Map<String, Boolean> checkboxes) {
        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("task_alert.fxml"));
                Parent root = loader.load();

                TaskAlertController controller = loader.getController();
                controller.setTaskDetails(task, date.toString(), time.toString(), checkboxes);
                controller.setCalendarController(this); // Pass the CalendarController to TaskAlertController

                Stage stage = new Stage();
                stage.setTitle("Task Alert");
                stage.setScene(new Scene(root));
                stage.getScene().getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
                stage.setOnHidden(event -> {
                    resetDateStyle(date); // Reset the date style when the alert window is closed
                    removeUserTask(currentUserId, LocalDateTime.of(date, time)); // Remove the task from the database
                });
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void resetDateStyle(LocalDate date) {
        Label dayLabel = dateLabels.get(date);
        if (dayLabel != null) {
            dayLabel.setStyle(null); // Reset the style
            dayLabel.getStyleClass().add("date-label"); // Reapply the default style
        }
    }

    public static class TaskDetails {
        public String taskDetails;
        public Map<String, Boolean> checkboxes;

        public TaskDetails(String taskDetails, Map<String, Boolean> checkboxes) {
            this.taskDetails = taskDetails;
            this.checkboxes = checkboxes;
        }
    }
}
