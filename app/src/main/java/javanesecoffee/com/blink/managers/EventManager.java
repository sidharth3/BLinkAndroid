package javanesecoffee.com.blink.managers;

import android.util.Log;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import javanesecoffee.com.blink.api.BLinkApiException;
import javanesecoffee.com.blink.api.LoadParticipantListTask;
import javanesecoffee.com.blink.constants.ApiCodes;
import javanesecoffee.com.blink.entities.Event;
import javanesecoffee.com.blink.entities.User;
import javanesecoffee.com.blink.events.EventListTypes;
import javanesecoffee.com.blink.helpers.ResponseParser;

public class EventManager extends Manager {
    public static EventManager singleton = new EventManager();
    public static EventManager getInstance() {
        return singleton;
    }

    public static ArrayList<User> participant_list = new ArrayList<>();

    ArrayList<Event> pastEvents = new ArrayList<>();
    ArrayList<Event> upcomingEvents = new ArrayList<>();
    ArrayList<Event> exploreEvents = new ArrayList<>();



    public void getParticipantList(String event_id){
        LoadParticipantListTask load_participant_list = new LoadParticipantListTask(getInstance());
        load_participant_list.execute(event_id);
    }

    public void getEventsList(){
        //TODO: Load Event Task
//        LoadParticipantListTask load_participant_list = new LoadParticipantListTask(getInstance());
//        load_participant_list.execute();
    }

    @Override //UserManager on async task complete, call super to notify observers
    public void onAsyncTaskComplete(JSONObject response, String taskId) {
        super.onAsyncTaskComplete(response, taskId); //notify observers

        switch (taskId)
        {
            case ApiCodes.TASK_LOAD_PARTICIPANT_LIST:

                try {
                    boolean success = ResponseParser.ResponseIsSuccess(response);
                    if(success)
                    {
                        JSONObject data = ResponseParser.DataFromResponse(response);
                        //TODO: Parse data into array.

                    }
                } catch (BLinkApiException e) {
                    e.printStackTrace();
                }
                break;

            case ApiCodes.TASK_LOAD_EVENTS_LIST:
                try {
                    boolean success = ResponseParser.ResponseIsSuccess(response);
                    if(success)
                    {
                        JSONObject data = ResponseParser.DataFromResponse(response);
                        //TODO: Parse data into the 3 types of arrays.
                        //TODO: Explore, Upcoming, Past Events

                    }
                } catch (BLinkApiException e) {
                    e.printStackTrace();
                }
                break;
            default:
                Log.d("UserManager", "Unhandled Async Task Completion");
        }
    }

    public ArrayList<Event> getEvents(EventListTypes type) {
        switch (type)
        {
            case EXPLORE:
                return exploreEvents;
            case UPCOMING:
                return upcomingEvents;
            case PAST_EVENTS:
                return pastEvents;
        }

        return new ArrayList<>();
    }
}
