package javanesecoffee.com.blink.api;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import javanesecoffee.com.blink.R;
import javanesecoffee.com.blink.constants.Endpoints;
import javanesecoffee.com.blink.helpers.RequestHandler;

public class RegisterTask extends BLinkAsyncTask {
    public RegisterTask(AsyncResponseHandler requestHandler)
    {
        super(requestHandler, R.string.TASK_REGISTER);
    }

    @Override
    JSONObject executeMainTask(String... params) throws IOException, JSONException {
        String username = params[0];
        String password = params[1];
        String first_name = params[2];
        String last_name = params[3];
        String email = params[4];
        String birth_year = params[5];


        RequestHandler requestHandler = new RequestHandler(Endpoints.REGISTER);
        requestHandler.addFormField("username", username);
        requestHandler.addFormField("password", password);
        requestHandler.addFormField("first_name", first_name);
        requestHandler.addFormField("last_name", last_name);
        requestHandler.addFormField("email", email);
        requestHandler.addFormField("birth_year", birth_year);

        return requestHandler.finish();
    }
}
