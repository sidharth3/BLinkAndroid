package javanesecoffee.com.blink;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import javanesecoffee.com.blink.registration.FaceScanActivity;

public class CameraFragment extends Fragment {
    private File selfie;
    static final int REQUEST_PIC_CAPTURE = 1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_camera, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        Button selfieButton = getView().findViewById(R.id.selfieButton);
        selfieButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                try {
                    //create file to store image in
                    String pictureFileName = "Selfie" + Calendar.getInstance().getTimeInMillis();
                    File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                    selfie = File.createTempFile(pictureFileName, ".jpg", storageDir);

                    //if there is an activity to push camera from
                    if (cameraIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                        //retrieve uri
                        String provider = getActivity().getPackageName();
                        Uri photoUri = FileProvider.getUriForFile(getActivity(), provider, selfie);
                        //put uri as target file for picture
                        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                        //push camera
                        startActivityForResult(cameraIntent, REQUEST_PIC_CAPTURE);
                    }
                } catch (IOException e) {
                    selfie.delete();

                    Log.d("ImageError", e.toString());
                    Toast.makeText(getActivity().getApplicationContext(), "Please try again as the image could not be saved", Toast.LENGTH_LONG).show();
                    return;
                }

            }

        });

    }
}
