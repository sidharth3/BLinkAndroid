package javanesecoffee.com.blink.managers;

import java.util.ArrayList;
import java.util.List;

import javanesecoffee.com.blink.constants.BuildModes;
import javanesecoffee.com.blink.constants.Config;
import javanesecoffee.com.blink.entities.User;

public class ConnectionsManager {

    public ArrayList<User> connectionUsers = new ArrayList<>();
    public ArrayList<User> exploreUsers = new ArrayList<>();

    public static ConnectionsManager singleton = new ConnectionsManager();

    public static ConnectionsManager getInstance() {
        return singleton;
    }

    public ConnectionsManager() {
        if (Config.buildMode == BuildModes.TEST_SOCIAL_DETAIL) {
            connectionUsers.add(new User("mooselliot"));
        }
    }

    public void UpdateConnections()
    {
        ConnectionsManager.getInstance().connectionUsers = LoadAllConnections();
    }

    //TODO: add recent connections loading
    public ArrayList<User> LoadAllConnections()
    {
        ArrayList<User> output = new ArrayList<>();

        output.add(new User("mooselliot"));
        output.add(new User("sean"));

        return output;
    }


    public ArrayList<User> LoadSuggestedConnections()
    {
        ArrayList<User> output = new ArrayList<>();

        return output;
    }

    public User getUserFromConnections(String username) {
        for (User user: ConnectionsManager.getInstance().connectionUsers) {
            if(username.equals(user.getUsername())) {
                return user;
            }
        }

        return null;
    }

    public User getUserFromExploreConnections(String username) {
        for (User user: exploreUsers) {
            if(username == user.getUsername()) {
                return user;
            }
        }

        return null;
    }
}
