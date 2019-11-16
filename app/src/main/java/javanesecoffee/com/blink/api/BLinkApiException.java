package javanesecoffee.com.blink.api;

public class BLinkApiException extends Exception {
    private final String status;
    private final String statusText;
    private final String message;

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

}
