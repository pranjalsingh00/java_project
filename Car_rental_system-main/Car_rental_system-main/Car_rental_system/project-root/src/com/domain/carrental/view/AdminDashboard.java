package com.domain.carrental.view;

import com.domain.carrental.controller.CarController;
import com.domain.carrental.model.Car;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AdminDashboard extends JFrame {
    private CarController carController;

    public AdminDashboard() {
        carController = new CarController();

        setTitle("Admin Dashboard - Car Rental System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton btnAddCar = new JButton("Add New Car");
        btnAddCar.addActionListener(e -> showAddCarDialog());

        JButton btnViewCars = new JButton("View Available Cars");
        btnViewCars.addActionListener(e -> showAvailableCars());

        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.add(btnAddCar);
        panel.add(btnViewCars);

        add(panel);

        setVisible(true);
    }

    private void showAddCarDialog() {
        JTextField tfBrand = new JTextField();
        JTextField tfModel = new JTextField();
        JTextField tfYear = new JTextField();
        JTextField tfPricePerDay = new JTextField();

        Object[] fields = {
                "Brand:", tfBrand,
                "Model:", tfModel,
                "Year:", tfYear,
                "Price Per Day:", tfPricePerDay,
        };

        int result = JOptionPane.showConfirmDialog(this, fields, "Add New Car", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String brand = tfBrand.getText();
            String model = tfModel.getText();
            int year = Integer.parseInt(tfYear.getText());
            double pricePerDay = Double.parseDouble(tfPricePerDay.getText());

            boolean success = carController.addCar(brand, model, year, pricePerDay);

            if (success) {
                JOptionPane.showMessageDialog(this, "Car added successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add car!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void showAvailableCars() {
        List<Car> cars = carController.getAvailableCars();

        StringBuilder sb = new StringBuilder("Available Cars:\n");

        for (Car car : cars) {
            sb.append(car.toString()).append("\n");
        }

        JOptionPane.showMessageDialog(this, sb.toString());
    }
}
