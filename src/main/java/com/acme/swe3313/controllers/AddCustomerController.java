package com.acme.swe3313.controllers;

import com.acme.swe3313.Application;
import com.acme.swe3313.models.Customer;
import com.acme.swe3313.util.JSON;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;


public class AddCustomerController {
    @FXML
    private TextField storeNameInput;
    @FXML
    private TextField nameInput;
    @FXML
    private TextField streetAddressInput;
    @FXML
    private TextField cityInput;
    @FXML
    private TextField stateInput;
    @FXML
    private TextField licenseInput;
    @FXML
    private TextField phoneNumberInput;
    @FXML
    private TextField cardNumInput;
    @FXML
    private TextField cardExpInput;
    @FXML
    private TextField cardCvvInput;
    @FXML
    private TextField dockCapabilitiesInput;

    /**
     * On the exit of the add customer page, this function will be called to navigate back to the customers view
     * @param event
     * @throws IOException
     */
    @FXML
    private void onExit(ActionEvent event) throws IOException {
        Application.setScene("customers-view.fxml");
    }

    /**
     * On the creation of a new customer, this function will be called to add the customer to the state
     * and persist the customer to the customers.json file
     * @param event
     * @throws IOException
     */
    @FXML
    private void onSubmit(ActionEvent event) throws IOException {
        // Fetch the initial customers from the customers.json file
        JSONArray customers = JSON.parseDynamicArray("/customers.json");

        // Create an object to store the new customer
        JSONObject newCustomer = new JSONObject();

        newCustomer.put("storeName", storeNameInput.getText());
        newCustomer.put("fullName", nameInput.getText());
        newCustomer.put("license", licenseInput.getText());
        newCustomer.put("phoneNumber", phoneNumberInput.getText());
        newCustomer.put("streetAddress", streetAddressInput.getText());
        newCustomer.put("city", cityInput.getText());
        newCustomer.put("state", stateInput.getText());
        newCustomer.put("cardNumber", cardNumInput.getText());
        newCustomer.put("cardExpiration", cardExpInput.getText());
        newCustomer.put("cardCVV", cardCvvInput.getText());
        newCustomer.put("dockCapabilities", dockCapabilitiesInput.getText());

        Customer customer = new Customer(newCustomer);

        newCustomer.put("id", customer.getId());
        customers.add(newCustomer);

        // Write the new customer to the customers.json file, and add it to the state
        JSON.write("/customers.json", customers);
        Application.customers.add(customer);

        Application.setScene("customers-view.fxml");
    }
}