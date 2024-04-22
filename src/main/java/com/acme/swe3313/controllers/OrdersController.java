package com.acme.swe3313.controllers;

import com.acme.swe3313.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class OrdersController {
    @FXML
    private VBox orderVBox;

    /**
     * On the initialization of the customers view, this function will be called to populate the customers list
     */
    @FXML
    public void initialize() {

    }

    /**
     * When the add customer button is clicked, this function will be called to navigate to the add customer page
     * @param event
     * @throws IOException
     */
    @FXML
    private void onAddOrder(ActionEvent event) throws IOException {
        Application.setScene("add_order.fxml");
    }

    /**
     * When the logout button is clicked, this function will be called to navigate to the login page
     * @param event
     * @throws IOException
     */
    @FXML
    private void onLogout(ActionEvent event) throws IOException {
        Application.setScene("login.fxml");
    }

    /**
     * When the orders button is clicked, this function will be called to navigate to the orders page
     * @param event
     * @throws IOException
     */
    @FXML
    private void onCustomers(ActionEvent event) throws IOException {
        Application.setScene("customers.fxml");
    }
}