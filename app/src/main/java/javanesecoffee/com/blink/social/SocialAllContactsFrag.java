package javanesecoffee.com.blink.social;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

public class SocialAllContactsFrag extends Fragment {
    private ArrayList<Bitmap> bCardImage = new ArrayList<>();
    private ArrayList<String> bCardUsername = new ArrayList<>();
    private ArrayList<String> bCardDesignation = new ArrayList<>();
    private ArrayList<String> bCardEmail = new ArrayList<>();
    private ArrayList<String> bCardLinkedin = new ArrayList<>();
    private ArrayList<String> bCardFacebook = new ArrayList<>();
    private ArrayList<String> bCardInstagram = new ArrayList<>();
    private ArrayList<String> bCardCompany = new ArrayList<>();

    RecyclerView recyclerView_NameCard;

    ConnectionsManager connectionsManager;
    ArrayList<User> UserConnections = new ArrayList<>();
    ArrayList<User> UserRecommended = new ArrayList<>();
    User currentUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frangment_social_all_contacts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        currentUser = UserManager.getLoggedInUser();
        connectionsManager = ConnectionsManager.getInstance();
        UserConnections = connectionsManager.LoadAllConnections();
        UserRecommended = connectionsManager.LoadSuggestedConnections();

        recyclerView_NameCard = view.findViewById(R.id.socialNameCardRecycler_all);

        initConnectionsLoading();
    }


    public void initConnectionsLoading(){
        Log.d(TAG, "initConnectionsLoading: Commenced");

        for(User user:UserConnections){
            bCardUsername.add(user.getUsername());
            //bCardImage.add(user.getProfilepictureAndLoadIfNeeded());
            bCardDesignation.add(user.getPosition());
            bCardEmail.add(user.getEmail());
            bCardFacebook.add(user.getFacebook());
            bCardInstagram.add(user.getInstagram());
            bCardLinkedin.add(user.getLinkedin());
            bCardCompany.add(user.getCompany());
        }

        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: Commenced");
        SocialNameCard_RecyclerViewAdapter nameCard_adapter = new SocialNameCard_RecyclerViewAdapter(bCardImage,bCardUsername,bCardDesignation,bCardEmail,bCardLinkedin, bCardFacebook, bCardInstagram, bCardCompany, getActivity());
        recyclerView_NameCard.setAdapter(nameCard_adapter);


    }
}
