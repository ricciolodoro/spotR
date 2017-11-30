package com.example.i864514.projectspotr;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
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
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static android.R.id.list;


public class workoutPage extends AppCompatActivity {

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String userID = user.getUid();

    private SlidingUpPanelLayout mLayout;
    ListView holdsSuggestedWorkouts;
    int workoutCount;

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
    String Date;
    View newView;
    View newViewForVideos;
    EditText repsForExercise;
    EditText weightForExercise;
    int setCount;
    int layoutTracker;
    String currentExercise;
    String workout;
    int exerciseInt;
    static int ACTIVATE_START_CAMERA_APP = 1;
    Uri uriString = null;
    Map<View, Integer> toRecognizeView;
    Map<View, LinearLayout> toRecognizeLayout;
    Map<View, Integer> toRecognizeReps;
    Map<View, Integer> toRecognizeSets;
    int weightToSave = 0;
    ArrayList<String> array = new ArrayList<>();


    ArrayList<Uri> uriHelper;
    ArrayList<Uri> layoutHelper;
    ArrayList<HashMap<String,String>> workoutlist = new ArrayList<>();
    private FirebaseDatabase database;
    private DatabaseReference myRef1;


    View tempView;

    final String[] Shoulders  = {"Side Raise", "Dumbbell Rows","Dumbbell Upright Rows","Push Press","Dumbbell Shrugs","Clean and Press","Clean and Jerk","Standing Palms-In Dumbbell Press","Standing Military Press","Seated Barbell Military Press","Power Partials","Seated Dumbbell Press","Reverse Flyes","Alternating Deltoid Raise","Dumbbell Shoulder Press","Leverage Shoulder Press"};
    final String[] Chest  = {"Bench press", "Chest fly", "DUMBBELL SQUEEZE PRESS", "INCLINE DUMBBELL BENCH PRESS", "Board Press", "Floor Press", "Guillotine", "Decline Bench Press", "Decline Fly", "Lying Fly", "Seated Fly", "Standing Fly", "Cable Bar", "Chest Press", "Cable Bar", "Decline Chest Press",  "Wide Grip Chest Press", "Decline Fly", "Lying Fly"};
    final String[] Legs  = {"Single Leg Split Squat", "Squat", "Leg Extension", "Leg Presses", "Rear Lunge", "Barbell Machine", "Front Squat", "Full Squat", "Hack Squat", "V-Squat","forward lunge","side lunge", "Sissy Squat", "Smith", "Single Leg Squat", "Weighted Glute-Ham Raise", "Leg Curls", "power wheel", "Glute-Ham Raise", "Kneeling Leg Curl", "Lying Leg Curl", "Good-morning", "Straight-leg Deadlift", "Inverse Leg Curl", "Back Raise", };
    final String[] Abs  = {"Ab Crunches", "Bicycle Crunches", "Kneeling Cable Crunches","Floor Cable Crunches", "Swiss Ball Cable Crunches", "Hanging Leg Raises", "Incline Crunches", "Incline Sit Ups", "Jack Knife Maneuver", "Oblique Crunches", "Russian Twist", "Side Crunches", "Sit Ups", "Oblique Sit Ups", "Half Kneeling Rotations", "Cable Lifts", "Cable Chops", "Kneeling Rotation Cable Exercise", "Standing Side Bends for Abs", "1 Leg hip Hinge", "Bosu Ball Leg Raises", "Bosu Ball Russian Twists", "Bosu Ball Crunches Feet Up"};
    final String[] Arms  = {"Close Grip Bench Press", "JM Press", "Lying Triceps Extension", "Triceps Extension", "Bent-over Triceps Extension", "Cable Bar", "Forward Triceps Extension", "Incline Triceps Extension", "Kneeling Triceps Extension", "Pushdown", "Alternating Seated", "forward leaning", "Incline", "Kickback",  "Curl", "Alternating Curl", "Cable Bar", "Supine Curl",  "Doorway", "Preacher Curl", "Concentration Curl", "Underhand Supine Row"};
    final String[] Back  = {"Bent-over Row", "Cambered Bar Lying Row","Incline Row","Kneeling Row","Lying Row","One Arm Bent-over Row","Seated High Row","Seated Wide Grip Row","One Arm Standing Row","T-bar Row","Seated High Row"};
    final String[] BodyWeight  = {"Neck Flexion", "Triceps Dip", "Parallel Close Grip Pull-up","Step-up","Pull-up","Chin-up","Close Grip Pulldown","Rear Pull-up","Trap Bar Shrug","Gripless Shrug","Seated Shoulder External Rotation","Broomstick","Rear Pull-up", "Neck Rotation","Lateral Neck Flexion","Lying Neck Flexion","Chest Dip","Front Neck Bridge","Bench Dip", "Push-up","spinal twist","knees to chest","Underhand Chin-up","cat stretch","back extension","sitting hip stretch", "between benches","Isometric Neck Circuit","Neck Bridge Prone","Chin To Chest Stretch","Neck-SMR"};
    final String[] MinimalWeight = {"Lying Face Down Plate Neck Resistance","DUMBBELL SHRUG","BARBELL SHRUG","Lunge", "WEIGHTED PUSHUP","Car Drivers","Front Plate Raise"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_page);
        addSet = (MenuItem) findViewById(R.id.newSet);
        homeItem = (MenuItem) findViewById(R.id.home);
        setsSpace = (ScrollView)findViewById(R.id.scrollViewSetsSpace);

