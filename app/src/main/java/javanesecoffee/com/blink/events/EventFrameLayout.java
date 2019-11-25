package javanesecoffee.com.blink.events;


import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import javanesecoffee.com.blink.R;
import javanesecoffee.com.blink.api.BLinkApiException;
import javanesecoffee.com.blink.api.ImageLoadObserver;
import javanesecoffee.com.blink.entities.Event;

public class EventFrameLayout extends FrameLayout implements ImageLoadObserver {

    private Event event;

    public EventFrameLayout(@NonNull Context context) {
        super(context);
        this.setClipChildren(false);
        this.setClipToPadding(false);
    }

    public EventFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.setClipChildren(false);
        this.setClipToPadding(false);
    }

    public EventFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setClipChildren(false);
        this.setClipToPadding(false);
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void UpdateData() {
        if(this.event != null) {
            TextView eventName = this.findViewById(R.id.eventNameTextView);
            TextView eventDateTextView = this.findViewById(R.id.eventDateTextView);
            TextView eventOrganiserTextView = this.findViewById(R.id.eventOrganiserTextView);
            TextView eventTimeTextView = this.findViewById(R.id.eventTimeTextView);

            eventName.setText(event.getName());
            eventDateTextView.setText(event.getDate());
            eventTimeTextView.setText(event.getTime());
            eventOrganiserTextView.setText(event.getOrganiser());

            Bitmap image = this.event.getEventImageAndLoadIfNeeded(this);
            if(image != null) {
                ImageView imageView = this.findViewById(R.id.EventImage);
                imageView.setImageBitmap(image);
            }
        }
    }

    @Override
    public void onImageLoad(Bitmap bitmap) {
        this.UpdateData();
    }

    @Override
    public void onImageLoadFailed(BLinkApiException exception) {

    }
}
