package com.acme.swe3313.models;

import org.json.simple.JSONObject;

public class OrderItem {
    private final Product product;
    private final int quantity;

    /**
     * Create a new order item
     * @param product the product to associate with this order item
     * @param quantity the quantity of the product
     */
    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    /**
     * Create a new order item from a JSON object
     * @param jsonObject the JSON object to create the order item from
     */
    public OrderItem(JSONObject jsonObject) {
        this.product = new Product((JSONObject) jsonObject.get("product"));
        this.quantity = Integer.parseInt(jsonObject.get("quantity").toString());
    }

    /**
     * Get the product associated with this order item
     * @return the product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Get the quantity of the product associated with this order item
     * @return the quantity of the product
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Get the JSON representation of the order item
     * @return the JSON representation of the order item
     */
    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("product", product.toJSON());
        jsonObject.put("quantity", quantity);

        return jsonObject;
    }
}
