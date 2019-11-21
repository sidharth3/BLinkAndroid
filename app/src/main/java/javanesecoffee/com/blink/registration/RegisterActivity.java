package javanesecoffee.com.blink.registration;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONObject;

import javanesecoffee.com.blink.R;
import javanesecoffee.com.blink.api.BLinkApiException;
import javanesecoffee.com.blink.api.BLinkEventObserver;
import javanesecoffee.com.blink.constants.ApiCodes;
import javanesecoffee.com.blink.constants.BuildModes;
import javanesecoffee.com.blink.constants.Config;
import javanesecoffee.com.blink.events.EventDescriptionActivity;
import javanesecoffee.com.blink.helpers.ResponseParser;
import javanesecoffee.com.blink.registration.FaceScanActivity;

public class RegisterActivity extends AppCompatActivity implements BLinkEventObserver {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        button = findViewById(R.id.REGISTER_BUTTON);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Config.buildMode == BuildModes.BYPASS_ONBOARDING) {
                    Intent intent = new Intent(getApplicationContext(), EventDescriptionActivity.class);
                    startActivity(intent);
                }
                else {
                    //TODO: Call UserManager Register
                    NextActivity();
                }
            }
        });
    }

    public void NextActivity()
    {
        Intent intent = new Intent(getApplicationContext(), FaceScanActivity.class);
        startActivity(intent);        
    }

    @Override //the exceptions thrown will be caught in the caller, AsyncResponseHandler. It will call onBlinkEventException
    public void onBLinkEventTriggered(JSONObject response, String taskId) throws BLinkApiException{
        if(taskId == ApiCodes.TASK_REGISTER)
        {
            boolean success = ResponseParser.ResponseIsSuccess(response);

            if(success)
            {
                NextActivity();
            }
            else
            {
                throw ResponseParser.ExceptionFromResponse(response);
            }
        }
    }

    @Override
    public void onBLinkEventException(BLinkApiException exception, String taskId) {
        if(Config.buildMode == BuildModes.PRODUCTION) {
            new AlertDialog.Builder(RegisterActivity.this).setTitle(exception.statusText).setMessage(exception.message).setPositiveButton("Ok", null).show();
        }
        else {
            NextActivity();
        }
    }
}
