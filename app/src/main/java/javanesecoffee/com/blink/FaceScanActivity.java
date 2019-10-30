package javanesecoffee.com.blink;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.BitSet;

public class FaceScanActivity extends AppCompatActivity {
    private static final int pic_id = 123;

    Button camera_open_id;
    ImageView click_image_id;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.face_scan_activity);


        camera_open_id = (Button)findViewById(R.id.camera_button);
        click_image_id = (ImageView)findViewById(R.id.click_image);


        camera_open_id.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {


                Intent camera_intent
                        = new Intent(MediaStore
                        .ACTION_IMAGE_CAPTURE);

                try
                {
                    if(camera_intent.resolveActivity(getPackageManager())!=null) {
                        startActivityForResult(camera_intent, pic_id);
                    }
                }
                catch (Exception e)
                {
                    System.out.println(e);
                }

            }
        });
    }

    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data)
    {

        if (requestCode == pic_id) {


            Bitmap photo = (Bitmap)data.getExtras()
                    .get("data");

            click_image_id.setImageBitmap(photo);
        }
    }
//    public void onActivityResult(int requestcode, int resultcode, Intent data){
//        super.onActivityResult(requestcode, resultcode, data);
//        Bitmap bitmap = (Bitmap)data.getExtras().get("data");
//        imageview.setImageBitmap(bitmap);
//    }
}
