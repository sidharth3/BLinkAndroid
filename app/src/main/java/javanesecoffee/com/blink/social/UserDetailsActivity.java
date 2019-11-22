package javanesecoffee.com.blink.social;

import android.os.Bundle;
//import android.os.UserManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;
import javanesecoffee.com.blink.R;
import javanesecoffee.com.blink.entities.User;
import javanesecoffee.com.blink.managers.UserManager;

public class UserDetailsActivity extends AppCompatActivity {
    User currentUser;
    UserManager currentUserManager;
    String username = "username";
    String designation = "designation";
    String company = "company";
    public final String TAG = "Logcat";
    TextView editUsername;
    TextView editBio;
    TextView editDesignation;
    TextView editCompany;
    CircleImageView editProfilePic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_details_page);
        currentUserManager = currentUserManager.getInstance();
        editProfilePic = findViewById(R.id.profile_pic);
        editUsername = findViewById(R.id.fieldUsername);
        editBio = findViewById(R.id.bio);
        editDesignation = findViewById(R.id.designation);
        editCompany = findViewById(R.id.company);

        currentUser = currentUserManager.getLoggedInUser();
        editUsername.setText(currentUser.getUsername());
        editBio.setText(currentUser.getBio());
        //editDesignation.setText(currentUser.);
        editCompany.setText(currentUser.getCompany());
        editProfilePic.setImageBitmap(currentUser.getProfilepicture());


    }
}