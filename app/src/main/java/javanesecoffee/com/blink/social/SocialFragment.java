package javanesecoffee.com.blink.social;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
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

public class SocialFragment extends Fragment {
    ArrayList<User> UserConnections = new ArrayList<>();
    User currentUser;

    private ArrayList<Bitmap> bCardImage = new ArrayList<>();
    private ArrayList<String> bCardUsername = new ArrayList<>();
    private ArrayList<String> bCardDesignation = new ArrayList<>();
    private ArrayList<String> bCardEmail = new ArrayList<>();
    private ArrayList<String> bCardLinkedin = new ArrayList<>();
    private ArrayList<String> bCardFacebook = new ArrayList<>();
    private ArrayList<String> bCardInstagram = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){




        return inflater.inflate(R.layout.fragment_social, container, false);
    }

    public void initCards(){
        Log.d(TAG, "initCards: preparing recent contacts");


    }

}
