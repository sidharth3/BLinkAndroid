package javanesecoffee.com.blink.managers;

import java.util.ArrayList;
import java.util.List;

import javanesecoffee.com.blink.entities.User;

public class ConnectionsManager {

    public static ArrayList<User> connections = new ArrayList<>();

    public void UpdateConnections()
    {
        ArrayList<User> allConnections = LoadAllConnections();
        ConnectionsManager.connections = allConnections;
    }

    public ArrayList<User> LoadAllConnections()
    {
        ArrayList<User> output = new ArrayList<>();

        return output;
    }

    public ArrayList<User> LoadSuggestedConnections()
    {
        ArrayList<User> output = new ArrayList<>();

        return output;
    }
}
