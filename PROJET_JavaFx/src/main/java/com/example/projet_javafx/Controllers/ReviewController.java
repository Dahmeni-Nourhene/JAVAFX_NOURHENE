package com.example.projet_javafx.Controllers;

import com.example.projet_javafx.Entities.Review;
import com.example.projet_javafx.Services.ServiceReview;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import com.example.projet_javafx.MainApplication;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ReviewController {

    @FXML
    private TableView<Review> reviewTableView;

    @FXML
    private TextField userIdTextField;

    @FXML
    private TextField restaurantIdTextField;

    @FXML
    private TextField noteTextField;

    @FXML
    private TextField commentaireTextField;

    @FXML
    private TextField commentSearchTextField; // Added TextField for comment search

    @FXML
    private TableColumn<Review, Integer> userIdColumn;

    @FXML
    private TableColumn<Review, Integer> restaurantIdColumn;

    @FXML
    private TableColumn<Review, Double> noteColumn;

    @FXML
    private TableColumn<Review, String> commentaireColumn;

    @FXML
    private TableColumn<Review, Void> operationsColumn;

    private final ServiceReview serviceReview = new ServiceReview();

    @FXML
    public void initialize() {
        ObservableList<Review> reviewList = FXCollections.observableArrayList();

        try {
            reviewList.addAll(serviceReview.readAll());
        } catch (SQLException e) {
            e.printStackTrace(); // Gérer l'exception de manière appropriée
        }

        reviewTableView.setRowFactory(param -> new ReviewTableRow());

        reviewTableView.setItems(reviewList);

        userIdColumn.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getUserID()));
        restaurantIdColumn.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getRestaurantID()));
        noteColumn.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getNote()));
        commentaireColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCommentaire()));

        operationsColumn.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("❌");

            {
                deleteButton.getStyleClass().add("delete-button");
                deleteButton.setOnAction(event -> {
                    Review review = getTableView().getItems().get(getIndex());
                    handleDeleteReview(review);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                }
            }
        });

        reviewTableView.getSelectionModel()
                .selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        handleReviewSelection(newSelection);
                    }
                });
    }

    private class ReviewTableRow extends javafx.scene.control.TableRow<Review> {

        @Override
        protected void updateItem(Review review, boolean empty) {
            super.updateItem(review, empty);

            if (empty || review == null) {
                setText(null);
                setGraphic(null);
            } else {
                // Gérer l'affichage des données de la ligne (si nécessaire)
            }
        }
    }

    private void handleDeleteReview(Review review) {
        try {
            serviceReview.delete(review.getReviewID());
            initialize();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void handleReviewSelection(Review review) {
        userIdTextField.setText(String.valueOf(review.getUserID()));
        restaurantIdTextField.setText(String.valueOf(review.getRestaurantID()));
        noteTextField.setText(String.valueOf(review.getNote()));
        commentaireTextField.setText(review.getCommentaire());
    }

    @FXML
    private void handleAddReview() {
        int userId = Integer.parseInt(userIdTextField.getText());
        int restaurantId = Integer.parseInt(restaurantIdTextField.getText());
        double note = Double.parseDouble(noteTextField.getText());
        String commentaire = commentaireTextField.getText();

        Review newReview = new Review(userId, restaurantId, note, commentaire);

        try {
            serviceReview.ajouter(newReview);
            initialize();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        userIdTextField.clear();
        restaurantIdTextField.clear();
        noteTextField.clear();
        commentaireTextField.clear();
    }

    @FXML
    private void handleUpdateReview() {
        int userId = Integer.parseInt(userIdTextField.getText());
        int restaurantId = Integer.parseInt(restaurantIdTextField.getText());
        double note = Double.parseDouble(noteTextField.getText());
        String commentaire = commentaireTextField.getText();

        Review updatedReview = new Review(userId, restaurantId, note, commentaire);

        try {
            Review selectedReview = reviewTableView.getSelectionModel().getSelectedItem();
            if (selectedReview != null) {
                updatedReview.setReviewID(selectedReview.getReviewID());
                serviceReview.update(updatedReview);
                initialize();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        userIdTextField.clear();
        restaurantIdTextField.clear();
        noteTextField.clear();
        commentaireTextField.clear();
    }

    @FXML
    private void handleSearchByComment() {
        String commentSearchText = commentSearchTextField.getText();

        try {
            List<Review> searchResults = serviceReview.searchByComment(commentSearchText);
            ObservableList<Review> searchResultsList = FXCollections.observableArrayList(searchResults);
            reviewTableView.setItems(searchResultsList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleManageReviewsButtonAction() throws IOException {
        MainApplication.showManageReviewsPage();
    }

    @FXML
    private void handleEvaluateReviewsButtonAction() throws IOException {
        MainApplication.showEvaluateReviewsPage();
    }

    @FXML
    private void handleManageReservationsButtonAction() throws IOException {
        MainApplication.showManageReservationsPage();
    }
}
