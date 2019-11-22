package javanesecoffee.com.blink;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class BlinkActivity extends AppCompatActivity {
    private ProgressDialog dialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        InitProgressDialog();
    }

    public void InitProgressDialog()
    {
        if(dialog == null) {
            dialog = new ProgressDialog(this);
            dialog.setMessage("Registering...");
        }
    }

    public void ShowProgressDialog()
    {
        InitProgressDialog();
        dialog.show();
    }

    public void ShowProgressDialog(String message)
    {
        InitProgressDialog();
        dialog.setMessage(message);
        dialog.show();
    }

    public void HideProgressDialog() {
        InitProgressDialog();
        if(dialog.isShowing()) {
            dialog.hide();
        }
    }
}
