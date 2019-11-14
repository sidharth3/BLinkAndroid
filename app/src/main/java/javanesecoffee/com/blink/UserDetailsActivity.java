package javanesecoffee.com.blink;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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