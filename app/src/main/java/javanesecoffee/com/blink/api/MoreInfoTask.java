package javanesecoffee.com.blink.api;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import javanesecoffee.com.blink.constants.ApiCodes;
import javanesecoffee.com.blink.constants.Endpoints;
import javanesecoffee.com.blink.helpers.RequestHandler;

public class MoreInfoTask extends BLinkAsyncTask {
    public MoreInfoTask(AsyncResponseHandler responseHandler){
        super(responseHandler, ApiCodes.TASK_MORE_INFO);
    }

    @Override
    JSONObject executeMainTask(String... params) throws IOException, JSONException, BLinkApiException {
        String description = params[0];
        String position = params[1];
        String education = params[2];
        String interest = params[3];


        RequestHandler requestHandler = RequestHandler.PostRequestHandler(Endpoints.MORE_INFO);
        requestHandler.addPostField("description", description);
        requestHandler.addPostField("position", position);
        requestHandler.addPostField("education", education);
        requestHandler.addPostField("interest", interest);
        return requestHandler.sendPostRequest();
    }

}
