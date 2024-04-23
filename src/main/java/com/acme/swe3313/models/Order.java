package com.acme.swe3313.models;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.*;

public class Order {
    private final String customerID;
    private final List<OrderItem> items;

    /**
     * Create a new order with no items
     * @param customerID
     */
    public Order(String customerID) {
        this.customerID = customerID;
        this.items = new ArrayList<>();
    }

    /**
     * Create a new order with items
     * @param customerID the ID of the customer who placed the order
     * @param items the items to add to the order
     */
    public Order(String customerID, List<OrderItem> items) {
        this.customerID = customerID;
        this.items = items;
    }

    /**
     * Create a new order from a JSON object
     * @param jsonObject the JSON object to create the order from
     */
    public Order(JSONObject jsonObject) {
        this.customerID = jsonObject.get("customerID").toString();

        // Loop through all items in the JSON object and add them to the order
        this.items = new ArrayList<>();
        for (Object item : (JSONArray) jsonObject.get("items")) {
            this.items.add(new OrderItem((JSONObject) item));
        }
    }

    /**
     * Get the ID of the customer who placed the order
     * @return the customer ID
     */
    public String getCustomerID() {
        return customerID;
    }

    /**
     * Get the items in the order
     * @return the items in the order
     */
    public List<OrderItem> getItems() {
        return items;
    }

    /**
     * Add an item to the order
     * @param item the item to add
     */
    public void addItem(OrderItem item) {
        items.add(item);
    }

    /**
     * Get the JSON representation of the order
     */
    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();

        // Add the regular fields to the JSON object
        jsonObject.put("customerID", customerID);

        // Create a JSON array to store the items and get the JSON representation of each item
        JSONArray itemsArray = new JSONArray();
        for (OrderItem item : items) {
            itemsArray.add(item.toJSON());
        }

        // Add the items array to the JSON object
        jsonObject.put("items", itemsArray);
        return jsonObject;
    }
}


/*
 * Method Ideas
 * add_item(drink, quantity)
 * remove_item(drink)
 * update_quantity(drink, quantity)
 * calculate_total()
 * cancel_order()
 * get_order_details()
 * get_order_status()
 * set_delivery_address(address)
 * */