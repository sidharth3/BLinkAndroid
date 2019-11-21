package javanesecoffee.com.blink.registration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import org.json.JSONObject;

import javanesecoffee.com.blink.R;
import javanesecoffee.com.blink.api.BLinkApiException;
import javanesecoffee.com.blink.api.BLinkEventObserver;
import javanesecoffee.com.blink.constants.ApiCodes;
import javanesecoffee.com.blink.helpers.ResponseParser;
import javanesecoffee.com.blink.managers.UserManager;


import static javanesecoffee.com.blink.R.layout.more_info;

public class MoreInfoActivity extends AppCompatActivity implements BLinkEventObserver {
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

        UserManager.getInstance().registerObserver(this);

    }
    public void moveForward(){
        Intent intent = new Intent(getApplicationContext(), CompleteRegistrationActivity.class);
        startActivity(intent);

    }

    @Override
    public void onBLinkEventTriggered(JSONObject response, String taskId) throws BLinkApiException {
        if(taskId == ApiCodes.TASK_MORE_INFO){
            boolean success = ResponseParser.ResponseIsSuccess(response);

            if(success){
                moveForward();
            }
            else{
                throw ResponseParser.ExceptionFromResponse(response);
            }
        }
    }

    @Override
    public void onBLinkEventException(BLinkApiException exception, String taskId){
        new AlertDialog.Builder(MoreInfoActivity.this).setTitle(exception.statusText).setMessage(exception.message).setPositiveButton("Ok", null).show();
    }


    @Override
    protected void onDestroy() {
        UserManager.getInstance().deregisterObserver(this);
        super.onDestroy();
    }
}
