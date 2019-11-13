package javanesecoffee.com.blink;

import java.io.File;
import java.io.IOException;
import java.util.*;


public class UserRequestManager {
    //TODO update ROOT_URL
    private static final String ROOT_URL = "http://10.12.185.214";
    private static final String LOGIN_URL = ROOT_URL+"/login";
    private static final String REGISTER_URL = ROOT_URL+"/register";
    private static final String CONNECT_URL = ROOT_URL+"/connect";

    /**
     * Send a login request to the URL
     *
     * @param username username for login
     * @param password password for login
     */
    public static boolean login(String username, String password)
    {
        RequestHandler requestHandler = new RequestHandler();
        //creating request parameters
        HashMap<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);

        //returning the response
        String respond = requestHandler.sendPostRequest("http://10.12.185.214/connect", params);
        System.out.println(respond);
        if (respond.equals("success")){
            return true;
        }else{
            return false;
        }

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

    public static boolean register(String username, String password, String first_name, String last_name, String email, String birth_year, File image_file){

        RequestHandler register_req_handler = new RequestHandler(REGISTER_URL);
        String out_response = "";
        register_req_handler.addFormField("username", username);
        register_req_handler.addFormField("password", password);
        register_req_handler.addFormField("first_name", first_name);
        register_req_handler.addFormField("last_name", last_name);
        register_req_handler.addFormField("email", email);
        register_req_handler.addFormField("birth_year", birth_year);
        try {
            register_req_handler.addFilePart("image_file", image_file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            List<String> response = register_req_handler.finish();
            for (String line : response) {
                System.out.println("Upload Files Response:::" + line);
                // get your server response here.
                out_response = line;
            }
            if(out_response.equals("success")){
                return true;
            }
            return false;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    public static boolean connect(String username, File image_file){

        RequestHandler register_req_handler = new RequestHandler(CONNECT_URL);
        String out_response = "";
        register_req_handler.addFormField("username", username);
        try {
            register_req_handler.addFilePart("selfie_image", image_file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            List<String> response = register_req_handler.finish();
            for (String line : response) {
                System.out.println("Upload Files Response:::" + line);
                // get your server response here.
                out_response = line;
            }
            if(out_response.equals("success")){
                return true;
            }
            return false;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
