package javanesecoffee.com.blink.events;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import javanesecoffee.com.blink.R;

//TODO: this should be of type Event
public class EventsListAdapter extends ArrayAdapter<String> {

    private int resourceLayout;
    private Context mContext;

    public EventsListAdapter(Context context, int resource, List<String> items) {
        super(context, resource, items);
        this.resourceLayout = resource;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            v = vi.inflate(resourceLayout, null);
        }

        String event = getItem(position);

        //TODO: this should link up with the respective UI elementss in the fragment_event
        if (event != null) {
            TextView eventNameTextView = v.findViewById(R.id.eventNameTextView);
            eventNameTextView.setText(event);
        }

        return v;
    }

}