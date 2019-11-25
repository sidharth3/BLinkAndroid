package javanesecoffee.com.blink.events;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import javanesecoffee.com.blink.R;
import javanesecoffee.com.blink.entities.Event;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventFragment extends Fragment {

    private Event event;
    public EventFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event, container, false);
    }

    public void LoadEvent(Event event) {
        this.event = event;
        TextView eventNameTextView = getView().findViewById(R.id.eventNameTextView);
        TextView eventDateTextView = getView().findViewById(R.id.eventDateTextView);
        TextView eventOrganiserTextView = getView().findViewById(R.id.eventOrganiserTextView);
        TextView eventTimeTextView = getView().findViewById(R.id.eventTimeTextView);
        eventNameTextView.setText(event.getName());
        eventDateTextView.setText(event.getDate());
        eventTimeTextView.setText(event.getTime());
        eventOrganiserTextView.setText(event.getOrganiser());

    }

}
