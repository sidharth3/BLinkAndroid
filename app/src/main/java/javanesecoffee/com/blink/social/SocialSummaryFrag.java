package javanesecoffee.com.blink.social;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import javanesecoffee.com.blink.R;
import javanesecoffee.com.blink.constants.IntentExtras;
import javanesecoffee.com.blink.entities.User;
import javanesecoffee.com.blink.managers.ConnectionsManager;
import javanesecoffee.com.blink.managers.UserManager;

import static android.support.constraint.Constraints.TAG;

public class SocialSummaryFrag extends Fragment {

    CircleImageView editProfilePic;
    TextView editUsername;
    Button viewProfile;
    RecyclerView recyclerView_NameCard;
    RecyclerView recyclerView_SmallCard;

    ConnectionsManager connectionsManager;
    ArrayList<User> recentConnectionUsers = new ArrayList<>();
    ArrayList<User> recommendedConnectionUsers = new ArrayList<>();
    User currentUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_social_summary, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editUsername = view.findViewById(R.id.fieldSocialUsername);
        editProfilePic = view.findViewById(R.id.social_profile_pic);
        viewProfile = view.findViewById(R.id.fieldSocialViewProfile);

        currentUser = UserManager.getLoggedInUser();
        connectionsManager = ConnectionsManager.getInstance();
        recentConnectionUsers = connectionsManager.LoadAllConnections();
        recommendedConnectionUsers = connectionsManager.LoadSuggestedConnections();

        Log.d("SUMMARY", recentConnectionUsers.size() + "");
        recyclerView_NameCard = view.findViewById(R.id.socialNameCardRecycler);
        recyclerView_SmallCard = view.findViewById(R.id.socialSmallCardRecycler);

        Log.d("SUMMARY", recentConnectionUsers.size() + "");

        //load up the correct usernames and profile pic on screen
        initUserProfileLoading();

        //view profile button
        viewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (getActivity(), UserDetailsActivity.class);
                intent.putExtra(IntentExtras.USER.USER_TYPE_KEY,IntentExtras.USER.USER_TYPE_SELF);
                startActivity(intent);
            }
        });

        initRecyclerView();
    }

    public void initUserProfileLoading(){
        Log.d(TAG, "initUserProfileLoading: Commenced");
        if(currentUser != null) {
            Log.d("USER_DETAILS_ACTIVITY", currentUser.getUsername());
            editUsername.setText(currentUser.getUsername());
            /*
            -- TO UPDATE PROFILE PIC IMAGE --
            Bitmap image = currentUser.getProfilepictureAndLoadIfNeeded(this.getActivity());
            if(image != null) {
                editProfilePic.setImageBitmap(image);
            }*/
        }
    }
    private void initRecyclerView(){
        Log.d("SUMMARY", recentConnectionUsers.size() + "");

        Log.d(TAG, "initRecyclerView: Commenced");
        SocialNameCard_RecyclerViewAdapter nameCard_adapter = new SocialNameCard_RecyclerViewAdapter(recentConnectionUsers, getActivity());
        SocialTabCard_RecyclerViewAdapter smallCard_adapter = new SocialTabCard_RecyclerViewAdapter(recommendedConnectionUsers,getActivity());

        recyclerView_NameCard.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        recyclerView_SmallCard.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));

        recyclerView_NameCard.setAdapter(nameCard_adapter);
        recyclerView_SmallCard.setAdapter(smallCard_adapter);

    }
}
