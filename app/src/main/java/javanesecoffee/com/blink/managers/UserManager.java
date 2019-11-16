package javanesecoffee.com.blink.managers;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javanesecoffee.com.blink.R;
import javanesecoffee.com.blink.api.AsyncResponseHandler;
import javanesecoffee.com.blink.api.BLinkApiException;
import javanesecoffee.com.blink.api.RegisterFaceTask;
import javanesecoffee.com.blink.constants.Endpoints;
import javanesecoffee.com.blink.helpers.RequestHandler;
import javanesecoffee.com.blink.entities.User;


public class UserManager implements AsyncResponseHandler {
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
    //TODO: change format to fit RegisterFace request format
    public static boolean Login(String username, String password)
    {
        RequestHandler requestHandler = new RequestHandler(Endpoints.LOGIN);
        //creating request parameters
        HashMap<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);

        //returning the response
        String respond = requestHandler.sendPostRequest("http://10.12.185.214/connect", params);
        System.out.println(respond);

        //TODO: CREATE ACTUAL USER ON LOG IN
        setLoggedInUser(new User("mooselliot", "email", "company"));

        if (respond.equals("success")){
            return true;
        }else{
            return false;
        }

    }

    public static User getLoggedInUser() {
        //TODO: TESTING ONLY
//        return loggedInUser;
        return new User("mooselliot", "email", "company");
    }

    public static void setLoggedInUser(User loggedInUser) {
        UserManager.loggedInUser = loggedInUser;
    }

    /**
     * Send a register request to the URL
     *
     * @param username username for login
     * @param password password for login
     * @param first_name first_name of user
     * @param last_name last_name of user
     * @param email email of user
     * @param birth_year birth_year of user
     * @param image_file selfie sent by user
     *
     */
//    public static boolean register(String username, File faceimage){

    //TODO: change format to fit RegisterFace request format
    public static boolean Register(String username, String password, String first_name, String last_name, String email, String birth_year, File image_file) throws BLinkApiException {
        try {
            RequestHandler register_req_handler = new RequestHandler(REGISTER_URL);
            register_req_handler.addFormField("username", username);
            register_req_handler.addFormField("password", password);
            register_req_handler.addFormField("first_name", first_name);
            register_req_handler.addFormField("last_name", last_name);
            register_req_handler.addFormField("email", email);
            register_req_handler.addFormField("birth_year", birth_year);

            register_req_handler.addFilePart("image_file", image_file);

            JSONObject response = register_req_handler.finish();
            Boolean success = response.getBoolean("success");

            if(success)
            {
                //TODO: CREATE ACTUAL USER ON LOG IN
                setLoggedInUser(new User("mooselliot", "email", "company"));
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new BLinkApiException("REGISTER_FAILED", "Request Failed");
        }
        catch (JSONException e) {
            e.printStackTrace();
            throw new BLinkApiException("REGISTER_MALFORMED_DATA","The server's response was invalid");
        }

        return false;
    }

    public static void RegisterFace(File image_file, String username){
        RegisterFaceTask task = new RegisterFaceTask(getInstance(), R.string.TASK_REGISTER_FACE); //pass singleton in as handler, and taskId to identify task
        task.execute(username, image_file.getPath()); //pass in params
    }

    @Override
    public void onAsyncTaskComplete(JSONObject response, int responseCode) {
        if(responseCode == R.string.TASK_REGISTER_FACE)
        {
            Log.d("TASK_REGISTER_FACE", response.toString());
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
            throw new BLinkApiException("REGISTER_FACE_MALFORMED_DATA","The server's response was invalid");
        }
    }
}
