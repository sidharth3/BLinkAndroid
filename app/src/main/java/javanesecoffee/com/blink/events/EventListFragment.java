package javanesecoffee.com.blink.events;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

import javanesecoffee.com.blink.R;
import javanesecoffee.com.blink.entities.Event;
import javanesecoffee.com.blink.managers.EventManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventListFragment extends Fragment {

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //TextView textView = getView().findViewById(R.id.typeTextView);
        //textView.setText(this.type.toString());

        UpdateEventList();
        eventListView = getView().findViewById(R.id.eventListView);
        eventListAdapter = new EventsListAdapter(getContext(), R.layout.fragment_event, events);
        eventListView.setAdapter(eventListAdapter);

    }

    public void SetEvents(ArrayList<Event> events) {
        this.events = events;
        if(eventListAdapter != null) {
            eventListAdapter.notifyDataSetChanged();
        }
    }
    public void UpdateEventList()
    {
        SetEvents(EventManager.getInstance().eventsForType(this.type));
    }
}
