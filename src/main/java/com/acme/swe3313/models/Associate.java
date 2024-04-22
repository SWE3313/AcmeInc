package com.acme.swe3313.models;

import com.acme.swe3313.Application;
import com.acme.swe3313.util.JSON;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Associate {
    private String name;
    private String email;
    private boolean isLoggedIn = false;

    /**
     * Get the associate's name
     * @return the associate's name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the associate's email
     * @return the associate's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Check if the associate is logged in
     * @return true if the associate is logged in, false otherwise
     */
    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    /**
     * Log into an associates account, sets all the associate's information if successful
     * @param email
     * @param password
     * @return
     */
    public boolean login(String email, String password) {
        // Read the associates.json file, and parse it's JSON content
        JSONObject associates = JSON.parseObject("/associates.json");

        if (associates == null) {
            return false;
        }

        // Get the associate with the given email (if it exists)
        JSONObject associate = (JSONObject) associates.get(email);

        // If the associate exists, and the password matches, set the associate's information
        if (associate != null) {
            if (associate.get("password").equals(password)) {
                this.name = (String) associate.get("name");
                this.email = email;
                this.isLoggedIn = true;


                return true;
            }
        }

        return false;
    }


}