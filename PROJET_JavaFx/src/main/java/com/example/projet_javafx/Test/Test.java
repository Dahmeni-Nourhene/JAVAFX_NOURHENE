package com.example.projet_javafx.Test;


import com.example.projet_javafx.Entities.Restaurant;
import com.example.projet_javafx.Entities.Review;
import com.example.projet_javafx.Entities.User;
import com.example.projet_javafx.Services.ServiceRestaurant;
import com.example.projet_javafx.Services.ServiceReview;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        ServiceRestaurant restaurantService = new ServiceRestaurant();
        User user = new User();
        Restaurant restaurant = new Restaurant();
        // Test ajout restaurant
        Restaurant restaurantToAdd = new Restaurant("Pizzaria 1", "Borj Cedria", "bnina", 4.5);

        try {
            restaurantService.ajouter(restaurantToAdd);
            System.out.println("Restaurant ajouté");
        } catch (SQLException e) {
            System.out.println("Erreur ajout : " + e.getMessage());
        }

        // Test affiche restaurant
        try {
            ArrayList<Restaurant> restaurants = restaurantService.readAll();
            System.out.println("Liste des restaurants :");
            for (Restaurant restaurantt : restaurants) {
                System.out.println(restaurantt);
            }
        } catch (SQLException e) {
            System.out.println("Erreur affichage : " + e.getMessage());
        }

        System.out.println("");
        System.out.println("----------------------------------");
        System.out.println("");

        // Test ajout review
        ServiceReview serviceReview = new ServiceReview();

        // Ajout d'une nouvelle review avec la date actuelle
        try {
            // Utilisez directement Timestamp pour capturer la date actuelle
            Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

            Review newReview = new Review(1, 1, 2, 1, "Mauvaise Service", currentTimestamp);
            serviceReview.ajouter(newReview);
            System.out.println("Nouvelle review ajoutée avec succès.");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de la review : " + e.getMessage());
        }

        // Lecture de toutes les reviews
        try {
            ArrayList<Review> reviews = serviceReview.readAll();
            System.out.println("Liste des reviews :");
            for (Review review : reviews) {
                // Utilisez un format différent pour afficher la date s'il n'est pas null
                String formattedDate = review.getDateEvaluation() != null ?
                        new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(review.getDateEvaluation()) : "null";

                System.out.println("Review{" +
                        "reviewID=" + review.getReviewID() +
                        ", userID=" + review.getUserID() +
                        ", restaurantID=" + review.getRestaurantID() +
                        ", note=" + review.getNote() +
                        ", commentaire='" + review.getCommentaire() + '\'' +
                        ", dateEvaluation=" + formattedDate +
                        '}');
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la lecture des reviews : " + e.getMessage());
        }



        // Mise à jour d'une review existante avec la date actuelle
        try {
            // Utilisez directement Timestamp pour capturer la date actuelle
            Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

            Review reviewToUpdate = new Review(1, 1, 2, 1, "Maivaise", currentTimestamp);
            serviceReview.update(reviewToUpdate);
            System.out.println("Review mise à jour avec succès.");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour de la review : " + e.getMessage());
        }


        // Lecture  de  la review mise à jour
        try {
            Review updatedReview = serviceReview.get(1);
            System.out.println("Review mise à jour : " + updatedReview);
        } catch (SQLException e) {
            System.out.println("Erreur lors de la lecture de la review mise à jour : " + e.getMessage());
        }

        // Suppression de la review
        try {
            serviceReview.delete(1);
            System.out.println("Review supprimée avec succès.");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de la review : " + e.getMessage());
        }
    }
}
