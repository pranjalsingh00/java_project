package com.domain.carrental.dao;

import com.domain.carrental.model.Rental;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RentalDAO {

    // Create a new rental record in the database
    public boolean createRental(Rental rental) {
        String query = "INSERT INTO rentals (user_id, car_id, start_date, end_date, total_price, status) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnector.connect();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, rental.getCustomerId());
            stmt.setInt(2, rental.getCarId());
            stmt.setDate(3, Date.valueOf(rental.getStartDate()));
            stmt.setDate(4, Date.valueOf(rental.getEndDate()));
            stmt.setDouble(5, rental.getTotalPrice());
            stmt.setString(6, "BOOKED"); // Default status is "BOOKED"

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Retrieve all rentals for a specific customer
    public List<Rental> getRentalsByCustomer(int customerId) {
        List<Rental> rentals = new ArrayList<>();
        String query = "SELECT * FROM rentals WHERE user_id = ?";

        try (Connection conn = DatabaseConnector.connect();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, customerId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Rental rental = new Rental(
                            rs.getInt("rental_id"),
                            rs.getInt("user_id"),
                            rs.getInt("car_id"),
                            rs.getDate("start_date").toLocalDate(),
                            rs.getDate("end_date").toLocalDate(),
                            rs.getDouble("total_price"));
                    rentals.add(rental);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rentals;
    }

    // Update the status of a rental
    public boolean updateRentalStatus(int rentalId, String status) {
        String query = "UPDATE rentals SET status = ? WHERE rental_id = ?";

        try (Connection conn = DatabaseConnector.connect();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, status);
            stmt.setInt(2, rentalId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Retrieve all rentals (Admin functionality)
    public List<Rental> getAllRentals() {
        List<Rental> rentals = new ArrayList<>();
        String query = "SELECT * FROM rentals";

        try (Connection conn = DatabaseConnector.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Rental rental = new Rental(
                        rs.getInt("rental_id"),
                        rs.getInt("user_id"),
                        rs.getInt("car_id"),
                        rs.getDate("start_date").toLocalDate(),
                        rs.getDate("end_date").toLocalDate(),
                        rs.getDouble("total_price"));
                rentals.add(rental);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rentals;
    }
}
