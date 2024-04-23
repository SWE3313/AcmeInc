package com.acme.swe3313.controllers.components;
import com.acme.swe3313.Application;
import com.acme.swe3313.models.Customer;
import com.acme.swe3313.models.Order;
import com.acme.swe3313.models.OrderItem;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class OrderRowController {
    @FXML
    private Label address;
    @FXML
    private Label customerStoreName;
    @FXML
    private Label quantity;

    public void setData(Order order) {
        // Get the customer based on the orders customer id
        String customerID = order.getCustomerID();
        Customer customer = Application.customers.stream().filter(c -> c.getId().equals(customerID)).findFirst().orElse(null);
        int totalQuantity = order.getItems().stream().mapToInt(OrderItem::getQuantity).sum();

        System.out.println(customer.getAddress());
        System.out.println(customer.getStreetAddress());

        // Set the data
        customerStoreName.setText(customer.getStoreName());
        address.setText(customer.getAddress());
        quantity.setText(String.valueOf(totalQuantity));
    }
}
