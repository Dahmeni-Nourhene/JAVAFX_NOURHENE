package com.example.projet_javafx.Services;
import com.example.projet_javafx.Utils.DataSource;

import com.example.projet_javafx.Entities.Restaurant;


import java.sql.*;
import java.util.ArrayList;
public class ServiceRestaurant implements IServiceResto<Restaurant> {
    private Connection con = DataSource.getInstance().getCon();
    private Statement ste;

    public ServiceRestaurant() {
        try {
            ste = con.createStatement();
        } catch (SQLException e)  {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void ajouter(Restaurant restaurant) throws SQLException {
        String sql = "INSERT INTO Restaurants (NomRestaurant, AdresseRestaurant, Description, NoteMoyenne) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, restaurant.getNomRestaurant());
            preparedStatement.setString(2, restaurant.getAdresseRestaurant());
            preparedStatement.setString(3, restaurant.getDescription());
            preparedStatement.setDouble(4, restaurant.getNoteMoyenne());

            preparedStatement.executeUpdate();

            // Récupérer l'ID généré automatiquement
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    restaurant.setRestaurantID(generatedKeys.getLong(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    @Override
    public void update(Restaurant restaurant) throws SQLException {
        String sql = "UPDATE Restaurants SET NomRestaurant=?, AdresseRestaurant=?, Description=?, NoteMoyenne=? WHERE RestaurantID=?";

        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setString(1, restaurant.getNomRestaurant());
            preparedStatement.setString(2, restaurant.getAdresseRestaurant());
            preparedStatement.setString(3, restaurant.getDescription());
            preparedStatement.setDouble(4, restaurant.getNoteMoyenne());
            preparedStatement.setLong(5, restaurant.getRestaurantID());

            System.out.println("id= "+restaurant.getRestaurantID());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Restaurants WHERE RestaurantID=?";

        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    @Override
    public ArrayList<Restaurant> readAll() throws SQLException {
        ArrayList<Restaurant> list = new ArrayList<>();
        try {
            ResultSet resultSet = ste.executeQuery("SELECT * FROM Restaurants");
            while (resultSet.next()) {
                long id = resultSet.getLong("RestaurantID");
                String nom = resultSet.getString("NomRestaurant");
                String adresse = resultSet.getString("AdresseRestaurant");
                String description = resultSet.getString("Description");
                double noteMoyenne = resultSet.getDouble("NoteMoyenne");

                Restaurant restaurant = new Restaurant(id,nom, adresse, description, noteMoyenne);
                list.add(restaurant);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    @Override
    public Restaurant get(int id) throws SQLException {
        String sql = "SELECT * FROM Restaurants WHERE RestaurantID=?";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                long restaurantID = resultSet.getLong("RestaurantID");
                String nom = resultSet.getString("NomRestaurant");
                String adresse = resultSet.getString("AdresseRestaurant");
                String description = resultSet.getString("Description");
                double noteMoyenne = resultSet.getDouble("NoteMoyenne");

                return new Restaurant(restaurantID,nom, adresse, description, noteMoyenne);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
