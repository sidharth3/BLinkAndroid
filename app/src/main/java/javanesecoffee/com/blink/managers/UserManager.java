package javanesecoffee.com.blink.managers;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javanesecoffee.com.blink.api.BLinkApiException;
import javanesecoffee.com.blink.api.ConnectUserTask;
import javanesecoffee.com.blink.api.LoginTask;
import javanesecoffee.com.blink.api.MoreInfoTask;
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
    private static ArrayList<String> user_recognised = new ArrayList<>();

    public static void setUser_recognised(ArrayList<String> user_recognised) {
        UserManager.user_recognised = user_recognised;
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

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

    public static void setLoggedInUser(User loggedInUser) {
        UserManager.loggedInUser = loggedInUser;
        Log.d("UserManager", "Logged in as user: " + loggedInUser.getUsername());
    }

    /**
     * Send a register request to the URL
     *
     * @param username username for login
     * @param password password for login
     * @param displayname email of user
     * @param email email of user
     *
     */

    public static void Register(String username, String password, String displayname, String email){
        RegisterTask task = new RegisterTask(getInstance()); //pass singleton in as handler
        task.execute(username, password, displayname ,email); //pass in params
    }

    public static void RegisterFace(File image_file, String username){
        RegisterFaceTask task = new RegisterFaceTask(getInstance()); //pass singleton in as handler
        task.execute(username, image_file.getPath()); //pass in params
    }

    public static void RegisterMoreInfo(String bio, String position, String company, String linkedin, String facebook, String instagram) throws BLinkApiException{
        User user = getLoggedInUser();

        if(user != null && user.getUsername() != "") {
            MoreInfoTask task = new MoreInfoTask(getInstance()); //pass singleton in as handler
            task.execute(user.getUsername(), bio, position, company, linkedin, facebook, instagram); //pass in params
        }
        else
        {
            throw new BLinkApiException("MORE_INFO_ERROR", "More Info Error", "Invalid user. Please try again.");
        }
    }

    public static void ConnectUsers(File image_file, String username){
        ConnectUserTask task = new ConnectUserTask(getInstance());
        task.execute(username, image_file.getPath());
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
            case ApiCodes.TASK_CONNECT_USERS:
                try {
                    boolean success = ResponseParser.ResponseIsSuccess(response);
                    if(success)
                    {

                        JSONObject data = ResponseParser.DataFromResponse(response);
                        JSONArray users_regconised = data.getJSONArray("data");
                        setUser_recognised(ConnectUser(users_regconised)); //this update the latest
                        //TODO SEND A SECOND REQUEST HERE TO UPDATE THE RECENT USER

                    }
                } catch (BLinkApiException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                break;
            default:
                Log.d("UserManager", "Unhandled Async Task Completion");
        }
    }

    public ArrayList<String> ConnectUser(JSONArray json_array){
        ArrayList<String> connected_user = new ArrayList<>();
        for(int i = 0; i < json_array.length(); i++){
            try {
                connected_user.add(json_array.getString(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return connected_user;
    }

//    public static void Connect(String username, File image_file) throws BLinkApiException{
//        try {
//            RequestHandler register_req_handler = RequestHandler.FormRequestHandler(CONNECT_URL);
//            register_req_handler.addFormField("username", username);
//            register_req_handler.addFilePart("image_file", image_file);
//            register_req_handler.sendFormDataRequest();
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new BLinkApiException("REGISTER_FACE_FAILED", "Request Failed");
//        }
//        catch (JSONException e) {
//            e.printStackTrace();
//            throw BLinkApiException.MALFORMED_DATA_EXCEPTION();
//        }
//    }
}
