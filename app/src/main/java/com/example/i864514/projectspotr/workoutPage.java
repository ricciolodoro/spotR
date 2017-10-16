package com.example.i864514.projectspotr;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class workoutPage extends AppCompatActivity {

    MenuItem addSet;
    ScrollView setsSpace;
    LinearLayout holdsWorkouts;
    LinearLayout holdsSetsAndReps;
    int requestExerciseCode = 0;
    static String extraExercise = "EXTRA_EXERCISE";
    String returnedString = "";
    View newView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_page);
        addSet = (MenuItem) findViewById(R.id.newSet);
        setsSpace = (ScrollView)findViewById(R.id.scrollViewSetsSpace);
        holdsWorkouts = (LinearLayout)findViewById(R.id.holdsWorkouts);
        holdsSetsAndReps = (LinearLayout)findViewById(R.id.holdsWorkouts);

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.newSet:
                LayoutInflater inflator = getLayoutInflater();
                newView = inflator.inflate(R.layout.add_sets_to_schedule, null);
                holdsWorkouts.addView(newView);
                ImageView addNewSet = (ImageView)newView.findViewById(R.id.addSetsAndReps);
                TextView exercise = (TextView)newView.findViewById(R.id.exerciseText);
                addNewSet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LayoutInflater inflator2 = getLayoutInflater();
                        View newView2 = inflator2.inflate(R.layout.add_reps, null);
                        holdsSetsAndReps.addView(newView2);
                    }
                });

                exercise.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent goToExercise = new Intent(workoutPage.this, exercises.class);
                        newView = v;
                        startActivityForResult(goToExercise, requestExerciseCode);
                    }
                });


                return true;
//
//            case R.id.addSetsAndReps:
//                Toast.makeText(getBaseContext(), "it works",Toast.LENGTH_LONG).show();
//                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_workout_menu, menu);

        Drawable previousScreenDrawable = menu.findItem(R.id.previousScreen).getIcon();
        previousScreenDrawable.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);

        Drawable homeDrawable = menu.findItem(R.id.home).getIcon();
        homeDrawable.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == requestExerciseCode){

            if (resultCode == RESULT_OK){
                returnedString = data.getStringExtra(extraExercise);
                ((TextView) newView.findViewById(R.id.exerciseText)).setText(returnedString);
            }
        }


    }

    public void makeGUIs(){

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedState) {
        super.onRestoreInstanceState(savedState);
    }

}
