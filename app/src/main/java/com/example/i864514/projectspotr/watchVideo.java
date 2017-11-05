package com.example.i864514.projectspotr;

import android.content.Intent;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import static com.example.i864514.projectspotr.workoutPage.ACTIVATE_START_CAMERA_APP;
import static com.example.i864514.projectspotr.workoutPage.extraExercise;

public class watchVideo extends AppCompatActivity {

    VideoView watchVideo;
    ImageView playButton;
    ImageView deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_video);

        watchVideo = (VideoView)findViewById(R.id.watchVideo);
        playButton = (ImageView)findViewById(R.id.playButton);
        deleteButton = (ImageView)findViewById(R.id.deleteButton);

        Uri vidUri = (Uri) getIntent().getExtras().get(workoutPage.extraVideo);
        watchVideo.setVideoURI(vidUri);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                watchVideo.start();
            }
        });
    }

}
