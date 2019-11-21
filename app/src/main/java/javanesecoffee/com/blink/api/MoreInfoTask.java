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
        String username = params[0];
        String bio = params[1];
        String position = params[2];
        String company = params[3];
        String linkedin = params[4];
        String facebook = params[5];
        String instagram = params[6];

        RequestHandler requestHandler = RequestHandler.PostRequestHandler(Endpoints.MORE_INFO);
        requestHandler.addPostField("username", username);
        requestHandler.addPostField("bio", bio);
        requestHandler.addPostField("position", position);
        requestHandler.addPostField("company", company);
        requestHandler.addPostField("linkedin", linkedin);
        requestHandler.addPostField("facebook", facebook);
        requestHandler.addPostField("instagram", instagram);
        return requestHandler.sendPostRequest();
    }

}
