package javanesecoffee.com.blink.entities;

import android.graphics.Bitmap;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javanesecoffee.com.blink.api.BLinkApiException;
import javanesecoffee.com.blink.api.ImageLoadObserver;
import javanesecoffee.com.blink.api.LoadImageTask;
import javanesecoffee.com.blink.constants.Endpoints;

public class User implements ImageLoadObserver {

    private String username;
    private String email;
    private String company;
    private String Facebook;
    private String Twitter;
    private String description;
    private Bitmap profilepicture;

    public User()
    {
        this.username = "";
        this.email = "";
        this.company = "";
        this.Facebook = "";
        this.Twitter = "";
        this.description = "";
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
            this.Facebook = data.getString("Facebook");
            this.Twitter = data.getString("Twitter");
            this.description = data.getString("description");
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

    public String getDescription() {
        return description;
    }

    public String getFacebook() {
        return Facebook;
    }

    public String getTwitter() {
        return Twitter;
    }

    public void RequestLoadImage() {
        if(this.username != "")
        {
            LoadImageTask task = new LoadImageTask(this);
            task.execute(Endpoints.GET_PROFILE_IMAGE, this.username);
        }
        else
        {
            Log.e("User_Error", "User has no valid username");
        }
    }

    @Override
    public void onImageLoad(Bitmap bitmap) {
        this.profilepicture = bitmap;
    }

    @Override
    public void onImageLoadFailed(BLinkApiException exception) {
        Log.e("User_Error", exception.message);
    }
}
