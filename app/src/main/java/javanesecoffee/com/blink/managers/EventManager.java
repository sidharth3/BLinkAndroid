package javanesecoffee.com.blink.managers;

import android.renderscript.AllocationAdapter;
import android.util.JsonReader;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import javanesecoffee.com.blink.api.BLinkApiException;
import javanesecoffee.com.blink.api.LoadEventListTask;
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
    public static EventListTypes current_request_event_list_type;

    ArrayList<Event> pastEvents = new ArrayList<>();
    ArrayList<Event> upcomingEvents = new ArrayList<>();
    ArrayList<Event> exploreEvents = new ArrayList<>();

    EventManager() {
        super();
        upcomingEvents.add(new Event("Industry Night 2019", "SUTD", "We are having industry night whoop!", "8 Somapah Road", "08/10/19", "9:00pm","FREE", "SOME_EVENT_ID"));
        upcomingEvents.add(new Event("Hackathon 2019", "SUTD", "We are having industry night whoop!", "8 Somapah Road", "08/10/19", "9:00pm","FREE", "SOME_EVENT_ID"));
        exploreEvents.add(new Event("Recruitment Talk", "MasterCard", "A talk!", "8 Somapah Road", "12/12/19", "6:00pm", "FREE", "SOME_EVENT_ID" ));
    }

    /**
     * Method to be called from activity
     * @param event_id
     */
    public void getParticipantList(String event_id){
        LoadParticipantListTask load_participant_list = new LoadParticipantListTask(getInstance());
        load_participant_list.execute(event_id);
    }

    /**
     * Method to be called from activity
     * @param username
     * @param type
     */
    public void getEventsList(String username, EventListTypes type ){
        //TODO: Load Event Task
        LoadEventListTask load_event_list = new LoadEventListTask(getInstance());
        current_request_event_list_type = type; // set the desired event type as the current event list type for request
        load_event_list.execute(username);
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
                        EventManager.participant_list = getUserList(data);
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
                        getEvents(data);//Parse data into 4 arrayList of event list type

                    }
                } catch (BLinkApiException e) {
                    e.printStackTrace();
                }
                break;
            default:
                Log.d("UserManager", "Unhandled Async Task Completion");
        }
    }

    public ArrayList<User> getUserList(JSONObject json_object){
        ArrayList<User> user_list = new ArrayList<>();
        try {
            //TODO: change user_list into the correct json field name
            JSONArray json_user_list = json_object.getJSONArray("user_list");
            for(int i = 0; i < json_user_list.length(); i++){
                user_list.add(new User(json_user_list.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (BLinkApiException e){
            e.printStackTrace();
        }
        return user_list;
    }

    public ArrayList<Event> getEvents(JSONObject json_object) {
        ArrayList<Event> event_list = new ArrayList<>();
        try {
            JSONObject data = json_object.getJSONObject("data");

            switch (current_request_event_list_type)
            {
                case EXPLORE:
                    JSONArray explore_event_list = data.getJSONArray("explore");
                    for(int i=0; i < explore_event_list.length(); i++){
                        exploreEvents.add(new Event(explore_event_list.getJSONObject(i)));
                    }
                case UPCOMING:
                    JSONArray upcoming_event_list = data.getJSONArray("upcoming");
                    for(int i=0; i < upcoming_event_list.length(); i++){
                        upcomingEvents.add(new Event(upcoming_event_list.getJSONObject(i)));
                    }
                case PAST_EVENTS:
                    JSONArray past_event_list = data.getJSONArray("past");
                    for(int i=0; i < past_event_list.length(); i++){
                        pastEvents.add(new Event(past_event_list.getJSONObject(i)));
                    }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (BLinkApiException e) {
            e.printStackTrace();
        }


        return new ArrayList<>();
    }

    public ArrayList<Event> eventsForType(EventListTypes type) {
        switch(type) {
            case EXPLORE:
                return exploreEvents;
            case UPCOMING:
                return upcomingEvents;
            case PAST_EVENTS:
                return pastEvents;
            default:
                return new ArrayList<>();
        }
    }
}
