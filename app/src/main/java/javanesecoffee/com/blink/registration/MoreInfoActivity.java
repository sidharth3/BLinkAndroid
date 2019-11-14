package javanesecoffee.com.blink.registration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import javanesecoffee.com.blink.R;

import static javanesecoffee.com.blink.R.layout.more_info;

public class MoreInfoActivity extends AppCompatActivity {
    Button next;
    Button skip;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(more_info);
        next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveForward();
            }
        });

        skip = findViewById(R.id.skip);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveForward();
            }
        });
    }
    public void moveForward(){
        Intent intent = new Intent(getApplicationContext(), CompleteRegistrationActivity.class);
        startActivity(intent);

    }

}
