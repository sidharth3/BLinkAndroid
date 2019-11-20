package javanesecoffee.com.blink.api;

import org.json.JSONObject;


public interface AsyncResponseHandler {
    void onAsyncTaskComplete(JSONObject response, String taskId);
    void onAsyncTaskFailedWithException(BLinkApiException exception, String taskId);
}
