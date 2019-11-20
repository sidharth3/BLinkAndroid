package javanesecoffee.com.blink.api;

import org.json.JSONObject;

import javanesecoffee.com.blink.helpers.ResponseParser;

public class BLinkApiException extends Exception {
    public final String status;
    public final String statusText;
    public final String message;

    public BLinkApiException(String status, String statusText, String message){
        this.status = status;
        this.statusText = statusText;
        this.message = message;
    }

    public BLinkApiException(String status, String message){
        this.status = status;
        this.statusText = "Error";
        this.message = message;
    }

    public static BLinkApiException MALFORMED_DATA_EXCEPTION()
    {
        return new BLinkApiException("MALFORMED_DATA","Invalid Data", "The received data does not conform to the required format");
    }
}
