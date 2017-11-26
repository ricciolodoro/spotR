package com.example.i864514.projectspotr;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;

import java.util.Date;

public class Main2Activity extends AppCompatActivity {

    Button calendarview;
    Button createWorkout;
    Button todaysWorkout;
    Button userProfile;
    Button runningMap;
    Button musicPlaylists;
    Button myProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        createWorkout = (Button)findViewById(R.id.createWorkoutButton);
        todaysWorkout = (Button)findViewById(R.id.todaysWorkoutButton);
        userProfile = (Button)findViewById(R.id.userProfileButton);
        runningMap = (Button)findViewById(R.id.runningMapButton);
        musicPlaylists = (Button)findViewById(R.id.musicPlaylistsButton);
        myProgress = (Button)findViewById(R.id.myProgressButton);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            String uid = user.getUid();
        }

        calendarview = (Button)findViewById(R.id.calendarButton);
    }

    public void onStart(){
        super.onStart();

        calendarview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarView dialog = new calendarView();
                FragmentTransaction ft =getFragmentManager().beginTransaction();
                dialog.show(ft, "DatePicker");
            }
        });

        createWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main2Activity.this, workoutPage.class);
                startActivity(i);
            }
        });

        todaysWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

                Date today = Calendar.getInstance().getTime();

                String reportDate = df.format(today);

                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("currentTime",reportDate).apply();

                Intent i = new Intent(Main2Activity.this, workoutPage.class);
                startActivity(i);
            }
        });

        userProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main2Activity.this, userProfile.class);
                startActivity(i);
            }
        });

        runningMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main2Activity.this, workoutPage.class);
                startActivity(i);
            }
        });

        musicPlaylists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main2Activity.this, workoutPage.class);
                startActivity(i);
            }
        });

        myProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main2Activity.this, workoutPage.class);
                startActivity(i);
            }
        });
    }








    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);

        Drawable previousScreenDrawable = menu.findItem(R.id.previousScreen).getIcon();
        previousScreenDrawable.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);

        Drawable homeDrawable = menu.findItem(R.id.home).getIcon();
        homeDrawable.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        return true;
    }
}
