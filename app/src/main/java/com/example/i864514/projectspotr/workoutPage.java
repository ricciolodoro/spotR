package com.example.i864514.projectspotr;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import java.util.Date;
import java.util.List;
import java.util.Map;


public class workoutPage extends AppCompatActivity {

    TextView todaysDate;
    MenuItem addSet;
    MenuItem homeItem;
    ScrollView setsSpace;
    LinearLayout holdsSetsAndReps;
    int requestExerciseCode = 0;
    static String extraExercise = "EXTRA_EXERCISE";
    static String extraVideo = "EXTRA_VIDEO";
    static String extraDate = "EXTRA_DATE";
    String returnedString = "";
    String returnedString2 = "";
    View newView;
    View newViewForVideos;
    EditText repsForExercise;
    EditText weightForExercise;
    int setCount;
    int layoutTracker;
    String exerciseChoice;
    int exerciseInt;
    static int ACTIVATE_START_CAMERA_APP = 1;
    Uri uriString = null;
    Map<View, Integer> toRecognizeView;
    Map<View, LinearLayout> toRecognizeLayout;
    ArrayList<Uri> uriHelper;
    ArrayList<Uri> layoutHelper;

    final String[] Shoulders  = {"Side Raise", "Dumbbell Rows","Dumbbell Upright Rows","Push Press","Dumbbell Shrugs","Clean and Press","Clean and Jerk","Standing Palms-In Dumbbell Press","Standing Military Press","Seated Barbell Military Press","Power Partials","Seated Dumbbell Press","Reverse Flyes","Alternating Deltoid Raise","Dumbbell Shoulder Press","Leverage Shoulder Press"};
    final String[] Chest  = {"Bench press", "Chest fly", "DUMBBELL SQUEEZE PRESS", "INCLINE DUMBBELL BENCH PRESS", "Board Press", "Floor Press", "Guillotine", "Decline Bench Press", "Decline Fly", "Lying Fly", "Seated Fly", "Standing Fly", "Cable Bar", "Chest Press", "Cable Bar", "Decline Chest Press",  "Wide Grip Chest Press", "Decline Fly", "Lying Fly"};
    final String[] Legs  = {"Single Leg Split Squat", "Squat", "Leg Extension", "Leg Presses", "Rear Lunge", "Barbell Machine", "Front Squat", "Full Squat", "Hack Squat", "V-Squat","forward lunge","side lunge", "Sissy Squat", "Smith", "Single Leg Squat", "Weighted Glute-Ham Raise", "Leg Curls", "power wheel", "Glute-Ham Raise", "Kneeling Leg Curl", "Lying Leg Curl", "Good-morning", "Straight-leg Deadlift", "Inverse Leg Curl", "Back Raise", };
    final String[] Abs  = {"Ab Crunches", "Bicycle Crunches", "Kneeling Cable Crunches","Floor Cable Crunches", "Swiss Ball Cable Crunches", "Hanging Leg Raises", "Incline Crunches", "Incline Sit Ups", "Jack Knife Maneuver", "Oblique Crunches", "Russian Twist", "Side Crunches", "Sit Ups", "Oblique Sit Ups", "Half Kneeling Rotations", "Cable Lifts", "Cable Chops", "Kneeling Rotation Cable Exercise", "Standing Side Bends for Abs", "1 Leg hip Hinge", "Bosu Ball Leg Raises", "Bosu Ball Russian Twists", "Bosu Ball Crunches Feet Up"};
    final String[] Arms  = {"Close Grip Bench Press", "JM Press", "Lying Triceps Extension", "Triceps Extension", "Bent-over Triceps Extension", "Cable Bar", "Forward Triceps Extension", "Incline Triceps Extension", "Kneeling Triceps Extension", "Pushdown", "Alternating Seated", "forward leaning", "Incline", "Kickback",  "Curl", "Alternating Curl", "Cable Bar", "Supine Curl",  "Doorway", "Preacher Curl", "Concentration Curl", "Underhand Supine Row"};
    final String[] Back  = {"Bent-over Row", "Cambered Bar Lying Row","Incline Row","Kneeling Row","Lying Row","One Arm Bent-over Row","Seated High Row","Seated Wide Grip Row","One Arm Standing Row","T-bar Row","Seated High Row"};
    final String[] BodyWeight  = {"Neck Flexion", "Triceps Dip", "Parallel Close Grip Pull-up","Step-up","Pull-up","Chin-up","Close Grip Pulldown","Rear Pull-up","Trap Bar Shrug","Gripless Shrug","Seated Shoulder External Rotation","Broomstick","Rear Pull-up", "Neck Rotation","Lateral Neck Flexion","Lying Neck Flexion","Chest Dip","Front Neck Bridge","Bench Dip", "Push-up","spinal twist","knees to chest","Underhand Chin-up","cat stretch","back extension","sitting hip stretch", "between benches","Isometric Neck Circuit","Neck Bridge Prone","Chin To Chest Stretch","Neck-SMR"};
    final String[] MinimalWeight = {"Lying Face Down Plate Neck Resistance","DUMBBELL SHRUG","BARBELL SHRUG","Lunge", "WEIGHTED PUSHUP","Car Drivers","Front Plate Raise"};

//    String Date = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("currentTime","No String Found.");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_page);
        addSet = (MenuItem) findViewById(R.id.newSet);
        homeItem = (MenuItem) findViewById(R.id.home);
        setsSpace = (ScrollView)findViewById(R.id.scrollViewSetsSpace);

