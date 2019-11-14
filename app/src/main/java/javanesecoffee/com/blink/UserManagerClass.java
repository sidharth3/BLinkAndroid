package com.example.blink;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static android.content.ContentValues.TAG;


public class UserManagerClass {
    private static final String ROOT_URL = "localhost";
    private static final String LOGIN_URL = ROOT_URL+"/login";
    private static final String REGISTER_URL = ROOT_URL+"/register";

//    private static UserManagerClass mInstance;
//    private static Context ctx;
//
//    private UserManagerClass(Context context){
//        ctx = context;
//    }
//
//    public static synchronized UserManagerClass getInstance(Context context) {
//        if(mInstance==null){
//            mInstance = new UserManagerClass(context);
//        }
//        return mInstance;
//    }
//
//        public void userLogin(){
//        final String username = "pepo";
//        final String password = "1234";
//    }

    public static boolean login(String username, String password)
    {

        RequestHandler requestHandler = new RequestHandler();
        //creating request parameters
        HashMap<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);

        //returning the response
        if (requestHandler.sendPostRequest(LOGIN_URL, params).equals("success")){
            return true;
        }else{
            return false;
        }

    }

    public static boolean register(String username, String password, String first_name, String last_name, String email, String birth_year, File faceimage){

        RequestHandler register_req_handler = new RequestHandler(REGISTER_URL);
        String out_response = "";
        register_req_handler.addFormField("username", username);
        register_req_handler.addFormField("password", password);
        register_req_handler.addFormField("first_name", first_name);
        register_req_handler.addFormField("last_name", last_name);
        register_req_handler.addFormField("email", email);
        register_req_handler.addFormField("birth_year", birth_year);
        try {
            register_req_handler.addFilePart("faceimage", faceimage);
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