        mLayout = (SlidingUpPanelLayout)findViewById(R.id.sliding_layout);
        holdsSuggestedWorkouts = (ListView)findViewById(R.id.list_main);

        holdsSetsAndReps = (LinearLayout)findViewById(R.id.holdsWorkouts);
        todaysDate = (TextView)findViewById(R.id.date);
        currentExercise = "No Exercise Chosen";

        repsForExercise = (EditText)findViewById(R.id.repsForExercise);
        weightForExercise = (EditText)findViewById(R.id.weightForExercise);

        toRecognizeView = new HashMap<>();
        toRecognizeLayout = new HashMap<>();
        toRecognizeSets = new HashMap<>();
        toRecognizeReps = new HashMap<>();
        uriHelper = new ArrayList<>();

        panelListener();


        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        if(mDatabase.child("Workouts").child(userID) != null) {
            myRef1 = mDatabase.child("Workouts").child(userID);

            myRef1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.child("Workouts").child(userID).child(Date).getChildrenCount() > 0) {
                        int workoutcount = dataSnapshot.child("Workouts").child(userID).child(Date).child("workoutcount").getValue(DatesByUserID.class).getWorkoutcount();
                        addWorkouts(workoutcount, dataSnapshot);
                    }
                }


                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }




//        Intent getDateIntent = getIntent();
//        getDateIntent.getStringExtra("");


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userID = user.getUid();

        Date = new SimpleDateFormat("MM-dd-yyyy").format(new Date());


        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            Date = extras.getString(calendarView.extraDate);
        }

        todaysDate.setText(Date);

        database = FirebaseDatabase.getInstance();

        if(database.getReference().child("Workouts").child(userID).child(Date) != null) {

            myRef1 = database.getReference().child("Workouts").child(userID).child(Date);

            myRef1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    getData(dataSnapshot);


                   }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }


    }

    private void addWorkouts(int workoutcount, DataSnapshot dataSnapshot) {
        for(int i = workoutcount; i>=0; i--){
            layoutTracker++;
            LayoutInflater inflator = getLayoutInflater();
            newView = inflator.inflate(R.layout.add_sets_to_schedule, null);
            LayoutInflater inflator3 = getLayoutInflater();
            View newView3 = inflator3.inflate(R.layout.list_view, null);

            LinearLayout holdsWorkouts = (LinearLayout)findViewById(R.id.holdsWorkouts);

            holdsWorkouts.addView(newView3);

            LinearLayout forExercises = (LinearLayout) newView3.findViewById(R.id.listView);

            forExercises.addView(newView);

            toRecognizeLayout.put(newView, forExercises);

            int setcount = (int)dataSnapshot.child("Workouts").child(userID).child(Date).child("workouts").child("sets").getChildrenCount();
            for(int j = setcount; j>=0; j--){
                setCount++;
                uriHelper.add(null);
                LayoutInflater inflator2 = getLayoutInflater();
                View newView2 = inflator2.inflate(R.layout.add_reps, null);
                View v = holdsSetsAndReps;
                toRecognizeLayout.get(v.getParent().getParent()).addView(newView2);

            }
        }
    }


    private void getData(DataSnapshot dataSnapshot) {
        for(DataSnapshot ds : dataSnapshot.getChildren()){
            DatesByUserID d = new DatesByUserID();
            WorkoutsByDate w = new WorkoutsByDate();
            SetsByWorkout s = new SetsByWorkout();

            if(ds.child("Workouts").child(userID).child(Date).getChildrenCount()>0) {

                d.setWorkoutcount(ds.child("Workouts").child(userID).child(Date).getValue(DatesByUserID.class).getWorkoutcount());
                if(ds.child("Workouts").child(userID).child(Date).child("workouts").getChildrenCount()>0) {
                for (int i = 0; i < d.getWorkoutcount(); i++) {
                    w.setWorkout(ds.child("Workouts").child(userID).child(Date).child("workouts").child(Integer.toString(i)).getValue(WorkoutsByDate.class).getWorkout());
                    w.setSetcount(ds.child("Workouts").child(userID).child(Date).child("workouts").child(Integer.toString(i)).getValue(WorkoutsByDate.class).getSetcount());

                    HashMap<String, String> workouts = new HashMap<>();
                    workouts.put("workout",w.getWorkout());
                    workouts.put("workoutcount",Integer.toString(w.getSetcount()));


                    if(ds.child("Workouts").child(userID).child(Date).child("workouts").child(Integer.toString(i)).child("sets").getChildrenCount()>0)
                    for (int j = 0; j < w.getSetcount(); j++) {
                        s.setWeight(ds.child("Workouts").child(userID).child(Date).child("workouts").child(Integer.toString(i)).child("sets").child(Integer.toString(j)).getValue(SetsByWorkout.class).getWeight());
                        s.setReps(ds.child("Workouts").child(userID).child(Date).child("workouts").child(Integer.toString(i)).child("sets").child(Integer.toString(j)).getValue(SetsByWorkout.class).getReps());
                        s.setViduri(ds.child("Workouts").child(userID).child(Date).child("workouts").child(Integer.toString(i)).child("sets").child(Integer.toString(j)).getValue(SetsByWorkout.class).getViduri());
                        HashMap<String, String> sets = new HashMap<>();
                        sets.put("weight",Integer.toString(s.getWeight()));
                        sets.put("reps",Integer.toString(s.getReps()));
                        sets.put("viduri",s.getViduri());

                        workouts.put(Integer.toString(j),sets.toString());

                    }
                    HashMapAdapter adapter = new HashMapAdapter(workouts);
                    holdsSuggestedWorkouts.setAdapter(adapter);
                }



                }
            }

        }
    }

    private void panelListener() {

        mLayout.setPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                Log.e("Slide Panel:", "onPanelSlide, offset " + slideOffset);
            }

            @Override
            public void onPanelExpanded(View panel) {
                Log.e("Slide Panel:", "onPanelExpanded");
            }

            @Override
            public void onPanelCollapsed(View panel) {
                Log.e("Slide Panel:", "onPanelCollapsed");
            }

            @Override
            public void onPanelAnchored(View panel) {
                Log.e("Slide Panel:", "onPanelAnchored");
            }

            @Override
            public void onPanelHidden(View panel) {
                Log.e("Slide Panel:", "onPanelHidden");
            }
        });


    }

    @Override
    public void onBackPressed() {
        if(mLayout != null && (mLayout.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED || mLayout.getPanelState() == SlidingUpPanelLayout.PanelState.ANCHORED)) {
            mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
        } else {
            super.onBackPressed();
        }
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
                ImageView deleteSets = (ImageView)newView.findViewById(R.id.deleteSetsAndReps);

                final TextView exercise = (TextView)newView.findViewById(R.id.exerciseText);

                toRecognizeLayout.put(newView, forExercises);

                final View[] v1 = {null};

                deleteSets.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        v1[0] = v;
                        AlertDialog.Builder adb = new AlertDialog.Builder(workoutPage.this);
                        // set title and message using string resources
                        // The  set methods look up the actual values of the resources
                        adb.setTitle("Are you sure?");
                        adb.setMessage("This will delete the set");
                        adb.setCancelable(true);
                        // negative button does nothing other than dismiss the dialog
                        adb.setNegativeButton("Cancel", null);
                        // positive button will carry out the deletion
                        adb.setPositiveButton("Remove", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                (toRecognizeLayout.get(v1[0].getParent().getParent())).removeAllViews();
                            }
                        });
                        AlertDialog confirmDialog = adb.create();

                        confirmDialog.show();




                    }
                });

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

                        final EditText weightForExercise = (EditText) newView2.findViewById(R.id.weightForExercise);
                        final EditText repsForExercise = (EditText) newView2.findViewById(R.id.repsForExercise);

                        if(!array.isEmpty()) {
                            weightForExercise.setHint("4");
                            repsForExercise.setHint("3");
                        }

                        weightForExercise.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                tempView = v;

                                //Toast.makeText(getBaseContext(), "click", Toast.LENGTH_SHORT).show();
                                try{
                                    toRecognizeReps.put((View)v.getParent(),Integer.parseInt(repsForExercise.getText().toString()));
                                }catch (Exception e){

                                }
                                //Toast.makeText(getBaseContext(), Integer.parseInt(weightForExercise.getText().toString()), Toast.LENGTH_SHORT).show();
                                final String reps = repsForExercise.getText().toString();
                                //Toast.makeText(getBaseContext(), "New Reps: " + reps, Toast.LENGTH_SHORT).show();



                                myRef1 = database.getReference().child("Workouts").child(userID).child(Date).child("workouts").child(Integer.toString(workoutCount));

                                myRef1.addValueEventListener(new ValueEventListener() {

                                    String weight = weightForExercise.getText().toString();

                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {

                                        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

                                        SetsByWorkout s = new SetsByWorkout();

                                        mDatabase.child("Workouts").child(userID).child(Date).child("workouts").child(Integer.toString(workoutCount)).child("sets").child(Integer.toString(setCount)).child("weight").setValue(weight);
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });
                          }

                        });

                        weightForExercise.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {

                            }

                            @Override
                            public void afterTextChanged(Editable s) {
                                //toRecognizeView.get(tempView.getParent());
                                //weightToSave = Integer.parseInt(weightForExercise.getText().toString());

                                //Toast.makeText(getBaseContext(), "edit", Toast.LENGTH_SHORT).show();


                            }
                        });

                        repsForExercise.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                tempView = v;

                                //Toast.makeText(getBaseContext(), "click", Toast.LENGTH_SHORT).show();
                                try{
                                    toRecognizeReps.put((View)v.getParent(),Integer.parseInt(repsForExercise.getText().toString()));
                                }catch (Exception e){

                                }
                                    //Toast.makeText(getBaseContext(), Integer.parseInt(weightForExercise.getText().toString()), Toast.LENGTH_SHORT).show();
                                final String reps = repsForExercise.getText().toString();
                                //Toast.makeText(getBaseContext(), "New Reps: " + reps, Toast.LENGTH_SHORT).show();

                                myRef1 = database.getReference().child("Workouts").child(userID).child(Date).child("workouts").child(Integer.toString(workoutCount));

                                myRef1.addValueEventListener(new ValueEventListener() {

                                    String reps = repsForExercise.getText().toString();

                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {

                                        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

                                        SetsByWorkout s = new SetsByWorkout();

                                        mDatabase.child("Workouts").child(userID).child(Date).child("workouts").child(Integer.toString(workoutCount)).child("sets").child(Integer.toString(setCount)).child("reps").setValue(reps);
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });



                            }
                        });

                        repsForExercise.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {

                            }

                            @Override
                            public void afterTextChanged(Editable s) {
                                //toRecognizeView.get(tempView.getParent());
                                //weightToSave = Integer.parseInt(weightForExercise.getText().toString());
                                //Toast.makeText(getBaseContext(), "edit", Toast.LENGTH_SHORT).show();
                            }
                        });

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
                                        SetsByWorkout s = new SetsByWorkout();
                                        s.deleteSet(userID,Date,workoutCount,setCount);
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
                                    Uri videoUri = uriHelper.get(toRecognizeView.get((View) v.getParent()));
