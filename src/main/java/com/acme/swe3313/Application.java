package com.acme.swe3313;

import com.acme.swe3313.models.Customer;
import com.acme.swe3313.models.Order;
import com.acme.swe3313.models.Product;
import com.acme.swe3313.util.JSON;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Application extends javafx.application.Application {
    /**
     * The current stage that is being displayed
     */
    private static Stage primaryStage;
    /**
     * List of customers that will be populated from the customers.json file
     * This is the local 'state' of the application
     */
    public static List<Customer> customers = new ArrayList <> ();
    /**
     * List of orders that will be populated from the orders.json file
     * This is the local 'state' of the application
     */
    public static List<Order> orders = new ArrayList <> ();
    /**
     * List of products that will be populated from the products.json file
     * This is the local 'state' of the application
     */
    public static List<Product> products = new ArrayList <> ();
    /**
     * The path to the program data directory (IE %appdata%/Acme Distributing)
     */
    public static final String PROGRAM_DATA_PATH = System.getenv("APPDATA") + "\\Acme Distributing";

    /**
     * The main entry point for the program
     * @param args
     */
    public static void main(String[] args) {
        // First, create the necessary files
        try {
            createConfigFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }

        launch();
    }

    /**
     * Starts the program and shows the login screen
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Acme Distributing");
        stage.setScene(scene);
        stage.show();
        stage.setMaximized(true);
    }

    /**
     * Sets the current scene (Think of this as a page router)
     * @param fxml
     * @throws IOException
     */
    public static void setScene(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(fxml));

        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
    }

    /**
     * Initialize the files needed for the program
     */
    public static void createConfigFiles() throws IOException {
        // Ensure the program data directory exists
        new File(PROGRAM_DATA_PATH).mkdirs();

        // Ensure the customers.json file exists, and create it if it doesn't.
        File customersFile = new File(PROGRAM_DATA_PATH + "\\customers.json");
        boolean customersFileCreated = customersFile.createNewFile();

        // If the file was just created, write the provided customers data as a starting point
        if (customersFileCreated) {
            JSONArray customers = JSON.parseArray("/customers.json");

            JSON.write("/customers.json", customers);
        }

        // Ensure the orders.json file exists, and create it if it doesn't.
        File ordersFile = new File(PROGRAM_DATA_PATH + "\\orders.json");
        boolean ordersFileCreated = ordersFile.createNewFile();

        // If the file was just created, write an empty JSON object as a starting point
        if (ordersFileCreated) {
            JSONArray orders = JSON.parseArray("/orders.json");

            JSON.write("/orders.json", orders);
        }

        // Ensure the products.json file exists, and create it if it doesn't.
        File productsFile = new File(PROGRAM_DATA_PATH + "\\products.json");
        boolean productsFileCreated = productsFile.createNewFile();

        // If the file was just created, write the provided products data as a starting point
        if (productsFileCreated) {
            JSONArray products = JSON.parseArray("/products.json");

            JSON.write("/products.json", products);
        }
    }

    /**
     * Populates the dynamic lists of data for the program
     * Creates a 'mock' database by startin
     */
    public static void populateData() {
        JSONArray ordersArray = JSON.parseDynamicArray("/orders.json");
        JSONArray productsArray = JSON.parseDynamicArray("/products.json");
        JSONArray customersArray = JSON.parseDynamicArray("/customers.json");

        // If the customers have not already been populated, populate them
        if (customers.isEmpty()) {
            for (Object obj : customersArray) {
                JSONObject customer = (JSONObject) obj;
                Customer c = new Customer(customer);
                customers.add(c);
            }
        }

        // If the products have not already been populated, populate them
        if (products.isEmpty()) {
            for (Object obj : productsArray) {
                JSONObject product = (JSONObject) obj;
                Product p = new Product(product);
                products.add(p);
            }
        }

        // If the orders have not already been populated, populate them
        if (orders.isEmpty()) {
            for (Object obj : ordersArray) {
                JSONObject order = (JSONObject) obj;
                Order o = new Order(order);
                orders.add(o);
            }
        }
    }
}