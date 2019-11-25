package javanesecoffee.com.blink.managers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import javanesecoffee.com.blink.api.BLinkApiException;
import javanesecoffee.com.blink.api.LoadConnectionsTask;
import javanesecoffee.com.blink.constants.ApiCodes;
import javanesecoffee.com.blink.constants.BuildModes;
import javanesecoffee.com.blink.constants.Config;
import javanesecoffee.com.blink.entities.User;
import javanesecoffee.com.blink.helpers.ResponseParser;

public class ConnectionsManager extends Manager {

    private ArrayList<User> allConnections = new ArrayList<>();
    private ArrayList<User> recentConnections = new ArrayList<>();
    private ArrayList<User> recommendedConnections = new ArrayList<>();

    public static ConnectionsManager singleton = new ConnectionsManager();

    public static ConnectionsManager getInstance() {
        return singleton;
    }

    public ConnectionsManager() {
        if (Config.buildMode == BuildModes.TEST_SOCIAL_DETAIL) {
            allConnections.add(new User("mooselliot"));
        }
    }

    public void LoadAllConnections()
    {
        if(UserManager.getLoggedInUser() != null) {
            String username = UserManager.getLoggedInUser().getUsername();
            LoadConnectionsTask task = new LoadConnectionsTask(getInstance());
            task.execute(username);
        }
    }

    public User getUserFromConnections(String username) {
        for (User user: ConnectionsManager.getInstance().allConnections) {
            if(username.equals(user.getUsername())) {
                return user;
            }
        }

        return null;
    }

    public User getUserFromExploreConnections(String username) {
        for (User user: recommendedConnections) {
            if(username == user.getUsername()) {
                return user;
            }
        }

        return null;
    }

    @Override
    public void onAsyncTaskComplete(JSONObject response, String taskId) {
        super.onAsyncTaskComplete(response,taskId);
        try {
            if(taskId == ApiCodes.TASK_LOAD_CONNECTIONS) {
                boolean success = ResponseParser.ResponseIsSuccess(response);

                if(success) {
                    JSONObject data = ResponseParser.DataFromResponse(response);
                    recentConnections = UserListFromData(data, "recent");
                    recommendedConnections = UserListFromData(data, "recommended");
                    allConnections = UserListFromData(data, "all");
                }
            }
        } catch (BLinkApiException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onAsyncTaskFailedWithException(BLinkApiException exception, String taskId) {
        super.onAsyncTaskFailedWithException(exception, taskId);
    }


    public ArrayList<User> UserListFromData(JSONObject data, String key) throws BLinkApiException{
        ArrayList<User> output = new ArrayList<>();
        try {
            JSONArray user_list = data.getJSONArray(key);

            for(int i=0; i < user_list.length(); i++){
                output.add(new User(user_list.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return output;
    }


    public ArrayList<User> getAllConnections() {
        return allConnections;
    }

    public ArrayList<User> getRecentConnections() {
        return recentConnections;
    }

    public ArrayList<User> getRecommendedConnections() {
        return recommendedConnections;
    }
}
