package javanesecoffee.com.blink.social;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.sql.Connection;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import javanesecoffee.com.blink.R;
import javanesecoffee.com.blink.constants.IntentExtras;
import javanesecoffee.com.blink.entities.User;
import javanesecoffee.com.blink.managers.ConnectionsManager;
import javanesecoffee.com.blink.managers.UserManager;

public class SocialNameCard_RecyclerViewAdapter extends RecyclerView.Adapter<SocialNameCard_RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "SocialNameCard_Recycler";
    private ArrayList<Bitmap> bCardImage = new ArrayList<>();
    private ArrayList<String> bCardUsername = new ArrayList<>();
    private ArrayList<String> bCardDesignation = new ArrayList<>();
    private ArrayList<String> bCardEmail = new ArrayList<>();
    private ArrayList<String> bCardLinkedin = new ArrayList<>();
    private ArrayList<String> bCardFacebook = new ArrayList<>();
    private ArrayList<String> bCardInstagram = new ArrayList<>();
    private Context bCardContext;
    User currentUser = UserManager.getLoggedInUser();
    public SocialNameCard_RecyclerViewAdapter(ArrayList<Bitmap> bCardImage, ArrayList<String> bCardUsername, ArrayList<String> bCardDesignation, ArrayList<String> bCardContactDetails, ArrayList<String> bCardEmail, ArrayList<String> bCardLinkedin, ArrayList<String> bCardFacebook, ArrayList<String> bCardInstagram, Context bCardContext) {
        this.bCardImage = bCardImage;
        this.bCardUsername = bCardUsername;
        this.bCardDesignation = bCardDesignation;
        this.bCardEmail = bCardEmail;
        this.bCardLinkedin = bCardLinkedin;
        this.bCardFacebook = bCardFacebook;
        this.bCardInstagram = bCardInstagram;
        this.bCardContext = bCardContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_social_card, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int i) {
        Log.d(TAG, "onBindViewHolder:called ");
        holder.cardImage.setImageBitmap(bCardImage.get(i));
        holder.cardUsername.setText(bCardUsername.get(i));
        holder.cardDesignation.setText(bCardDesignation.get(i));
        //holder.cardContactDetails.setText(bCardContactDetails.get(i));
        holder.cardEmail.setText(bCardEmail.get(i));
        holder.cardLinkedin.setText(bCardLinkedin.get(i));
        holder.cardFacebook.setText(bCardFacebook.get(i));
        holder.cardInstagram.setText(bCardInstagram.get(i));

        holder.cardViewProfile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on view profile");
                Toast.makeText(bCardContext, "loading user profile",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(bCardContext, UserDetailsActivity.class);
                intent.putExtra(IntentExtras.USER.USER_TYPE_KEY,IntentExtras.USER.USER_TYPE_CONNECTION);
                bCardContext.startActivity(intent);
        }
        });

        holder.cardViewConnections.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on view connections");
                Toast.makeText(bCardContext, "loading user connections",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView cardImage;
        TextView cardUsername;
        TextView cardDesignation;
        LinearLayout cardContactDetails;
        TextView cardEmail;
        TextView cardLinkedin;
        TextView cardFacebook;
        TextView cardInstagram;
        RelativeLayout parentLayout;
        Button cardViewProfile;
        Button cardViewConnections;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parentLayout = itemView.findViewById(R.id.fragment_social_card);
            cardImage = itemView.findViewById(R.id.card_profile_pic);
            cardDesignation = itemView.findViewById(R.id.card_designation);
            cardUsername = itemView.findViewById(R.id.card_username);
            cardContactDetails = itemView.findViewById(R.id.card_contact_details);
            cardEmail = itemView.findViewById(R.id.card_email);
            cardLinkedin = itemView.findViewById(R.id.card_linkedin);
            cardFacebook = itemView.findViewById(R.id.card_facebook);
            cardInstagram = itemView.findViewById(R.id.card_instagram);
            cardViewProfile = itemView.findViewById(R.id.card_view_profile);
            cardViewConnections = itemView.findViewById(R.id.card_view_connection);


        }


    }

 }
