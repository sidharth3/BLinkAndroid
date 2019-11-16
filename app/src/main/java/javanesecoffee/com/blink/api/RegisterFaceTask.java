package javanesecoffee.com.blink.api;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import javanesecoffee.com.blink.R;
import javanesecoffee.com.blink.constants.Endpoints;
import javanesecoffee.com.blink.helpers.RequestHandler;

public class RegisterFaceTask extends BLinkAsyncTask {

    private File image_file;

    public RegisterFaceTask(AsyncResponseHandler requestHandler, int taskId)
    {
        super(requestHandler, taskId);
    }

    @Override
    JSONObject executeMainTask(String... params) throws IOException, JSONException {
        String username = params[0];
        String imagePath = params[1];
        image_file = new File(imagePath);

        RequestHandler register_req_handler = new RequestHandler(Endpoints.REGISTER_FACE);
        register_req_handler.addFormField("username", username);
        register_req_handler.addFilePart("image_file", image_file);
        return register_req_handler.finish();
    }

    @Override
    void onTaskComplete(JSONObject jsonObject) {
        //cleanup
        if(image_file != null)
        {
            image_file.delete();
        }
    }
}
