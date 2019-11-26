package javanesecoffee.com.blink.events;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

import javanesecoffee.com.blink.R;
import javanesecoffee.com.blink.api.BLinkApiException;
import javanesecoffee.com.blink.api.BLinkEventObserver;
import javanesecoffee.com.blink.constants.ApiCodes;
import javanesecoffee.com.blink.entities.Event;
import javanesecoffee.com.blink.managers.EventManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventListFragment extends Fragment implements BLinkEventObserver {

    private EventListTypes type = EventListTypes.EXPLORE;

    private ListView eventListView;
    private EventsListAdapter eventListAdapter;

    private ArrayList<Event> events = new ArrayList<>();

    public EventListFragment(){
        super();
    }

    public void setType(EventListTypes type) {
        this.type = type;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventManager.getInstance().registerObserver(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventManager.getInstance().deregisterObserver(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        eventListView = getView().findViewById(R.id.eventListView);
        AdapterView.OnItemClickListener temp = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO complete this function to call eventDescriptionActivity wrt events.get(position)
                System.out.println(events.get(position));
                Toast.makeText(getContext(), String.valueOf(events.get(position)), Toast.LENGTH_SHORT).show();
            }
        };

        eventListView.setOnItemClickListener(temp);
        eventListAdapter = new EventsListAdapter(getContext(), R.layout.fragment_event, events);
        eventListView.setAdapter(eventListAdapter);
        UpdateEventList();
    }

    public void SetEvents(ArrayList<Event> newEvents) {
//        this.events = newEvents;
        //cannot change the reference otherwise adapter loses ref
        this.events.clear();
        for(Event event : newEvents) {
            events.add(event);
        }

        if(eventListAdapter != null) {
            eventListAdapter.notifyDataSetChanged();
        }
    }
    public void UpdateEventList()
    {
        SetEvents(EventManager.getInstance().eventsForType(this.type));
    }

    @Override
    public void onBLinkEventTriggered(JSONObject response, String taskId) throws BLinkApiException {
        if(taskId == ApiCodes.TASK_LOAD_EVENTS_LIST) {
            this.UpdateEventList();
        }
    }

    @Override
    public void onBLinkEventException(BLinkApiException exception, String taskId) {

    }
}
