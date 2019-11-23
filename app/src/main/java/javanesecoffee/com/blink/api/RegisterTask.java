package javanesecoffee.com.blink.api;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import javanesecoffee.com.blink.constants.ApiCodes;
import javanesecoffee.com.blink.constants.Endpoints;
import javanesecoffee.com.blink.helpers.RequestHandler;

public class RegisterTask extends BLinkAsyncTask {
    public RegisterTask(AsyncResponseHandler requestHandler)
    {
        super(requestHandler, ApiCodes.TASK_REGISTER);
    }

    @Override
    JSONObject executeMainTask(String... params) throws IOException, JSONException, BLinkApiException {
        String username = params[0];
        String password = params[1];
        String displayname = params[2];
        String email = params[3];


        RequestHandler requestHandler = RequestHandler.PostRequestHandler(Endpoints.REGISTER);
        requestHandler.addPostField("username", username);
        requestHandler.addPostField("password", password);
        requestHandler.addPostField("displayname", displayname );
        requestHandler.addPostField("email", email);
        return requestHandler.sendPostRequest();
    }
}
