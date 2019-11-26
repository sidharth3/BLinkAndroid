package javanesecoffee.com.blink.events;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import javanesecoffee.com.blink.R;
import javanesecoffee.com.blink.api.BLinkApiException;
import javanesecoffee.com.blink.api.ImageLoadObserver;
import javanesecoffee.com.blink.constants.IntentExtras;
import javanesecoffee.com.blink.entities.Event;
import javanesecoffee.com.blink.entities.User;
import javanesecoffee.com.blink.managers.ConnectionsManager;
import javanesecoffee.com.blink.managers.EventManager;
import javanesecoffee.com.blink.social.SocialNameCard_RecyclerViewAdapter;
import javanesecoffee.com.blink.social.SocialSummaryFragment;
import javanesecoffee.com.blink.social.SocialTabCard_RecyclerViewAdapter;

public class EventDetailActivity extends AppCompatActivity implements ImageLoadObserver {
    Event currentEvent;

    TextView eventName;
    TextView eventDate;
    TextView eventTime;
    TextView eventLocation;
    TextView eventPrice;
    TextView eventDescription;


    Button eventRegister;
    RecyclerView eventTags;
    RecyclerView eventAlsoAttending;

    Event EVENT_KEY;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_detail_page);

        //textview
        eventName = findViewById(R.id.event_detail_name);
        eventDate = findViewById(R.id.event_detail_date);
        eventTime = findViewById(R.id.event_detail_time);
        eventLocation = findViewById(R.id.event_detail_location);
        eventPrice = findViewById(R.id.event_detail_price);
        eventDescription = findViewById(R.id.event_detail_description);


        //button
        eventRegister = findViewById(R.id.event_detail_register_button);

        //recyclerview
        eventTags = findViewById(R.id.event_detail_tags);
        eventAlsoAttending = findViewById(R.id.event_also_attending_profile_pic);

        Intent intent = getIntent();
        if (intent.hasExtra(String.valueOf(EVENT_KEY))) {
            currentEvent = EVENT_KEY;
        }

        UpdateData();
        //initRecyclerView();

    }

    public void UpdateData(){
        if(currentEvent!=null){
            Log.d("EVENT DETIALS ACTIVITY", "UpdateData: EVENT DETAILS ACTIITY");
            eventName.setText(currentEvent.getName());
            eventDate.setText(currentEvent.getDate());
            eventTime.setText(currentEvent.getTime());
            eventLocation.setText(currentEvent.getAddress());
            eventPrice.setText(currentEvent.getPrice());
            eventDescription.setText(currentEvent.getDescription());
        }



    }

    /*private void initRecyclerView() {
        ArrayList<User> alsoAttending = EventManager.getInstance().getParticipantList(currentEvent.getEvent_id());

        EventDetailImageAdapter DetailImage_adapter = new EventDetailImageAdapter(alsoAttending, this);

        eventAlsoAttending.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        EventDetailActivity.HorizontalSpaceItemDecoration spaceDecoration = new EventDetailActivity.HorizontalSpaceItemDecoration(40);
        eventAlsoAttending.addItemDecoration(spaceDecoration);

        eventAlsoAttending.setAdapter(DetailImage_adapter);
    }*/

    public class HorizontalSpaceItemDecoration extends RecyclerView.ItemDecoration {

        private final int space;

        public HorizontalSpaceItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
        }
    }

        @Override
    public void onImageLoad(Bitmap bitmap) {

    }

    @Override
    public void onImageLoadFailed(BLinkApiException exception) {

    }
}
