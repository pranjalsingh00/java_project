package com.domain.carrental.view;

import com.domain.carrental.controller.CarController;
import com.domain.carrental.controller.RentalController;
import com.domain.carrental.model.Car;
import com.domain.carrental.model.Rental;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class CustomerDashboard extends JFrame {
    private CarController carController;
    private RentalController rentalController;
    private int customerId; // ID of the logged-in customer

    public CustomerDashboard(int customerId) {
        this.customerId = customerId;
        carController = new CarController();
        rentalController = new RentalController();

        setTitle("Customer Dashboard - Car Rental System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton btnViewCars = new JButton("Browse Available Cars");
        btnViewCars.addActionListener(e -> showAvailableCars());

        JButton btnRentCar = new JButton("Rent a Car");
        btnRentCar.addActionListener(e -> rentCar());

        JButton btnViewRentals = new JButton("View My Rentals");
        btnViewRentals.addActionListener(e -> viewMyRentals());

        JPanel panel = new JPanel(new GridLayout(3, 1));
        panel.add(btnViewCars);
        panel.add(btnRentCar);
        panel.add(btnViewRentals);

        add(panel);

        setVisible(true);
    }

    private void showAvailableCars() {
        List<Car> cars = carController.getAvailableCars();

        StringBuilder sb = new StringBuilder("Available Cars:\n");
        for (Car car : cars) {
            sb.append(car.toString()).append("\n");
        }

        JOptionPane.showMessageDialog(this, sb.toString());
    }

    private void rentCar() {
        String carIdStr = JOptionPane.showInputDialog(this, "Enter Car ID to Rent:");

        if (carIdStr != null && !carIdStr.isEmpty()) {
            int carId = Integer.parseInt(carIdStr);

            String startDateStr = JOptionPane.showInputDialog(this, "Enter Start Date (YYYY-MM-DD):");
            String endDateStr = JOptionPane.showInputDialog(this, "Enter End Date (YYYY-MM-DD):");

            if (startDateStr != null && endDateStr != null) {
                LocalDate startDate = LocalDate.parse(startDateStr);
                LocalDate endDate = LocalDate.parse(endDateStr);

                double totalPrice = calculateTotalPrice(carId, startDate, endDate);

                boolean success = rentalController.createRental(customerId, carId, startDate, endDate, totalPrice);

                if (success) {
                    JOptionPane.showMessageDialog(this, "Car rented successfully! Total Price: $" + totalPrice);
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to rent car!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private double calculateTotalPrice(int carId, LocalDate startDate, LocalDate endDate) {
        List<Car> cars = carController.getAvailableCars();

        for (Car car : cars) {
            if (car.getId() == carId) {
                long days = java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate);
                return days * car.getPricePerDay();
            }
        }

        return 0.0;
    }

    private void viewMyRentals() {
        List<Rental> rentals = rentalController.getRentalsByCustomer(customerId);

        StringBuilder sb = new StringBuilder("My Rentals:\n");

        for (Rental rental : rentals) {
            sb.append(rental.toString()).append("\n");
        }

        JOptionPane.showMessageDialog(this, sb.toString());
    }
}
