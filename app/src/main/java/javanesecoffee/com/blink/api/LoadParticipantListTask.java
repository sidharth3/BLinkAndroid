package javanesecoffee.com.blink.api;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import javanesecoffee.com.blink.constants.ApiCodes;
import javanesecoffee.com.blink.constants.Endpoints;
import javanesecoffee.com.blink.helpers.RequestHandler;

public class LoadParticipantListTask extends BLinkAsyncTask{
    public LoadParticipantListTask(AsyncResponseHandler responseHandler){
        super(responseHandler, ApiCodes.TASK_LOAD_PARTICIPANT_LIST);
    }

    @Override
    JSONObject executeMainTask(String... params) throws IOException, JSONException, BLinkApiException {
        String event_id = params[0];

        RequestHandler requestHandler = RequestHandler.PostRequestHandler(Endpoints.LOAD_PARTICIPANT_LIST);
        requestHandler.addPostField("event_id", event_id);

        return requestHandler.sendPostRequest();
    }
}
