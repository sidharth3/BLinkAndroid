package javanesecoffee.com.blink.social;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javanesecoffee.com.blink.R;
import javanesecoffee.com.blink.entities.User;

public class UserDetailsActivity extends AppCompatActivity {
    User currentUser = new User();
    String username = "username";
    String designation = "designation";
    String company = "company";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_details_page);
    }
}