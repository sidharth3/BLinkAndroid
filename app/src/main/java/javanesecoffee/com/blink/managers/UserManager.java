package javanesecoffee.com.blink.managers;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javanesecoffee.com.blink.api.AsyncResponseHandler;
import javanesecoffee.com.blink.api.BLinkApiException;
import javanesecoffee.com.blink.api.BLinkEventObserver;
import javanesecoffee.com.blink.api.LoginTask;
import javanesecoffee.com.blink.api.RegisterFaceTask;
import javanesecoffee.com.blink.api.RegisterTask;
import javanesecoffee.com.blink.constants.ApiCodes;
import javanesecoffee.com.blink.helpers.RequestHandler;
import javanesecoffee.com.blink.entities.User;
import javanesecoffee.com.blink.helpers.ResponseParser;


public class UserManager extends Manager{
    //TODO update ROOT_URL
//    private static final String ROOT_URL = "http://10.12.185.214";
    private static final String ROOT_URL = "http://192.168.1.88";
    private static final String LOGIN_URL = ROOT_URL+"/login";
    private static final String REGISTER_URL = ROOT_URL+"/register";
    private static final String REGISTER_FACE_URL = ROOT_URL+"/registerFace";
    private static final String CONNECT_URL = ROOT_URL+"/connect";

    public static UserManager singleton = new UserManager();
    public static UserManager getInstance() {
        return singleton;
    }

    private static User loggedInUser;



    /**
     * Send a login request to the URL
     *
     * @param username username for login
     * @param password password for login
     */
    public static void Login(String username, String password)
    {
        LoginTask task = new LoginTask(getInstance()); //pass singleton in as handler
        task.execute(username, password); //pass in params

    }

    public static User getLoggedInUser() {
        //TODO: TESTING ONLY
//        return loggedInUser;
        return new User("mooselliot", "email", "company");
    }

    public static void setLoggedInUser(User loggedInUser) {
        UserManager.loggedInUser = loggedInUser;
        Log.d("UserManager", "Logged in as user: " + loggedInUser.getUsername());
    }

    /**
     * Send a register request to the URL
     *
     * @param username username for login
     * @param password password for login
     * @param display_name email of user
     * @param email email of user
     *
     */

    public static void Register(String username, String password, String display_name, String email) throws BLinkApiException {
        RegisterTask task = new RegisterTask(getInstance()); //pass singleton in as handler
        task.execute(username, password, display_name ,email); //pass in params
    }

    public static void RegisterFace(File image_file, String username){
        RegisterFaceTask task = new RegisterFaceTask(getInstance()); //pass singleton in as handler
        task.execute(username, image_file.getPath()); //pass in params
    }

    @Override //UserManager on async task complete, call super to notify observers
    public void onAsyncTaskComplete(JSONObject response, String taskId) {
        super.onAsyncTaskComplete(response, taskId); //notify observers

        String status = null;

        switch (taskId)
        {

            case ApiCodes.TASK_REGISTER_FACE: //handle by user interface
                break;

            case ApiCodes.TASK_LOGIN:
            case ApiCodes.TASK_REGISTER:

                try {
                    boolean success = ResponseParser.ResponseIsSuccess(response);
                    if(success)
                    {
                        JSONObject data = ResponseParser.DataFromResponse(response);
                        User user = new User(data);
                        setLoggedInUser(user);
                    }
                } catch (BLinkApiException e) {
                    e.printStackTrace();
                }
                break;

            case ApiCodes.TASK_MORE_INFO:
                try {
                    boolean success = ResponseParser.ResponseIsSuccess(response);
                    if(success)
                    {
                        //TODO send More info POST to server
                        Log.d("UserManager", "MORE_INFO_TASK performed succesfully");
                    }
                } catch (BLinkApiException e) {
                    e.printStackTrace();
                }
                break;
            default:
                Log.d("UserManager", "Unhandled Async Task Completion");
        }
    }

    //TODO: change format to fit RegisterFace request format
    public static void Connect(String username, File image_file) throws BLinkApiException{
        try {
            RequestHandler register_req_handler = new RequestHandler(CONNECT_URL);
            register_req_handler.addFormField("username", username);
            register_req_handler.addFilePart("image_file", image_file);
            register_req_handler.finish();
        } catch (IOException e) {
            e.printStackTrace();
            throw new BLinkApiException("REGISTER_FACE_FAILED", "Request Failed");
        }
        catch (JSONException e) {
            e.printStackTrace();
            throw BLinkApiException.MALFORMED_DATA_EXCEPTION();
        }
    }
}
