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

public class SocialTabCard_RecyclerViewAdapter extends RecyclerView.Adapter<SocialTabCard_RecyclerViewAdapter.ViewHolder>  {
    private static final String TAG = "SocialNameCard_Recycler";
    private ArrayList<Bitmap> sCardImage = new ArrayList<>();
    private ArrayList<String> sCardUsername = new ArrayList<>();
    private ArrayList<String> sCardDesignation = new ArrayList<>();
    private ArrayList<String> sCardCompany = new ArrayList<>();
    private Context sCardContext;
    User currentUser = UserManager.getLoggedInUser();

    public SocialTabCard_RecyclerViewAdapter(ArrayList<Bitmap> sCardImage, ArrayList<String> sCardUsername, ArrayList<String> sCardDesignation, ArrayList<String> sCardCompany, Context sCardContext) {
        this.sCardImage = sCardImage;
        this.sCardUsername = sCardUsername;
        this.sCardDesignation = sCardDesignation;
        this.sCardCompany = sCardCompany;
        this.sCardContext = sCardContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_social_small_card, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        holder.cardImage.setImageBitmap(sCardImage.get(i));
        holder.cardUsername.setText(sCardUsername.get(i));
        holder.cardDesignation.setText(sCardDesignation.get(i));
        holder.cardCompany.setText(sCardCompany.get(i));

        holder.cardViewProfile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on view profile");
                Toast.makeText(sCardContext, "loading user profile", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(sCardContext, UserDetailsActivity.class);
                intent.putExtra(IntentExtras.USER.USER_TYPE_KEY, IntentExtras.USER.USER_TYPE_EXPLORE);
                //intent.putExtra(IntentExtras.USER.USER_NAME_KEY, holder.cardUsername);
                sCardContext.startActivity(intent);
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
        TextView cardCompany;
        Button cardViewProfile;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parentLayout = itemView.findViewById(R.id.fragment_social_small_card);
            cardImage = itemView.findViewById(R.id.small_card_profile_pic);
            cardCompany = itemView.findViewById(R.id.small_card_company);
            cardDesignation = itemView.findViewById(R.id.small_card_designation);
            cardUsername = itemView.findViewById(R.id.small_card_username);

        }
    }
}
