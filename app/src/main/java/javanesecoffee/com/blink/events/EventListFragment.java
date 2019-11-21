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

import java.util.ArrayList;

import javanesecoffee.com.blink.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventListFragment extends Fragment {

    private EventListTypes type = EventListTypes.EXPLORE;

    private ListView eventListView;
    private EventsListAdapter eventListAdapter;

    //TODO: this should be of type Event
    private ArrayList<String> events = new ArrayList<>();

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

        TextView textView = getView().findViewById(R.id.typeTextView);
        textView.setText(this.type.toString());

        UpdateEventList();
        eventListView = getView().findViewById(R.id.eventListView);
        eventListAdapter = new EventsListAdapter(getContext(), R.layout.fragment_event, events);
        eventListView.setAdapter(eventListAdapter);


    }

    public void UpdateEventList()
    {
        //TODO: this list should be loaded from eventsmanager
        switch (this.type)
        {
            case EXPLORE:
                ArrayList<String> exploreEvents = new ArrayList<>();
                exploreEvents.add("Explore event 1");
                exploreEvents.add("Explore event 2");
                exploreEvents.add("Explore event 3");
                events = exploreEvents;
                break;
            case UPCOMING:
                ArrayList<String> upcomingEvents = new ArrayList<>();
                upcomingEvents.add("Upcoming event 1");
                upcomingEvents.add("Upcoming event 2");
                upcomingEvents.add("Upcoming event 3");
                events = upcomingEvents;
                break;
            case PAST_EVENTS:
                ArrayList<String> pastEvents = new ArrayList<>();
                pastEvents.add("Past event 1");
                pastEvents.add("Past event 2");
                pastEvents.add("Past event 3");
                events = pastEvents;
                break;
        }
    }
}
