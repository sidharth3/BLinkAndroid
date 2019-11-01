package javanesecoffee.com.blink;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class UserDetailsActivity extends AppCompatActivity {
    User currentUser = new User();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_details_page);
    }
}