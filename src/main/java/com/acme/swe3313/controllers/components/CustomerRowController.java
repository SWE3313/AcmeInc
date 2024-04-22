package com.acme.swe3313.controllers.components;
import com.acme.swe3313.models.Customer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class CustomerRowController {
    @FXML
    private Label address;
    @FXML
    private Label license;
    @FXML
    private Label storeName;
    @FXML
    private Label cardNumber;
    @FXML
    private Label dockCapabilities;

    public void setData(Customer customer){
        String cardDetails = customer.getCardNumber().length() > 4
            ? customer.getCardNumber().substring(customer.getCardNumber().length() - 4)
            : customer.getCardNumber();

        storeName.setText(customer.getStoreName());
        address.setText(customer.getStreetAddress());
        license.setText(customer.getLicense());
        cardNumber.setText("**** **** **** " + cardDetails);
        dockCapabilities.setText(customer.getDockCapabilities());
    }
}
