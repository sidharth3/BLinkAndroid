package javanesecoffee.com.blink.api;

import org.json.JSONObject;

public interface BLinkEventObserver {
    void onBLinkEventTriggered(JSONObject response, String taskId) throws BLinkApiException;
    void onBLinkEventException(BLinkApiException exception, String taskId);
}
