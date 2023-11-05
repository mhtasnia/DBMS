package com.codebind;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class DataRetrieval {
    public static Vector<Vector<Object>> selectAll() {
        Vector<Vector<Object>> data = new Vector<>();
        String query = "SELECT * FROM buslist";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();
                row.add(resultSet.getString("Bus Code"));
                row.add(resultSet.getString("Registration Number"));
                row.add(resultSet.getString("Driver Name"));
                row.add(resultSet.getString("Contact Info"));
                row.add(resultSet.getString("Route"));
                data.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
}
