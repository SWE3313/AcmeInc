package com.acme.swe3313.controllers;

import com.acme.swe3313.Application;
import com.acme.swe3313.models.Customer;
import com.acme.swe3313.models.Order;
import com.acme.swe3313.models.OrderItem;
import com.acme.swe3313.models.Product;
import com.acme.swe3313.util.JSON;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class AddOrderController {
    private final int MAX_PRODUCTS = 10;
    private List<TextField> quantityInputs = new ArrayList<>();
    private List<ChoiceBox<String>> productChoiceBoxes = new ArrayList<>();

    @FXML
    private VBox inputVBox;
    @FXML
    private ChoiceBox<String> customerChoiceBox;

    @FXML
    private void initialize() {
        // Create an array of customers store names from all application.customer
        String[] customerNames = new String[Application.customers.size()];
        String[] productNames = new String[Application.products.size()];

        // Loop through all customers and add their names to the customerNames array
        for (Customer c: Application.customers) {
            customerNames[Application.customers.indexOf(c)] = c.getStoreName();
        }

        // Loop through all products and add their names to the productNames array
        for (Product p: Application.products) {
            productNames[Application.products.indexOf(p)] = (String) p.getDescription();
        }

        customerChoiceBox.setItems(FXCollections.observableArrayList(customerNames));

        // Create MAX_PRODUCTS hboxes in the inputVBox, each containing a choice box for the product and a text field for the quantity
        for (int i = 0; i < MAX_PRODUCTS; i++) {
            HBox hBox = new HBox();
            hBox.setSpacing(12);

            // Create the choice box
            ChoiceBox<String> productChoiceBox = new ChoiceBox<>();
            productChoiceBox.setItems(FXCollections.observableArrayList(productNames));
            productChoiceBox.setStyle("-fx-background-color: white; -fx-border-color: #D1D5DB; -fx-border-width: 1px; -fx-border-radius: 4px; -fx-padding:8px;");
            productChoiceBox.setPrefWidth(600);
            productChoiceBoxes.add(productChoiceBox);

            // Create the text field
            TextField quantityInput = new TextField();
            quantityInput.setPromptText("Quantity");
            quantityInput.setStyle("-fx-background-color: white; -fx-border-color: #D1D5DB; -fx-border-width: 1px; -fx-border-radius: 4px; -fx-padding:10px; -fx-font-size: 14px");
            quantityInput.setPrefWidth(400);
            quantityInputs.add(quantityInput);

            // Add the choice box and text field to the hbox
            hBox.getChildren().addAll(productChoiceBox, quantityInput);

            // Add the hbox to the inputVBox
            inputVBox.getChildren().add(hBox);
        }
    }

    /**
     * On the exit of the add customer page, this function will be called to navigate back to the customers view
     * @param event
     * @throws IOException
     */
    @FXML
    private void onExit(ActionEvent event) throws IOException {
        Application.setScene("orders.fxml");
    }

    /**
     * On the creation of a new customer, this function will be called to add the customer to the state
     * and persist the customer to the customers.json file
     * @param event
     * @throws IOException
     */
    @FXML
    private void onSubmit(ActionEvent event) throws IOException {
        // First, get the selected customer
        String customerName = customerChoiceBox.getValue();
        Customer customer = Application.customers.stream().filter(c -> c.getStoreName().equals(customerName)).findFirst().orElse(null);

        if (customer == null) {
            return;
        }

        Order order = new Order(customer.getId());

        for (int i = 0; i < MAX_PRODUCTS; i++) {
            String productName = productChoiceBoxes.get(i).getValue();
            String quantity = quantityInputs.get(i).getText();

            if (productName == null || productName.isEmpty() || quantity == null || quantity.isEmpty()) {
                continue;
            }

            // For each product, create a new OrderItem. First, find the  product by its description
            Product product = Application.products.stream().filter(p -> p.getDescription().equals(productName)).findFirst().orElse(null);

            if (product == null) {
                continue;
            }

            // Create a new OrderItem
            OrderItem orderItem = new OrderItem(product, Integer.parseInt(quantity));

            order.addItem(orderItem);
        }

        // Read the orders.json file
        JSONArray orders = JSON.parseDynamicArray("/orders.json");

        // Add the new order to the orders array
        JSONObject orderJSON = order.toJSON();
        orders.add(orderJSON);

        // Update the state
        Application.orders.add(order);

        // Write the orders array back to the orders.json file
        JSON.write("/orders.json", orders);

        // Navigate back to the orders confirmation page
        Application.setScene("order_confirmation.fxml");
    }
}