        holdsSetsAndReps = (LinearLayout)findViewById(R.id.holdsWorkouts);
        todaysDate = (TextView)findViewById(R.id.date);

        repsForExercise = (EditText)findViewById(R.id.repsForExercise);
        weightForExercise = (EditText)findViewById(R.id.weightForExercise);

        toRecognizeView = new HashMap<>();
        toRecognizeLayout = new HashMap<>();
        uriHelper = new ArrayList<>();


        String Date = "";
//        Intent getDateIntent = getIntent();
//        getDateIntent.getStringExtra("");


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String email = user.getEmail();


        }

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("users");


        ref.addValueEventListener(new ValueEventListener() {
              @Override
              public void onDataChange(DataSnapshot dataSnapshot) {
                  HashMap<String, Long> dataSnapshotValue = (HashMap) dataSnapshot.getValue();

                  long reps1String = dataSnapshotValue.get("reps1");
                  int reps1 = (int)reps1String;
                  long reps2String = dataSnapshotValue.get("reps2");
                  int reps2 = (int)reps2String;
                  long reps3String = dataSnapshotValue.get("reps3");
                  int reps3 = (int)reps3String;
                  long armWeightString = dataSnapshotValue.get("armWeight");
                  int armWeight = (int)armWeightString;
                  long legWeightString = dataSnapshotValue.get("legWeight");
                  int legWeight = (int)legWeightString;


                  if(exerciseChoice == null) {
                      exerciseChoice = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("exerciseChoice", "No String Found.");
                  } else {
                      exerciseChoice = "Neck Flexion";
                  }
                  if (Arrays.asList(Shoulders,Arms,Back,Chest).contains(exerciseChoice)) {
                      exerciseInt = 1;
                  } else if(Arrays.asList(Legs).contains(exerciseChoice)) {
                      exerciseInt = 2;
                  } else if(Arrays.asList(BodyWeight,Abs).contains(exerciseChoice)) {
                      exerciseInt = 3;
                  } else if(Arrays.asList(MinimalWeight).contains(exerciseChoice)) {
                      exerciseInt = 4;
                  } else {
                      exerciseInt = 0;
                  }

                  switch(setCount) {
                      case 1:
                          repsForExercise.setHint(reps1);
                          if(exerciseInt == 1){
                              weightForExercise.setHint(armWeight);
                          } else if(exerciseInt == 2) {
                              weightForExercise.setHint(legWeight);
                          } else if(exerciseInt == 3) {
                              weightForExercise.setHint("Body Weight");
                          } else if(exerciseInt == 4) {
                              weightForExercise.setHint((int)(armWeight/5.2));
                          }
                          else if (exerciseInt == 0) {
                              weightForExercise.setHint("Choose Exercise");
                          }
                      break;
                      case 2:
                          repsForExercise.setHint(reps2);
                              if(exerciseInt == 1){
                              weightForExercise.setHint((int)(armWeight*1.05));
                          } else if(exerciseInt == 2) {
                              weightForExercise.setHint((int)(legWeight*1.05));
                          } else if(exerciseInt == 3) {
                              weightForExercise.setHint("Body Weight");
                          } else if(exerciseInt == 4) {
                              weightForExercise.setHint((int)(armWeight/5.2*1.05));
                          }
                          else if (exerciseInt == 0) {
                              weightForExercise.setHint("Choose Exercise");
                          }
                      break;
                      case 3:
                          repsForExercise.setHint(reps3);
                          if(exerciseInt == 1){
                              weightForExercise.setHint((int)(armWeight*1.10));
                          } else if(exerciseInt == 2) {
                              weightForExercise.setHint((int)(legWeight*1.10));
                          } else if(exerciseInt == 3) {
                              weightForExercise.setHint("Body Weight");
                          } else if(exerciseInt == 4) {
                              weightForExercise.setHint((int)(armWeight/5.2*1.10));
                          }
                          else if (exerciseInt == 0) {
                              weightForExercise.setHint("Choose Exercise");
                          }
                      break;
                      case 4:
                          repsForExercise.setHint(reps3);
                          if(exerciseInt == 1){
                              weightForExercise.setHint((int)(armWeight*1.10));
                          } else if(exerciseInt == 2) {
                              weightForExercise.setHint((int)(legWeight*1.10));
                          } else if(exerciseInt == 3) {
                              weightForExercise.setHint("Body Weight");
                          } else if(exerciseInt == 4) {
                              weightForExercise.setHint((int)(armWeight/5.2*1.10));
                          }
                          else if (exerciseInt == 0) {
                              weightForExercise.setHint("Choose Exercise");
                          }
                      break;
                      case 5:
                          repsForExercise.setHint(reps3);
                          if(exerciseInt == 1){
                              weightForExercise.setHint((int)(armWeight*1.10));
                          } else if(exerciseInt == 2) {
                              weightForExercise.setHint((int)(legWeight*1.10));
                          } else if(exerciseInt == 3) {
                              weightForExercise.setHint("Body Weight");
                          } else if(exerciseInt == 4) {
                              weightForExercise.setHint((int)(armWeight/5.2*1.10));
                          }
                          else if (exerciseInt == 0) {
                              weightForExercise.setHint("Choose Exercise");
                          }
                      break;
                      case 6:
                          repsForExercise.setHint(reps3);
                          if(exerciseInt == 1){
                              weightForExercise.setHint((int)(armWeight*1.10));
                          } else if(exerciseInt == 2) {
                              weightForExercise.setHint((int)(legWeight*1.10));
                          } else if(exerciseInt == 3) {
                              weightForExercise.setHint("Body Weight");
                          } else if(exerciseInt == 4) {
                              weightForExercise.setHint((int)(armWeight/5.2*1.10));
                          }
                          else if (exerciseInt == 0) {
                              weightForExercise.setHint("Choose Exercise");
                          }
                      break;

                  }


              }

              @Override
              public void onCancelled(DatabaseError FirebaseError) {

              }

          });

        Date = new SimpleDateFormat("MM-dd-yyyy").format(new Date());


        Bundle extras = getIntent().getExtras();
        String userDateName;


        if (extras != null) {
            Date = extras.getString(calendarView.extraDate);
        }

        todaysDate.setText(Date);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.newSet:
                layoutTracker++;
                LayoutInflater inflator = getLayoutInflater();
                newView = inflator.inflate(R.layout.add_sets_to_schedule, null);
                LayoutInflater inflator3 = getLayoutInflater();
                View newView3 = inflator3.inflate(R.layout.list_view, null);


                LinearLayout holdsWorkouts = (LinearLayout)findViewById(R.id.holdsWorkouts);


                holdsWorkouts.addView(newView3);

                LinearLayout forExercises = (LinearLayout) newView3.findViewById(R.id.listView);

                forExercises.addView(newView);

                ImageView addNewSet = (ImageView)newView.findViewById(R.id.addSetsAndReps);

                TextView exercise = (TextView)newView.findViewById(R.id.exerciseText);

                toRecognizeLayout.put(newView, forExercises);

                addNewSet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setCount++;
                        uriHelper.add(null);
                        LayoutInflater inflator2 = getLayoutInflater();
                        View newView2 = inflator2.inflate(R.layout.add_reps, null);


                        toRecognizeLayout.get(v.getParent().getParent()).addView(newView2);


                        ImageButton recordVideo = (ImageButton) newView2.findViewById(R.id.recordVideo);
                        ImageButton viewVideo = (ImageButton) newView2.findViewById(R.id.viewVideo);

                        newView2.setOnLongClickListener(new View.OnLongClickListener() {
                            @Override
                            public boolean onLongClick(View v) {

                                final View thisView = v;

                                AlertDialog.Builder adb = new AlertDialog.Builder(workoutPage.this);
                                // set title and message using string resources
                                // The  set methods look up the actual values of the resources
                                adb.setTitle("Are you sure?");
                                adb.setMessage("This will delete your set");
                                adb.setCancelable(true);
                                // negative button does nothing other than dismiss the dialog
                                adb.setNegativeButton("Cancel", null);
                                // positive button will carry out the deletion
                                adb.setPositiveButton("Erase", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        removeSet(thisView);
                                    }
                                });
                                AlertDialog confirmDialog = adb.create();

                                confirmDialog.show();
                                return true;


                            }
                        });

                        recordVideo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //Toast.makeText(getBaseContext(), "it works",Toast.LENGTH_LONG).show();
                                toRecognizeView.put((View) v.getParent(), setCount);

                                Intent callVideoAppIntent = new Intent();
                                callVideoAppIntent.setAction(MediaStore.ACTION_VIDEO_CAPTURE);
                                startActivityForResult(callVideoAppIntent, ACTIVATE_START_CAMERA_APP);
                            }
                        });

                        viewVideo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                try {
                                    Uri vidUri = uriHelper.get(toRecognizeView.get((View) v.getParent()));

                                    Intent resultVideoIntent = new Intent(workoutPage.this, watchVideo.class);
                                    resultVideoIntent.putExtra(workoutPage.extraVideo, vidUri);
                                    startActivity(resultVideoIntent);

                                }catch (Exception e){
                                    AlertDialog.Builder adb = new AlertDialog.Builder(workoutPage.this);
                                    adb.setTitle("Empty");
                                    adb.setMessage("You haven't recorded a video for this exercise");
                                    adb.setCancelable(true);
                                    // negative button does nothing other than dismiss the dialog
                                    adb.setNegativeButton("Cancel", null);

                                    adb.setPositiveButton("Ok", null);
                                    // create the dialog
                                    AlertDialog confirmDialog = adb.create();

                                    confirmDialog.show();
                                }

                            }
                        });

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

            case R.id.home:
                Intent returnHome = new Intent(workoutPage.this, Main2Activity.class);
                startActivity(returnHome);
            return true;

            case R.id.clearPage:

                AlertDialog.Builder adb = new AlertDialog.Builder(workoutPage.this);
                // set title and message using string resources
                // The  set methods look up the actual values of the resources
                adb.setTitle("Are you sure?");
                adb.setMessage("This will delete all the workouts");
                adb.setCancelable(true);
                // negative button does nothing other than dismiss the dialog
                adb.setNegativeButton("Cancel", null);
                // positive button will carry out the deletion
                adb.setPositiveButton("Erase", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        holdsSetsAndReps.removeAllViews();
                    }
                });
                AlertDialog confirmDialog = adb.create();

                confirmDialog.show();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_workout_menu, menu);

        Drawable previousScreenDrawable = menu.findItem(R.id.clearPage).getIcon();
        previousScreenDrawable.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);

        Drawable homeDrawable = menu.findItem(R.id.home).getIcon();
        homeDrawable.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0:
                if (requestCode == requestExerciseCode) {

                    if (resultCode == RESULT_OK) {
                        returnedString = data.getStringExtra(extraExercise);
                        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("exerciseChoice", returnedString).apply();

                        ((TextView) newView.findViewById(R.id.exerciseText)).setText(returnedString);
                    }
//
//            if(resultCode == RESULT_OK){
//                returnedString2 = data.getStringExtra(extraDate);
//                todaysDate.setText(returnedString2);
//            }
                }
                break;
            case 1:
                if (requestCode == ACTIVATE_START_CAMERA_APP && resultCode == RESULT_OK) {

                    Uri videoUri = data.getData();
                    uriHelper.add(setCount, videoUri);

                }
                break;
        }

    }


    public void removeSet(View thisView){
        ((ViewGroup) thisView.getParent()).removeView(thisView);
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
