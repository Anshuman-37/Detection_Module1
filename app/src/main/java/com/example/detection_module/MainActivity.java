
package com.example.detection_module;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.View;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    // Variable to get the index of the directory
    public static int idx = 0;

    // Variable to get the path of the directory
    public final static String source = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Camera App/";


    // OnCreate function
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ask for permission
        ActivityCompat.requestPermissions(this,new String[] { CAMERA_SERVICE, CAMERA , WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

    }

    // Implemented the storage in the camera have to try with firebase.
    // The server module will directly read from the firebase and act on the image.
    // The camera method (action for the click of the camera will be handled by this method....
    public void Camera(View view){
        idx++;
        String file = source + idx + ".jpg";
        File NF = new File(file);

        //Creating a place to store the file in the internal media storage for now
        try {
            NF.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Uri outputFileUri = Uri.fromFile(NF);

        // Launching an intent to the camera app
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

        // Starting the activity
        startActivity(cameraIntent);
    }
    // Try implementing the app and storing the data in the firebase...

}

