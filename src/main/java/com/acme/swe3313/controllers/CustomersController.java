package com.acme.swe3313.controllers;
import com.acme.swe3313.Application;
import com.acme.swe3313.models.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.acme.swe3313.Application.customers;

public class CustomersController implements Initializable{

    @FXML
    public static VBox customerVbox;
    @FXML
    private HBox customerLayout;
    private List<Customer> initialList;


 /*   public void updateCustomersView(Customer c) {
        nameTextField.setText(c.getName());
        addressTextField.setText(c.getStreetAddress());
        licenseTextField.setText(c.getBeerLicense());
        payMethodTextField.setText(c.getPaymentMethod());

        customerHbox.getChildren().set(0, nameTextField);
        customerHbox.getChildren().set(1, addressTextField);
        customerHbox.getChildren().set(2, licenseTextField);
        customerHbox.getChildren().set(3, payMethodTextField);
        customerHbox.setLayoutX(10.0);
    }*/
    @FXML
    private void onAddCustomer (ActionEvent event) throws IOException {
        Application.setScene("add-customer.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

                try {
                    for(Customer customer: customers) {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("customer-box.fxml"));
                        HBox newBox = fxmlLoader.load();
                        CustomerBoxController boxController = fxmlLoader.getController();
                        boxController.setData(customer);
                        customerVbox.getChildren().add(newBox);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

    }

