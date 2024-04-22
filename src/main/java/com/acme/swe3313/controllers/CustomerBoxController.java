package com.acme.swe3313.controllers;
import com.acme.swe3313.models.Customer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class CustomerBoxController {
    @FXML
    private Label address;

    @FXML
    private HBox box;

    @FXML
    private Label dockCapabilities;

    @FXML
    private Label license;

    @FXML
    private Label name;

    @FXML
    private Label paymentMethod;

    public void setData(Customer customer){
    name.setText(customer.getStore());
    address.setText(customer.getStreetAddress());
    license.setText(customer.getBeerLicense());
    paymentMethod.setText(customer.getPaymentMethod());
    dockCapabilities.setText(customer.getDockCapabilities());

    box.setStyle("-fx-border-color: transparent transparent #D1D5DB transparent");
    }
}
