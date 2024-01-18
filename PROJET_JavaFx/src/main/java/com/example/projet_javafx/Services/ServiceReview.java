package com.example.projet_javafx.Services;
import com.example.projet_javafx.Entities.Review;


import java.sql.*;
import java.util.ArrayList;
import com.example.projet_javafx.Utils.DataSource;

public class ServiceReview implements IServiceResto<Review> {

    private Connection con = DataSource.getInstance().getCon();
    private Statement ste;

    public ServiceReview() {
        try {
            ste = con.createStatement();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void ajouter(Review review) throws SQLException {
        String req = "INSERT INTO Reviews (UserID, RestaurantID, Note, Commentaire, DateEvaluation) " +
                "VALUES (" + review.getUserID() + ", " + review.getRestaurantID() + ", " +
                review.getNote() + ", '" + review.getCommentaire() + "', ";

        if (review.getDateEvaluation() != null) {
            req += "'" + review.getDateEvaluation() + "')";
        } else {
            req += "null)";
        }

        int res = ste.executeUpdate(req);
    }

    @Override
    public void update(Review review) throws SQLException {
        String req = "UPDATE Reviews SET Note=" + review.getNote() +
                ", Commentaire='" + review.getCommentaire() + "' WHERE ReviewID=" + review.getReviewID();
        int res = ste.executeUpdate(req);
    }

    @Override
    public void delete(int id) throws SQLException {
        String req = "DELETE FROM Reviews WHERE ReviewID=" + id;
        int res = ste.executeUpdate(req);
    }

    @Override
    public ArrayList<Review> readAll() throws SQLException {
        ArrayList<Review> list = new ArrayList<>();
        try (ResultSet resultSet = ste.executeQuery("SELECT * FROM Reviews")) {
            while (resultSet.next()) {
                int reviewID = resultSet.getInt("ReviewID");
                int UserID = resultSet.getInt("UserID");
                int restaurantID = resultSet.getInt("RestaurantID");
                double note = resultSet.getDouble("Note");
                String commentaire = resultSet.getString("Commentaire");
                Timestamp dateEvaluation = resultSet.getTimestamp("DateEvaluation");

                Review review = new Review(reviewID, UserID, restaurantID, note, commentaire, dateEvaluation);
                list.add(review);
            }
        }
        return list;
    }

    @Override
    public Review get(int id) throws SQLException {
        String req = "SELECT * FROM Reviews WHERE ReviewID=" + id;
        try (ResultSet resultSet = ste.executeQuery(req)) {
            if (resultSet.next()) {
                int UserID = resultSet.getInt("UserID");
                int restaurantID = resultSet.getInt("RestaurantID");
                double note = resultSet.getDouble("Note");
                String commentaire = resultSet.getString("Commentaire");
                Timestamp dateEvaluation = resultSet.getTimestamp("DateEvaluation");

                return new Review(id, UserID, restaurantID, note, commentaire, dateEvaluation);
            }
        }
        return null;
    }
    // New method to search reviews by comment
    public ArrayList<Review> searchByComment(String commentText) throws SQLException {
        ArrayList<Review> searchResults = new ArrayList<>();
        String req = "SELECT * FROM Reviews WHERE Commentaire LIKE ?";
        try (PreparedStatement pstmt = con.prepareStatement(req)) {
            pstmt.setString(1, "%" + commentText + "%");
            try (ResultSet resultSet = pstmt.executeQuery()) {
                while (resultSet.next()) {
                    int reviewID = resultSet.getInt("ReviewID");
                    int UserID = resultSet.getInt("UserID");
                    int restaurantID = resultSet.getInt("RestaurantID");
                    double note = resultSet.getDouble("Note");
                    String commentaire = resultSet.getString("Commentaire");
                    Timestamp dateEvaluation = resultSet.getTimestamp("DateEvaluation");

                    Review review = new Review(reviewID, UserID, restaurantID, note, commentaire, dateEvaluation);
                    searchResults.add(review);
                }
            }
        }
        return searchResults;
    }
}
