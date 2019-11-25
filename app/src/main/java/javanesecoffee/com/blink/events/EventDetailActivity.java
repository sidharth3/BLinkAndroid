package javanesecoffee.com.blink.events;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import javanesecoffee.com.blink.api.BLinkApiException;
import javanesecoffee.com.blink.api.ImageLoadObserver;
import javanesecoffee.com.blink.entities.Event;

public class EventDetailActivity extends AppCompatActivity implements ImageLoadObserver {
    Event currentEvent;

    TextView eventName;
    TextView eventDate;
    TextView eventTime;
    TextView eventAddress;
    TextView eventPrice;
    TextView eventDescription;
    Button eventRegister;
    RecyclerView eventTags;
    RecyclerView eventAlsoAttending;





    @Override
    public void onImageLoad(Bitmap bitmap) {

    }

    @Override
    public void onImageLoadFailed(BLinkApiException exception) {

    }
}
