package com.acme.swe3313.controllers;
import com.acme.swe3313.Application;
import com.acme.swe3313.controllers.components.CustomerRowController;
import com.acme.swe3313.models.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class CustomersController {
    @FXML
    private VBox customerVBox;

    /**
     * On the initialization of the customers view, this function will be called to populate the customers list
     */
    @FXML
    public void initialize() {
        try {
            for (Customer customer: Application.customers) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Application.class.getResource("components/customer_row.fxml"));
                HBox newBox = fxmlLoader.load();
                CustomerRowController boxController = fxmlLoader.getController();
                boxController.setData(customer);

                customerVBox.getChildren().add(newBox);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * When the add customer button is clicked, this function will be called to navigate to the add customer page
     * @param event
     * @throws IOException
     */
    @FXML
    private void onAddCustomer(ActionEvent event) throws IOException {
        Application.setScene("add_customer.fxml");
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
    private void onOrders(ActionEvent event) throws IOException {
        Application.setScene("orders.fxml");
    }
}