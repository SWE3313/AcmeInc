package com.acme.swe3313.models;

import org.json.simple.JSONObject;

import java.util.Random;

public class Customer {
    Random rand = new Random();

    // General Information
    private String id;
    private String license;
    private String fullName;
    private String storeName;
    private String phoneNumber;
    private String dockCapabilities;

    // Address Information
    private String streetAddress;
    private String city;
    private String state;

    // Payment Information
    private String cardNumber;
    private String cardExpiration;
    private String cardCVV;

    /**
     * Create a new customer by inputting all of the information manually
     *
     * @param storeName        The name of the store
     * @param fullName         The full name of the customer
     * @param phoneNumber      The phone number of the customer
     * @param license          The beer license of the customer
     * @param streetAddress    The street address of the customer
     * @param city             The city of the customer
     * @param state            The state of the customer
     * @param cardNumber       The card number of the customer
     * @param cardExpiration   The expiration date of the card
     * @param cardCVV          The CVV of the card
     * @param dockCapabilities The dock capabilities of the customer
     */
    public Customer(String storeName, String fullName, String phoneNumber, String license, String streetAddress, String city, String state, String cardNumber, String cardExpiration, String cardCVV, String dockCapabilities) {
        this.storeName = storeName;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.license = license;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.cardNumber = cardNumber;
        this.cardExpiration = cardExpiration;
        this.cardCVV = cardCVV;
        this.dockCapabilities = dockCapabilities;

        // Create a customer ID
        StringBuilder prefix = new StringBuilder();
        StringBuilder storeId = new StringBuilder();
        String[] storeSplit = storeName.split(" ");

        for (int i = 0; i < storeSplit.length; i++) {
            if (storeSplit[i].equalsIgnoreCase("the")) {
                prefix.append(storeSplit[i].toUpperCase());
            } else {
                prefix.append(storeSplit[i].charAt(0));
            }
        }

        storeId.append(prefix + "_");
        for (int i = 0; i < (14 - prefix.length()); i++) {
            storeId.append(rand.nextInt(9));
        }

        this.id = storeId.toString();
    }

    /**
     * Overloaded constructor to create a customer from a JSON object
     * @param customer
     */
    public Customer(JSONObject customer) {
        this.storeName = (String) customer.get("storeName");
        this.fullName = (String) customer.get("fullName");
        this.phoneNumber = (String) customer.get("phoneNumber");
        this.license = (String) customer.get("license");
        this.streetAddress = (String) customer.get("streetAddress");
        this.city = (String) customer.get("city");
        this.state = (String) customer.get("state");
        this.cardNumber = (String) customer.get("cardNumber");
        this.cardExpiration = (String) customer.get("cardExpiration");
        this.cardCVV = (String) customer.get("cardCVV");
        this.dockCapabilities = (String) customer.get("dockCapabilities");

        // Check if the customer has an ID
        if (customer.containsKey("id")) {
            this.id = (String) customer.get("id");
        } else {
            // Create a customer ID
            StringBuilder prefix = new StringBuilder();
            StringBuilder storeId = new StringBuilder();
            String[] storeSplit = storeName.split(" ");

            for (int i = 0; i < storeSplit.length; i++) {
                if (storeSplit[i].equalsIgnoreCase("the")) {
                    prefix.append(storeSplit[i].toUpperCase());
                } else {
                    prefix.append(storeSplit[i].charAt(0));
                }
            }

            storeId.append(prefix + "_");
            for (int i = 0; i < (14 - prefix.length()); i++) {
                storeId.append(rand.nextInt(9));
            }

            this.id = storeId.toString();
        }
    }

    /**
     * Get the customer ID
     */
    public String getId() {
        return id;
    }

    /**
     * Get the beer license of the customer
     */
    public String getLicense() {
        return license;
    }

    /**
     * Get the full name of the customer
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Get the store name of the customer
     */
    public String getStoreName() {
        return storeName;
    }

    /**
     * Get the phone number of the customer
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Get the dock capabilities of the customer
     */
    public String getDockCapabilities() {
        return dockCapabilities;
    }

    /**
     * Get the street address of the customer
     */
    public String getStreetAddress() {
        return streetAddress;
    }

    /**
     * Get the city of the customer
     */
    public String getCity() {
        return city;
    }

    /**
     * Get the state of the customer
     */
    public String getState() {
        return state;
    }

    /**
     * Get the address of the customer
     */
    public String getAddress() {
        return  streetAddress + ", " + city + ", " + state;
    }

    /**
     * Get the card number of the customer
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Get the card expiration date of the customer
     */
    public String getCardExpiration() {
        return cardExpiration;
    }

    /**
     * Get the card CVV of the customer
     */
    public String getCardCVV() {
        return cardCVV;
    }

    /**
     * Set the customer ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Set the beer license of the customer
     */
    public void setLicense(String license) {
        this.license = license;
    }

    /**
     * Set the full name of the customer
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Set the store name of the customer
     */
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    /**
     * Set the phone number of the customer
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Set the dock capabilities of the customer
     */
    public void setDockCapabilities(String dockCapabilities) {
        this.dockCapabilities = dockCapabilities;
    }

    /**
     * Set the street address of the customer
     */
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    /**
     * Set the city of the customer
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Set the state of the customer
     */
    public void setState(String state) {
        this.state = state;
    }


    /**
     * Set the card number of the customer
     */
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * Set the card expiration date of the customer
     */
    public void setCardExpiration(String cardExpiration) {
        this.cardExpiration = cardExpiration;
    }

    /**
     * Set the card CVV of the customer
     */
    public void setCardCVV(String cardCVV) {
        this.cardCVV = cardCVV;
    }

    /**
     * Convert the customer to a JSON object
     */
    public JSONObject toJSON() {
        JSONObject customer = new JSONObject();
        customer.put("id", id);
        customer.put("storeName", storeName);
        customer.put("fullName", fullName);
        customer.put("phoneNumber", phoneNumber);
        customer.put("license", license);
        customer.put("streetAddress", streetAddress);
        customer.put("city", city);
        customer.put("state", state);
        customer.put("cardNumber", cardNumber);
        customer.put("cardExpiration", cardExpiration);
        customer.put("cardCVV", cardCVV);
        customer.put("dockCapabilities", dockCapabilities);

        return customer;
    }
}