package javanesecoffee.com.blink.social;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import javanesecoffee.com.blink.R;
import javanesecoffee.com.blink.entities.User;

import static android.support.constraint.Constraints.TAG;

public class SocialSummaryFrag extends Fragment {
    private ArrayList<Bitmap> bCardImage = new ArrayList<>();
    private ArrayList<String> bCardUsername = new ArrayList<>();
    private ArrayList<String> bCardDesignation = new ArrayList<>();
    private ArrayList<String> bCardEmail = new ArrayList<>();
    private ArrayList<String> bCardLinkedin = new ArrayList<>();
    private ArrayList<String> bCardFacebook = new ArrayList<>();
    private ArrayList<String> bCardInstagram = new ArrayList<>();

    ArrayList<User> UserConnections = new ArrayList<>();
    User currentUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_social_summary, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void initUserProfileLoading(){
        Log.d(TAG, "initUserProfileLoading: Commenced");
        
    }

    public void initSummaryLoading(){
        Log.d(TAG, "initSummaryLoading: Commenced");
    }

    public void initRecommendationLoading(){
        Log.d(TAG, "initRecommendationLoading: Commenced");
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: Commenced");

    }
}
