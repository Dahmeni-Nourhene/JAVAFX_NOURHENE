package com.example.projet_javafx.Services;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IServiceResto<T> {
    void ajouter(T t) throws SQLException;

    void update(T t) throws SQLException;

    void delete(int id) throws SQLException;

    ArrayList<T> readAll() throws SQLException;

    T get(int id) throws SQLException;
}
