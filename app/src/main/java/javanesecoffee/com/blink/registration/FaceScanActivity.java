package javanesecoffee.com.blink.registration;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javanesecoffee.com.blink.R;

public class FaceScanActivity extends AppCompatActivity {
    private static final int pic_id = 123;
    private String pictureFilePath;
    static final int REQUEST_PIC_CAPTURE = 1;
    Button cameraButton;
    ImageView click_image_id;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.face_scan_activity);


        cameraButton = (Button)findViewById(R.id.camera_button);
        //click_image_id = (ImageView)findViewById(R.id.click_image);
//        confirmButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MoveNext();
//            }
//        });



        cameraButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                    Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    camera_intent.putExtra(MediaStore.EXTRA_FINISH_ON_COMPLETION, true);
                    if(camera_intent.resolveActivity(getPackageManager())!=null){
                        startActivityForResult(camera_intent,REQUEST_PIC_CAPTURE);
                        File pictureFile;

                        try{
                            pictureFile = getPictureFile();
                        }
                        catch(IOException ex){
                            Log.d("ImageError", "Retake Photo");
                            return;
                        }
                        if(pictureFile!=null){
                            Uri photoUri = FileProvider.getUriForFile(FaceScanActivity.this, "com.javanesecoffee.blink", pictureFile);
                            camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                            startActivityForResult(camera_intent, REQUEST_PIC_CAPTURE);
                        }
                    }

//                Intent camera_intent
//                        = new Intent(MediaStore
//                        .ACTION_IMAGE_CAPTURE);
//                try
//                {
//                    if(camera_intent.resolveActivity(getPackageManager())!=null) {
//                        startActivityForResult(camera_intent, pic_id);
//                    }
//                }
//                catch (Exception e)
//                {
//                    System.out.println(e);
//                }
            }
        });
    }
    public void MoveNext(){
        Intent intent = new Intent(getApplicationContext(), MoreInfoActivity.class);
        startActivity(intent);
    }
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {
        MoveNext();
//        if (requestCode == pic_id) {
//            Bitmap photo = (Bitmap)data.getExtras()
//                    .get("data");
//            click_image_id.setImageBitmap(photo);
//        }
    }
//    public void onActivityResult(int requestcode, int resultcode, Intent data){
//        super.onActivityResult(requestcode, resultcode, data);
//        Bitmap bitmap = (Bitmap)data.getExtras().get("data");
//        imageview.setImageBitmap(bitmap);
//    }
    private File getPictureFile() throws IOException{
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String pictureFile = "UserRegister_" + timeStamp;
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(pictureFile, ".jpg", storageDir);
        pictureFilePath = image.getAbsolutePath();
        return image;

    }
}
