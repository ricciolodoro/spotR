package com.example.i864514.projectspotr;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;


public class watchVideo extends AppCompatActivity {

    VideoView watchVideo;
    ImageView playButton;
    ImageView deleteButton;
    Uri vidUri;
    int MY_PERMISSIONS_REQUEST_WRITE_CONTACTS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_video);
        watchVideo = (VideoView)findViewById(R.id.watchVideo);
        playButton = (ImageView)findViewById(R.id.playButton);
        deleteButton = (ImageView)findViewById(R.id.deleteButton);

        vidUri = (Uri) getIntent().getExtras().get(workoutPage.extraVideo);
        watchVideo.setVideoURI(vidUri);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                watchVideo.start();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(watchVideo.this, WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {

                        ActivityCompat.requestPermissions(watchVideo.this, new String[]{WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_WRITE_CONTACTS);

                }else{
                    getBaseContext().getContentResolver().delete(vidUri, null, null);
                }

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    getBaseContext().getContentResolver().delete(vidUri, null, null);

                } else {

                    Toast.makeText(getBaseContext(), "PERMISSION DENIED",Toast.LENGTH_LONG).show();
                }
                return;
            }

        }
    }

}
