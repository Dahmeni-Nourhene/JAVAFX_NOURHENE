package com.example.projet_javafx.Entities;


import java.sql.Timestamp;
public class Review {
    private int reviewID;
    private int UserID;
    private int restaurantID;
    private double note;
    private String commentaire;
    private Timestamp dateEvaluation;

    public Review(int reviewID, int UserID, int restaurantID, double note, String commentaire, Timestamp dateEvaluation) {
        this.reviewID = reviewID;
        this.UserID = UserID;
        this.restaurantID = restaurantID;
        this.note = note;
        this.commentaire = commentaire;
        this.dateEvaluation = dateEvaluation;
    }
    public Review( int UserID, int restaurantID, double note, String commentaire) {

        this.UserID = UserID;
        this.restaurantID = restaurantID;
        this.note = note;
        this.commentaire = commentaire;

    }


    // Getters and setters...

    public int getReviewID() {
        return reviewID;
    }

    public void setReviewID(int reviewID) {
        this.reviewID = reviewID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        this.UserID = userID;
    }

    public int getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(int restaurantID) {
        this.restaurantID = restaurantID;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Timestamp getDateEvaluation() {
        return dateEvaluation;
    }

    public void setDateEvaluation(Timestamp dateEvaluation) {
        this.dateEvaluation = dateEvaluation;
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewID=" + reviewID +
                ", userID=" + UserID +
                ", restaurantID=" + restaurantID +
                ", note=" + note +
                ", commentaire='" + commentaire + '\'' +
                ", dateEvaluation=" + dateEvaluation +
                '}';
    }
}
