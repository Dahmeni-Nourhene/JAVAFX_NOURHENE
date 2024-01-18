package com.example.projet_javafx;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class MainApplication extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        MainApplication.primaryStage = primaryStage;
        showReviewPage(); // Vous pouvez changer cela pour afficher la page par défaut
    }


        public static void main(String[] args) {
            launch(args);
        }

    public static void showReviewPage() {
        try {
            Parent root = FXMLLoader.load(MainApplication.class.getResource("ReviewUI.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Review Management");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showManageReviewsPage() {
        try {
            Parent root = FXMLLoader.load(MainApplication.class.getResource("ReviewUI.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Manage Reviews");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showEvaluateReviewsPage() {
        try {
            Parent root = FXMLLoader.load(MainApplication.class.getResource("ReviewUI.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Evaluate Reviews");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showManageReservationsPage() {
        try {
            Parent root = FXMLLoader.load(MainApplication.class.getResource("ReviewUI.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Manage Reservations");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
