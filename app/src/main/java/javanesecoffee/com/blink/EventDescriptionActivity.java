package javanesecoffee.com.blink;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

public class EventDescriptionActivity extends AppCompatActivity {
    //get the details of the event from the server and display them
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_description_activity);
        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFrag = null;
            switch (item.getItemId()){
                case R.id.navhome:
                    selectedFrag = new Home();
                    break;
                case R.id.navcam:
                    selectedFrag = new CameraFrag();
                    break;
                case R.id.navevent:
                    selectedFrag = new EventsFrag();
                    break;
            }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragContainer,selectedFrag).commit();
            return true;
        }
    };



}
