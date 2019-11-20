package javanesecoffee.com.blink.entities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
     * @param json_obj input json file contain user data
     */
    public User(JSONObject json_obj){
        try {
            this.username = json_obj.getString("username");
            this.email = json_obj.getString("email");
            this.company = json_obj.getString("company");

        } catch (JSONException e) {
            e.printStackTrace();
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
