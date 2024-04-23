package com.acme.swe3313.controllers;

import com.acme.swe3313.Application;
import com.acme.swe3313.models.Order;
import com.acme.swe3313.models.OrderItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class OrderConfirmationController {
    @FXML
    private VBox detailVBox;

    /**
     * On the initialization of the order confirmation page, this will be called
     * to populate the latest order information
     */
    @FXML
    public void initialize() {
        // Get the last order from the orders list
        Order order = Application.orders.getLast();
        List<OrderItem> orderItems = order.getItems();

        // For each item, show it in the table
        for (OrderItem item : orderItems) {
            // For even rows, the background color will be light gray
            String backgroundColor = orderItems.indexOf(item) % 2 == 0 ? "#FFFFFF" : "#F3F4F6";
            System.out.println(backgroundColor);

            HBox itemBox = new HBox();
            itemBox.setStyle("-fx-background-color: " + backgroundColor + "; -fx-padding: 12px;");
            itemBox.setPrefWidth(600);

            Label productName = new Label(item.getProduct().getDescription());
            productName.setPrefWidth(300);

            Label quantity = new Label(String.valueOf(item.getQuantity()));
            quantity.setPrefWidth(100);

            itemBox.getChildren().addAll(productName, quantity);

            detailVBox.getChildren().add(itemBox);
        }
    }

    /**
     * On the creation of a new order, this will be called when they click the 'return to orders' button
     */
    @FXML
    private void onSubmit(ActionEvent event) throws IOException {
        Application.setScene("orders.fxml");
    }
}