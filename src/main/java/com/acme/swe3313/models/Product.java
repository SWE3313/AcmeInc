package com.acme.swe3313.models;

import org.json.simple.JSONObject;

public class Product {
    private long id;
    private String description;
    private String supplierName;
    private String brandName;
    private long  quantity;

    /**
     * Create a new Product by inputting all the necessary fields
     * @param id
     * @param description
     * @param supplierName
     * @param brandName
     * @param quantity
     */
    public Product(int id, String description, String supplierName, String brandName, int quantity) {
        this.id = id;
        this.description = description;
        this.supplierName = supplierName;
        this.brandName = brandName;
        this.quantity = quantity;
    }

    /**
     * Overloaded constructor to create a Product from a JSON object
     * @param json
     */
    public Product(JSONObject json) {
        this.id = (long) json.get("id");
        this.description = (String) json.get("description");
        this.supplierName = (String) json.get("supplier_name");
        this.brandName = (String) json.get("brand_name");
        this.quantity = (long) json.get("quantity");
    }

    /**
     * Get the ID of the product
     * @return the ID of the product
     */
    public long getId() {
        return id;
    }

    /**
     * Get the description of the product
     * @return the description of the product
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the supplier name of the product
     * @return the supplier name of the product
     */
    public String getSupplierName() {
        return supplierName;
    }

    /**
     * Get the brand name of the product
     * @return the brand name of the product
     */
    public String getBrandName() {
        return brandName;
    }

    /**
     * Get the quantity of the product
     * @return the quantity of the product
     */
    public long getQuantity() {
        return quantity;
    }

    /**
     * Set the quantity of the product
     * @param quantity the new quantity of the product
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Decrease the quantity of the product by a given amount
     * @param amount the amount to decrease the quantity by
     */
    public void decreaseQuantity(int amount) {
        this.quantity -= amount;
    }

    /**
     * Increase the quantity of the product by a given amount
     * @param amount the amount to increase the quantity by
     */
    public void increaseQuantity(int amount) {
        this.quantity += amount;
    }

    /**
     * Convert the product to a JSON object
     * @return the JSON object representation of the product
     */
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();

        json.put("id", id);
        json.put("description", description);
        json.put("supplier_name", supplierName);
        json.put("brand_name", brandName);
        json.put("quantity", quantity);

        return json;
    }
}