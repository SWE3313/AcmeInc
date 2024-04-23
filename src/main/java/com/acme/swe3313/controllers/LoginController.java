package com.acme.swe3313.controllers;

import com.acme.swe3313.Application;
import com.acme.swe3313.models.Associate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;


public class LoginController {
    @FXML
    private TextField emailInput;
    @FXML
    private TextField passwordInput;
    @FXML
    private Label errorLabel;

    @FXML
    private void onSubmit(ActionEvent event) {
        String email = emailInput.getText();
        String password = passwordInput.getText();

        Associate associate = new Associate();

        try {
            if (associate.login(email, password)) {
                Application.populateData();
                Application.setScene("customers.fxml");
            } else {
                errorLabel.setVisible(true);
                errorLabel.setText("Invalid email or password");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}