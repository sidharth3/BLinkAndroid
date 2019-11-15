package javanesecoffee.com.blink.events;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import javanesecoffee.com.blink.CameraFrag;
import javanesecoffee.com.blink.HomeFrag;
import javanesecoffee.com.blink.R;

public class EventDescriptionActivity extends AppCompatActivity {
    //get the details of the event from the server and display them

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_description_activity);
        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        navListener.onNavigationItemSelected(bottomNav.getMenu().findItem(R.id.navhome));
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFrag;
            switch (item.getItemId()){
                case R.id.navcam:
                    selectedFrag = new CameraFrag();
                    break;
                case R.id.navevent:
                    selectedFrag = new EventsFrag();
                    break;
                case R.id.navhome:
                default:
                    selectedFrag = new HomeFrag();

            }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragContainer,selectedFrag).commit();
            return true;
        }
    };



}
