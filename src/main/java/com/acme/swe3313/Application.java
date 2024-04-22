package com.acme.swe3313;

import com.acme.swe3313.models.Customer;
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

        // If the file was just created, write an empty json object to it
        if (customersFileCreated) {
            JSONArray customers = JSON.parseArray("/customers.json");

            JSON.write("/customers.json", customers);
        }

        // Ensure the orders.json file exists, and create it if it doesn't.
        File ordersFile = new File(PROGRAM_DATA_PATH + "\\orders.json");
        boolean ordersFileCreated = ordersFile.createNewFile();

        // If the file was just created, write an empty json object to it
        if (ordersFileCreated) {
            JSON.write("/orders.json", new JSONObject());
        }
    }

    /**
     * Populates the customers list with the customers from the customers.json file
     */
    public static void populateCustomers() {
        JSONArray customersArray = JSON.parseDynamicArray("/customers.json");

        // Only proceed if the customers list is empty
        if (!customers.isEmpty()) {
            return;
        }

        for (Object o : customersArray) {
            JSONObject customer = (JSONObject) o;
            Customer c = new Customer(customer);
            customers.add(c);
        }
    }
}