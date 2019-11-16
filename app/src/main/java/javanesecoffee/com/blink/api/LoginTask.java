package javanesecoffee.com.blink.api;

import android.os.AsyncTask;

import javanesecoffee.com.blink.managers.UserManager;

public class LoginTask extends AsyncTask<String,Void,Boolean> {
    @Override
    protected Boolean doInBackground(String... params) {
        String username = params[0];
        String password = params[1];
        Boolean success = UserManager.Login(username, password);
        return success;
    }
}
