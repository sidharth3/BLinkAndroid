package javanesecoffee.com.blink.helpers;

import android.provider.ContactsContract;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import javanesecoffee.com.blink.api.BLinkApiException;
import javanesecoffee.com.blink.entities.User;

public class ResponseParser {
    public static boolean ResponseIsSuccess(JSONObject response) throws BLinkApiException
    {
        String status = "";
        try {
            status = response.getString("status");
        } catch (JSONException e) {
            e.printStackTrace();
            throw BLinkApiException.MALFORMED_DATA_EXCEPTION();
        }

        if(status != null)
        {
            if(status == "SUCCESS")
            {
                return true;
            }
            else
            {
                //throws server given error
                throw ExceptionFromResponse(response);
            }
        }
        else
        {
            throw BLinkApiException.MALFORMED_DATA_EXCEPTION();
        }
    }

    public static User UserFromData(JSONObject data) throws BLinkApiException
    {
        try {
            String username = data.getString("username");
            String email = data.getString("email");
            String company = data.getString("company");

            User user = new User(username, email, company);
            return user;

        } catch (JSONException e) {
            e.printStackTrace();
            throw BLinkApiException.MALFORMED_DATA_EXCEPTION();
        }
    }

    public static JSONObject DataFromResponse(JSONObject response) throws BLinkApiException
    {
        try {
            JSONObject data = response.getJSONObject("data");
            return data;
        } catch (JSONException e) {
            e.printStackTrace();
            throw BLinkApiException.MALFORMED_DATA_EXCEPTION();
        }
    }

    public static BLinkApiException ExceptionFromData(JSONObject data) throws BLinkApiException
    {
        try {
            String status = data.getString("status");
            String statusText = data.getString("statusText");
            String message = data.getString("message");
            return new BLinkApiException(status, statusText, message);

        } catch (JSONException e) {
            e.printStackTrace();
            throw BLinkApiException.MALFORMED_DATA_EXCEPTION();
        }
    }

    public static BLinkApiException ExceptionFromResponse(JSONObject response) throws BLinkApiException
    {
        return ExceptionFromData(DataFromResponse(response));
    }

}
