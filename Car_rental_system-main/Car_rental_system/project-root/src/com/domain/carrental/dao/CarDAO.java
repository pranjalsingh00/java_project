package com.domain.carrental.dao;

import com.domain.carrental.model.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDAO {

    // Fetch all available cars from the database
    public List<Car> getAvailableCars() {
        List<Car> cars = new ArrayList<>();
        String query = "SELECT * FROM cars WHERE available=true";

        try (Connection conn = DatabaseConnector.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Car car = new Car(
                        rs.getInt("car_id"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getInt("year"),
                        rs.getDouble("price_per_day"),
                        rs.getBoolean("available"));
                cars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    // Add a new car to the database
    public boolean addCar(Car car) {
        String query = "INSERT INTO cars (brand, model, year, price_per_day, available) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnector.connect();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, car.getBrand());
            stmt.setString(2, car.getModel());
            stmt.setInt(3, car.getYear());
            stmt.setDouble(4, car.getPricePerDay());
            stmt.setBoolean(5, car.isAvailable());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