//                                    String vidUri = videoUri.toString();
//
//                                    String Date = todaysDate.getText().toString();
//                                    String setsCount = Integer.toString(setCount);
//
//                                    SetsByWorkout s = new SetsByWorkout();
//                                    s.writeNewVidUri(userID, Date, workout, vidUri, setsCount);

                                    Intent resultVideoIntent = new Intent(workoutPage.this, watchVideo.class);
                                    resultVideoIntent.putExtra(workoutPage.extraVideo, videoUri);
                                    startActivity(resultVideoIntent);

                                } catch (Exception e) {
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

                        String Date = todaysDate.getText().toString();
                        String workout = exercise.getText().toString();



                        int re;
                        int w = 135;


                        int reps1 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getInt("reps1", 1);
                        int reps2 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getInt("reps2", 1);
                        int reps3 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getInt("reps3", 1);
                        int armWeight = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getInt("armWeight", 135);
                        int legWeight = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getInt("armWeight", 135);


                        if (Arrays.asList(Shoulders).contains(currentExercise)) {
                            exerciseInt = 1;
                        } else if (Arrays.asList(Arms).contains(currentExercise)) {
                            exerciseInt = 1;
                        } else if (Arrays.asList(Back).contains(currentExercise)) {
                            exerciseInt = 1;
                        } else if (Arrays.asList(Chest).contains(currentExercise)) {
                            exerciseInt = 1;
                        } else if(Arrays.asList(Legs).contains(currentExercise)) {
                            exerciseInt = 2;
                        } else if(Arrays.asList(BodyWeight).contains(currentExercise)) {
                            exerciseInt = 3;
                        } else if(Arrays.asList(Abs).contains(currentExercise)) {
                            exerciseInt = 3;
                        } else if(Arrays.asList(MinimalWeight).contains(currentExercise)) {
                            exerciseInt = 4;
                        } else {
                            exerciseInt = 0;
                        }

                        switch(setCount) {
                            case 1:
//                                repsForExercise.setHint(reps1);
                                re = reps1;
                                if(exerciseInt == 1){
//                                    weightForExercise.setHint(armWeight);
                                    w = armWeight;
                                } else if(exerciseInt == 2) {
//                                    weightForExercise.setHint(legWeight);
                                    w = legWeight;
                                } else if(exerciseInt == 3) {
//                                    weightForExercise.setHint("Body Weight");
                                    w = 0;
                                } else if(exerciseInt == 4) {
//                                    weightForExercise.setHint((int)(armWeight/5.2));
                                    w = (int)(armWeight/5.2);
                                }
                                else if (exerciseInt == 0) {
//                                    weightForExercise.setHint("Choose Exercise");
                                }
                                break;
                            case 2:
//                                repsForExercise.setHint(reps2);
                                re = reps2;
                                if(exerciseInt == 1){
//                                    weightForExercise.setHint((int)(armWeight*1.05));
                                    w = (int)(armWeight*1.05);
                                } else if(exerciseInt == 2) {
//                                    weightForExercise.setHint((int)(legWeight*1.05));
                                    w = (int)(legWeight*1.05);
                                } else if(exerciseInt == 3) {
//                                    weightForExercise.setHint("Body Weight");
                                    w = 0;
                                } else if(exerciseInt == 4) {
//                                    weightForExercise.setHint((int)(armWeight/5.2*1.05));
                                    w = (int)(armWeight/5.2*1.05);
                                }
                                else if (exerciseInt == 0) {
//                                    weightForExercise.setHint("Choose Exercise");
                                }
                                break;
                            case 3:
//                                repsForExercise.setHint(reps3);
                                re = reps3;
                                if(exerciseInt == 1){
//                                    weightForExercise.setHint((int)(armWeight*1.10));
                                    w = ((int)(armWeight*1.10));
                                } else if(exerciseInt == 2) {
//                                    weightForExercise.setHint((int)(legWeight*1.10));
                                    w = ((int)(armWeight*1.10));
                                } else if(exerciseInt == 3) {
//                                    weightForExercise.setHint("Body Weight");
                                    w = 0;
                                } else if(exerciseInt == 4) {
//                                    weightForExercise.setHint((int)(armWeight/5.2*1.10));
                                    w = (int)(armWeight/5.2*1.10);
                                }
                                else if (exerciseInt == 0) {
//                                    weightForExercise.setHint("Choose Exercise");
                                }
                                break;
                            case 4:
//                                repsForExercise.setHint(reps3);
                                re = reps3;
                                if(exerciseInt == 1){
//                                    weightForExercise.setHint((int)(armWeight*1.10));
                                    w = ((int)(armWeight*1.10));
                                } else if(exerciseInt == 2) {
//                                    weightForExercise.setHint((int)(legWeight*1.10));
                                    w = ((int)(armWeight*1.10));
                                } else if(exerciseInt == 3) {
//                                    weightForExercise.setHint("Body Weight");
                                    w = 0;
                                } else if(exerciseInt == 4) {
//                                    weightForExercise.setHint((int)(armWeight/5.2*1.10));
                                    w = (int)(armWeight/5.2*1.10);
                                }
                                else if (exerciseInt == 0) {
//                                    weightForExercise.setHint("Choose Exercise");
                                }
                                break;
                            case 5:
//                                repsForExercise.setHint(reps3);
                                re = reps3;
                                if(exerciseInt == 1){
//                                    weightForExercise.setHint((int)(armWeight*1.10));
                                    w = ((int)(armWeight*1.10));
                                } else if(exerciseInt == 2) {
//                                    weightForExercise.setHint((int)(legWeight*1.10));
                                    w = ((int)(armWeight*1.10));
                                } else if(exerciseInt == 3) {
//                                    weightForExercise.setHint("Body Weight");
                                    w = 0;
                                } else if(exerciseInt == 4) {
//                                    weightForExercise.setHint((int)(armWeight/5.2*1.10));
                                    w = (int)(armWeight/5.2*1.10);
                                }
                                else if (exerciseInt == 0) {
//                                    weightForExercise.setHint("Choose Exercise");
                                }
                                break;
                            case 6:
//                                repsForExercise.setHint(reps3);
                                re = reps3;
                                if(exerciseInt == 1){
//                                    weightForExercise.setHint((int)(armWeight*1.10));
                                    w = ((int)(armWeight*1.10));
                                } else if(exerciseInt == 2) {
//                                    weightForExercise.setHint((int)(legWeight*1.10));
                                    w = ((int)(armWeight*1.10));
                                } else if(exerciseInt == 3) {
//                                    weightForExercise.setHint("Body Weight");
                                    w = 0;
                                } else if(exerciseInt == 4) {
//                                    weightForExercise.setHint((int)(armWeight/5.2*1.10));
                                    w = (int)(armWeight/5.2*1.10);
                                }
                                else if (exerciseInt == 0) {
//                                    weightForExercise.setHint("Choose Exercise");
                                }
                                break;
                            default:
//                                repsForExercise.setHint(reps3);
                                re = reps3;
                                if(exerciseInt == 1){
//                                    weightForExercise.setHint((int)(armWeight*1.10));
                                    w = ((int)(armWeight*1.10));
                                } else if(exerciseInt == 2) {
//                                    weightForExercise.setHint((int)(legWeight*1.10));
                                    w = ((int)(armWeight*1.10));
                                } else if(exerciseInt == 3) {
//                                    weightForExercise.setHint("Body Weight");
                                    w = 0;
                                } else if(exerciseInt == 4) {
//                                    weightForExercise.setHint((int)(armWeight/5.2*1.10));
                                    w = (int)(armWeight/5.2*1.10);
                                }
                                else if (exerciseInt == 0) {
//                                    weightForExercise.setHint("Choose Exercise");
                                }
                                break;

                        }




//
                        String reps = Integer.toString(re);
                        String weight = Integer.toString(w);

                        String setsCount = Integer.toString(setCount);

                        Uri videoUri = Uri.EMPTY;
                        String vidUri = videoUri.toString();

                        SetsByWorkout s = new SetsByWorkout();
                        s.writeNewSet(Date, userID, w, re, vidUri, workoutCount, setCount);

//                        DatabaseReference reference = database.getReference("Workouts").child(userID).child(Date).child(workout).child(setsCount);
//
//                        reference.addValueEventListener(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(DataSnapshot dataSnapshot) {
//                                HashMap<String, String> dataSnapshotValue = (HashMap) dataSnapshot.getValue();
//                                String userID = dataSnapshotValue.get("userID");
//                                String Date = dataSnapshotValue.get("Date");
//                                String reps = dataSnapshotValue.get("reps");
//                                String weight = dataSnapshotValue.get("weight");
//                                String setCount = dataSnapshotValue.get("setCount");
//                                String vidUri = dataSnapshotValue.get("vidUri");
//
//                                todaysDate.setText(Date);
//////                                repsForExercise.setText("0");
//////                                weightForExercise.setText("1"); WHAT DO I SET THE TEXT TO IF NOT THESE OBJECTS?
//                                //vidUri Need to find a way to save this...
//                            }
//
//                            @Override
//                            public void onCancelled(DatabaseError databaseError) {
//
//                            }
//                        });

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
                        int count = (holdsSetsAndReps).getChildCount() - 1;
                        for(int i = count; i > 0; i--) {
                            (holdsSetsAndReps).removeViewAt(i);
                        }

                        String a = userID;
                        String b = todaysDate.getText().toString();

                        DatesByUserID d = new DatesByUserID();

                        d.deleteDate(a, b);



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
                        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("workout", returnedString).apply();

                        String workoutType = returnedString;
                        String Date = todaysDate.getText().toString();

                        currentExercise = workoutType;
                        WorkoutsByDate w = new WorkoutsByDate();
                        w.deleteWorkout(userID, Date, workoutCount);
                        w.writeNewWorkout(userID, Date, currentExercise, workoutCount, setCount); // DEBUG NOTE: MUST REMOVE CURRENT SETS HERE EACH TIME SOMEONE CLICKS THE WORKOUT NAME, SETS FOR THAT WORKOUT WILL BE DELETED.

                        setCount = 0;

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
