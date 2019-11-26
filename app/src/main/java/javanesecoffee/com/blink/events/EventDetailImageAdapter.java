package javanesecoffee.com.blink.events;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import de.hdodenhof.circleimageview.CircleImageView;
import javanesecoffee.com.blink.R;
import javanesecoffee.com.blink.api.BLinkApiException;
import javanesecoffee.com.blink.api.ImageLoadObserver;
import javanesecoffee.com.blink.entities.Event;
import javanesecoffee.com.blink.entities.User;

public class EventDetailImageAdapter {
    private Context mContext;



    public class ViewHolder extends RecyclerView.ViewHolder implements ImageLoadObserver{

        Event event;
        User user;

        CircleImageView also_attendedImage;
        ConstraintLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parentLayout = itemView.findViewById(R.id.event_also_attending);
        }

        public void UpdateData() {
            if(user == null) {
                return;
            }

            Bitmap image = user.getProfilepictureAndLoadIfNeeded(this);

            if(image != null) {
                also_attendedImage.setImageBitmap(image);
            }

        }


        @Override
        public void onImageLoad(Bitmap bitmap) {
            UpdateData();
        }

        @Override
        public void onImageLoadFailed(BLinkApiException exception) {

        }
    }


}
