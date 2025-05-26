package com.domain.carrental.controller;

import com.domain.carrental.dao.UserDAO;
import com.domain.carrental.model.User;

public class LoginController {
    private UserDAO userDAO;

    // Constructor
    public LoginController() {
        this.userDAO = new UserDAO();
    }

    // Authenticate user credentials
    public User authenticate(String username, String password) {
        return userDAO.authenticate(username, password);
    }
}
