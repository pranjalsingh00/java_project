package com.domain.carrental.view;

import com.domain.carrental.controller.LoginController;
import com.domain.carrental.model.User;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private LoginController loginController;

    public LoginFrame() {
        loginController = new LoginController();

        setTitle("Car Rental System - Login");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame

        // Main panel with padding and background color
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        mainPanel.setBackground(new Color(245, 245, 245));

        // Title label
        JLabel titleLabel = new JLabel("Login", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 102, 204));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(245, 245, 245));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        tfUsername = new JTextField(18);
        tfUsername.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        pfPassword = new JPasswordField(18);
        pfPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(lblUsername, gbc);
        gbc.gridx = 1;
        formPanel.add(tfUsername, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(lblPassword, gbc);
        gbc.gridx = 1;
        formPanel.add(pfPassword, gbc);

        // Login button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(245, 245, 245));
        JButton btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnLogin.setBackground(new Color(0, 102, 204));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setPreferredSize(new Dimension(120, 35));
        btnLogin.addActionListener(e -> handleLogin());
        buttonPanel.add(btnLogin);

        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        setVisible(true);
    }

    private void handleLogin() {
        String username = tfUsername.getText();
        String password = new String(pfPassword.getPassword());

        User user = loginController.authenticate(username, password);

        if (user != null) {
            JOptionPane.showMessageDialog(this, "Login successful!");
            if (user.getRole().equalsIgnoreCase("ADMIN")) {
                new AdminDashboard();
            } else if (user.getRole().equalsIgnoreCase("CUSTOMER")) {
                new CustomerDashboard(user.getId());
            }
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials!", "Error", JOptionPane.ERROR_MESSAGE);
            tfUsername.setText("");
            pfPassword.setText("");
            tfUsername.requestFocus();
        }
    }
}
