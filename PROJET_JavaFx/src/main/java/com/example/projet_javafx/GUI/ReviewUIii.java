//package com.example.projet_javafx.GUI;
//import com.example.projet_javafx.Entities.Review;
//import com.example.projet_javafx.Services.ServiceReview;
//import javafx.application.Application;
//import javafx.geometry.Insets;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.HBox;
//import javafx.stage.Stage;
//import java.sql.SQLException;
//import java.sql.Timestamp;
//import java.util.ArrayList;
//
//public class ReviewUI extends Application {
//    private ServiceReview serviceReview = new ServiceReview();
//    private TextArea resultTextArea;
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//    @Override
//    public void start(Stage primaryStage) {
//        primaryStage.setTitle("Review Management");
//
//        // Create UI components
//        BorderPane borderPane = new BorderPane();
//        borderPane.setPadding(new Insets(10, 10, 10, 10));
//// Set the background color of the BorderPane
//        borderPane.setStyle("-fx-background-color: #d48ed7;");
//        HBox buttonBox = new HBox(10);
//        buttonBox.setPadding(new Insets(10, 10, 10, 10));
//
//        Button addButton = createStyledButton("Add Review", "button1");
//        Button editButton = createStyledButton("Edit Review", "button2");
//        Button deleteButton = createStyledButton("Delete Review", "button3");
//        Button displayButton = createStyledButton("Display Reviews", "button4");
//        addButton.setStyle("-fx-background-color: linear-gradient(to top left, #c71ecd, #D4CEE3);");
//        editButton.setStyle("-fx-background-color: linear-gradient(to top left, #c71ecd, #D4CEE3);");
//        deleteButton.setStyle("-fx-background-color: linear-gradient(to top left, #c71ecd, #D4CEE3);");
//        displayButton.setStyle("-fx-background-color: linear-gradient(to top left, #c71ecd, #D4CEE3);");
//        buttonBox.getChildren().addAll(addButton, editButton, deleteButton, displayButton);
//
//        resultTextArea = new TextArea();
//
//        // Add components to borderPane
//        borderPane.setTop(buttonBox);
//        borderPane.setCenter(resultTextArea);
//
//        // Add event handlers for the buttons
//        addButton.setOnAction(event -> addReview());
//        editButton.setOnAction(event -> editReview());
//        deleteButton.setOnAction(event -> deleteReview());
//        displayButton.setOnAction(event -> displayReviews());
//         // Set up the scene
//        Scene scene = new Scene(borderPane, 600, 400);
//
//        // Set the background color of the Scene (optional, depends on your preference)
//        scene.setFill(javafx.scene.paint.Color.web("#d48ed7"));
//
//        scene.getStylesheets().add("path/to/your/stylesheet.css"); // Replace with your actual stylesheet path
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//
//    private Button createStyledButton(String text, String styleClass) {
//        Button button = new Button(text);
//        button.getStyleClass().add(styleClass);
//        return button;
//    }
//
//    private void addReview() {
//        // Implement logic to add a review
//        try {
//            int userID = getUserInput("User ID:");
//            int restaurantID = getUserInput("Restaurant ID:");
//            double note = getDoubleInput("Note:");
//            String commentaire = getStringInput("Commentaire:");
//            Timestamp dateEvaluation = new Timestamp(System.currentTimeMillis());
//
//            Review newReview = new Review(0, userID, restaurantID, note, commentaire, dateEvaluation);
//            serviceReview.ajouter(newReview);
//
//            // Refresh the displayed reviews
//            displayReviews();
//        } catch (NumberFormatException | SQLException e) {
//            displayError("Error: " + e.getMessage());
//        }
//    }
//
//    private void editReview() {
//        // Implement logic to edit a review
//        try {
//            int reviewID = getUserInput("Review ID to edit:");
//            Review existingReview = serviceReview.get(reviewID);
//
//            if (existingReview != null) {
//                int userID = getUserInput("User ID (current: " + existingReview.getUserID() + "):");
//                int restaurantID = getUserInput("Restaurant ID (current: " + existingReview.getRestaurantID() + "):");
//                double note = getDoubleInput("Note (current: " + existingReview.getNote() + "):");
//                String commentaire = getStringInput("Commentaire (current: " + existingReview.getCommentaire() + "):");
//
//                Review updatedReview = new Review(reviewID, userID, restaurantID, note, commentaire, existingReview.getDateEvaluation());
//                serviceReview.update(updatedReview);
//
//                // Refresh the displayed reviews
//                displayReviews();
//            } else {
//                displayError("Review not found for ID: " + reviewID);
//            }
//        } catch (NumberFormatException | SQLException e) {
//            displayError("Error: " + e.getMessage());
//        }
//    }
//
//    private void deleteReview() {
//        // Implement logic to delete a review
//        try {
//            int reviewID = getUserInput("Review ID to delete:");
//            serviceReview.delete(reviewID);
//
//            // Refresh the displayed reviews
//            displayReviews();
//        } catch (NumberFormatException | SQLException e) {
//            displayError("Error: " + e.getMessage());
//        }
//    }
//
//    private void displayReviews() {
//        // Implement logic to display all reviews
//        try {
//            ArrayList<Review> reviews = serviceReview.readAll();
//            resultTextArea.clear();
//            for (Review review : reviews) {
//                resultTextArea.appendText(review.toString() + "\n");
//            }
//        } catch (SQLException e) {
//            displayError("Error: " + e.getMessage());
//        }
//    }
//
//    private int getUserInput(String prompt) {
//        TextInputDialog dialog = new TextInputDialog();
//        dialog.setHeaderText(prompt);
//        dialog.showAndWait();
//        return Integer.parseInt(dialog.getResult());
//    }
//
//    private double getDoubleInput(String prompt) {
//        TextInputDialog dialog = new TextInputDialog();
//        dialog.setHeaderText(prompt);
//        dialog.showAndWait();
//        return Double.parseDouble(dialog.getResult());
//    }
//
//    private String getStringInput(String prompt) {
//        TextInputDialog dialog = new TextInputDialog();
//        dialog.setHeaderText(prompt);
//        dialog.showAndWait();
//        return dialog.getResult();
//    }
//
//    private void displayError(String message) {
//        resultTextArea.setText(message);
//    }
//}
