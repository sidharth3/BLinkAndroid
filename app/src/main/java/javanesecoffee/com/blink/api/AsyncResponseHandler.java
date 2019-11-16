package javanesecoffee.com.blink.api;

import org.json.JSONObject;


public interface AsyncResponseHandler {
    void onAsyncTaskComplete(JSONObject response, int taskId);
}
