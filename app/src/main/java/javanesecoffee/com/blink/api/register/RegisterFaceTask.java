package javanesecoffee.com.blink.api.register;

import android.os.AsyncTask;
import android.util.Log;

import java.io.File;

import javanesecoffee.com.blink.managers.UserManager;

public class RegisterFaceTask extends AsyncTask<String, Void, Boolean> {
    @Override
    protected Boolean doInBackground(String... params) {
        String username = params[0];
        String imagePath = params[1];
        File image_file = new File(imagePath);
        try {
            Boolean success = UserManager.RegisterFace(image_file, username);
            image_file.delete();
            return success;
        }
        catch(Exception e)
        {
            Log.d("RegisterFaceError", e.toString());
            image_file.delete();
            return false;
        }
    }
}
