package com.acme.swe3313.controllers;

import com.acme.swe3313.Application;
import com.acme.swe3313.models.Customer;
import com.acme.swe3313.util.JSON;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
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


    @FXML
    private void onExit(ActionEvent event) throws IOException{
        Application.setScene("customers-view.fxml");
    }
    @FXML
    private void onSubmit(ActionEvent event) throws IOException {
        JSONObject customers = JSON.parseDynamic("/customers.json");

        String storeName = storeNameInput.getText();
        String customerName = nameInput.getText();
        String streetAddress = streetAddressInput.getText();
        String city = cityInput.getText();
        String state = stateInput.getText();
        String phoneNumber = phoneNumberInput.getText();
        String license = licenseInput.getText();
        String cardNum = cardNumInput.getText();
        String cardExp = cardExpInput.getText();
        String cardCvv = cardCvvInput.getText();
        String dockCapabilities = dockCapabilitiesInput.getText();

        // Create the new customer object
        Customer customer = new Customer(customerName, storeName, license, streetAddress, city, state, dockCapabilities, phoneNumber );

        JSONObject newCustomer = new JSONObject();
        newCustomer.put("store", customer.getStore());
        newCustomer.put("customer_id", customer.getCustomerId());
        newCustomer.put("name", customer.getName());
        newCustomer.put("address", customer.getStreetAddress());
        newCustomer.put("city", customer.getCity());
        newCustomer.put("state", customer.getState());
        newCustomer.put("phone", customer.getPhone());

        // Write the new customer to the customers.json file
        customers.put(customer.getCustomerId(), newCustomer);

        System.out.println(customers);

        JSON.write("/customers.json", customers);

        Application.setScene("customers-view.fxml");
    }
}
