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
        try {
            if(taskId == ApiCodes.TASK_LOAD_CONNECTIONS) {
                boolean success = ResponseParser.ResponseIsSuccess(response);

                if(success) {
                    JSONObject data = ResponseParser.DataFromResponse(response);

                    //this needs to be done manually so the original reference isnt over written and then the adapter loses reference
                    recentConnections.clear();
                    recommendedConnections.clear();
                    allConnections.clear();

                    try {
                        JSONArray recent_list = data.getJSONArray("recent");
                        JSONArray recommended_list = data.getJSONArray("recommended");
                        JSONArray all_list = data.getJSONArray("all");

                        for(int i=0; i < recent_list.length(); i++){
                            recentConnections.add(new User(recent_list.getJSONObject(i)));
                        }

                        for(int i=0; i < recommended_list.length(); i++){
                            recommendedConnections.add(new User(recommended_list.getJSONObject(i)));
                        }

                        for(int i=0; i < all_list.length(); i++){
                            allConnections.add(new User(all_list.getJSONObject(i)));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (BLinkApiException e) {
            e.printStackTrace();
        }

        super.onAsyncTaskComplete(response,taskId);
    }


    @Override
    public void onAsyncTaskFailedWithException(BLinkApiException exception, String taskId) {
        super.onAsyncTaskFailedWithException(exception, taskId);
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
