package javanesecoffee.com.blink.api;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javanesecoffee.com.blink.R;
import javanesecoffee.com.blink.constants.ApiCodes;
import javanesecoffee.com.blink.constants.Endpoints;
import javanesecoffee.com.blink.helpers.RequestHandler;
import javanesecoffee.com.blink.managers.UserManager;

public class LoginTask extends BLinkAsyncTask {
    public LoginTask(AsyncResponseHandler requestHandler)
    {
        super(requestHandler, ApiCodes.TASK_LOGIN);
    }

    @Override
    JSONObject executeMainTask(String... params) throws IOException, JSONException, BLinkApiException{
        String username = params[0];
        String password = params[1];
        RequestHandler requestHandler = new RequestHandler(Endpoints.LOGIN);

        //inserting request parameters
        HashMap<String, String> postParams = new HashMap<>();
        postParams.put("username", username);
        postParams.put("password", password);

        return requestHandler.finish();
    }
}
