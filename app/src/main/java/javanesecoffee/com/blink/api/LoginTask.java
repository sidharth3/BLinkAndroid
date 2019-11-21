package javanesecoffee.com.blink.api;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import javanesecoffee.com.blink.constants.ApiCodes;
import javanesecoffee.com.blink.constants.Endpoints;
import javanesecoffee.com.blink.helpers.RequestHandler;

public class LoginTask extends BLinkAsyncTask {
    public LoginTask(AsyncResponseHandler requestHandler)
    {
        super(requestHandler, ApiCodes.TASK_LOGIN);
    }

    @Override
    JSONObject executeMainTask(String... params) throws IOException, JSONException, BLinkApiException{
        String username = params[0];
        String password = params[1];

        RequestHandler requestHandler = RequestHandler.PostRequestHandler(Endpoints.LOGIN);
        requestHandler.addPostField("username", username);
        requestHandler.addPostField("password", password);

        return requestHandler.sendPostRequest();
    }
}
