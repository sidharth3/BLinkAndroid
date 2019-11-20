package javanesecoffee.com.blink.entities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javanesecoffee.com.blink.api.BLinkApiException;

public class User{

    private String username;
    private String email;
    private String company;

    public User()
    {
        this.username = "";
        this.email = "";
        this.company = "";
    }

    public User(String username, String email, String company)
    {
        this.username = username;
        this.email = email;
        this.company = company;
    }

    /**
     *
     * @param data input response.data json object for user
     */
    public User(JSONObject data) throws BLinkApiException {
        try {
            this.username = data.getString("username");
            this.email = data.getString("email");
            this.company = data.getString("company");
        } catch (JSONException e) {
            e.printStackTrace();
            throw BLinkApiException.MALFORMED_DATA_EXCEPTION();
        }

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
