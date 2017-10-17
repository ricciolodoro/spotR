package com.example.i864514.projectspotr;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class exercises extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner bodyPartsSpinner;
    LinearLayout linearLayout;
    LinearLayout ex;
    LinearLayout fornewList;
    int iterator = 0;
    MenuItem search;
    //EditText editTosearch;
    String[] All;
    ArrayList<String> listItems;
    ArrayAdapter<String> adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);
        search = (MenuItem)findViewById(R.id.search);
        ex = (LinearLayout) findViewById(R.id.exercisesLinearLayout);
        fornewList = (LinearLayout)findViewById(R.id.searchListView);
        //editTosearch = (EditText)findViewById(R.id.editToSearch);

        Spinner bodyPartsSpinner = (Spinner) findViewById(R.id.bodyPartsSpinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.body_parts, R.layout.custom_text_for_spinner);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.custom_spinner_drop_down);
// Apply the adapter to the spinner
        bodyPartsSpinner.setAdapter(adapter);
        bodyPartsSpinner.setOnItemSelectedListener(this);

        All = new String[]{"Bench press", "Chest fly", "DUMBBELL SQUEEZE PRESS", "INCLINE DUMBBELL BENCH PRESS", "WEIGHTED PUSHUP", "Board Press", "Floor Press", "Guillotine",
                "Decline Bench Press", "Chest Dip", "Decline Fly", "Lying Fly", "Seated Fly", "Standing Fly", "Cable Bar", "Chest Press", "Cable Bar", "Decline Chest Press",
                "Chest Dip", "Wide Grip Chest Press", "Decline Fly", "Lying Fly", "Push-up", "Side Raise", "Dumbbell Rows","Dumbbell Upright Rows","Push Press","Dumbbell Shrugs",
                "Clean and Press","Clean and Jerk","Standing Palms-In Dumbbell Press","Standing Military Press","Seated Barbell Military Press","Power Partials",
                "Seated Dumbbell Press","Reverse Flyes","Alternating Deltoid Raise","Dumbbell Shoulder Press","Car Drivers","Front Plate Raise","Leverage Shoulder Press", "Neck Flexion",
                "Neck Rotation","Lateral Neck Flexion","Lying Neck Flexion","Front Neck Bridge","spinal twist","knees to chest","cat stretch","back extension","forward lunge","side lunge",
                "sitting hip stretch","Isometric Neck Circuit","Lying Face Down Plate Neck Resistance","Neck Bridge Prone","Chin To Chest Stretch","Neck-SMR","DUMBBELL SHRUG","BARBELL SHRUG",
                "Bent-over Row", "Cambered Bar Lying Row","Incline Row","Kneeling Row","Lying Row","One Arm Bent-over Row","Seated High Row","Seated Wide Grip Row","One Arm Standing Row",
                "T-bar Row","Seated High Row", "Parallel Close Grip Pull-up","Pull-up","Chin-up","Close Grip Pulldown","Rear Pull-up","Trap Bar Shrug","Gripless Shrug",
                "Seated Shoulder External Rotation","Broomstick","Rear Pull-up", "Triceps Dip", "Close Grip Bench Press", "JM Press", "Lying Triceps Extension", "Triceps Extension",
                "Bent-over Triceps Extension", "Cable Bar", "Forward Triceps Extension", "Incline Triceps Extension", "Kneeling Triceps Extension", "Pushdown", "Alternating Seated",
                "forward leaning", "Incline", "Kickback", "Bench Dip", "Curl", "Alternating Curl", "Cable Bar", "Supine Curl", "Underhand Chin-up", "Doorway", "Preacher Curl", "Concentration Curl",
                "Underhand Supine Row", "between benches", "Ab Crunches", "Bicycle Crunches", "Kneeling Cable Crunches","Floor Cable Crunches", "Swiss Ball Cable Crunches", "Hanging Leg Raises",
                "Incline Crunches", "Incline Sit Ups", "Jack Knife Maneuver", "Oblique Crunches", "Russian Twist", "Side Crunches", "Sit Ups", "Oblique Sit Ups", "Half Kneeling Rotations",
                "Cable Lifts", "Cable Chops", "Kneeling Rotation Cable Exercise", "Standing Side Bends for Abs", "1 Leg hip Hinge", "Bosu Ball Leg Raises", "Bosu Ball Russian Twists",
                "Bosu Ball Crunches Feet Up", "Lunge", "Single Leg Split Squat", "Squat","Step-up", "Leg Extension", "Leg Presses", "Rear Lunge", "Barbell Machine", "Front Squat",
                "Full Squat", "Hack Squat", "V-Squat", "Sissy Squat", "Smith", "Single Leg Squat", "Weighted Glute-Ham Raise", "Leg Curls", "power wheel", "Glute-Ham Raise",
                "Kneeling Leg Curl", "Lying Leg Curl", "Good-morning", "Straight-leg Deadlift", "Inverse Leg Curl", "Back Raise", "Bench press", "Chest fly", "DUMBBELL SQUEEZE PRESS",
                "INCLINE DUMBBELL BENCH PRESS", "WEIGHTED PUSHUP", "Board Press", "Floor Press", "Guillotine", "Decline Bench Press", "Chest Dip", "Decline Fly", "Lying Fly", "Seated Fly",
                "Standing Fly", "Cable Bar", "Chest Press", "Cable Bar", "Decline Chest Press", "Chest Dip", "Wide Grip Chest Press", "Decline Fly", "Lying Fly", "Push-up"};





    }
    public void onClick(View v) {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.exercises_search_and_home, menu);

        Drawable previousScreenDrawable = menu.findItem(R.id.search).getIcon();
        previousScreenDrawable.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);

        Drawable homeDrawable = menu.findItem(R.id.backHome).getIcon();
        homeDrawable.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                linearLayout = (LinearLayout)findViewById(R.id.exercisesLinearLayout);
                linearLayout.removeAllViews();


                linearLayout.setOrientation(LinearLayout.VERTICAL);
                for( int i = 0; i < All.length; i++ )
                {
                    final TextView textView = new TextView(this);
                    textView.setText(All[i]);
                    textView.setTextColor(Color.WHITE);
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,32);
                    textView.setGravity(Gravity.CENTER);
                    textView.setPadding(0, 50, 0, 50);
                    textView.setClickable(true);
                    iterator = i;
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TextView sendBack = (TextView)v;

                            Intent resultIntent = new Intent();
                            resultIntent.putExtra(workoutPage.extraExercise, sendBack.getText());
                            setResult(RESULT_OK, resultIntent);
                            finish();
                        }
                    });
                    linearLayout.addView(textView);
                }
                break;
            case 1:
                linearLayout = (LinearLayout)findViewById(R.id.exercisesLinearLayout);
                linearLayout.removeAllViews();
                final String[] Chest  = {"Bench press", "Chest fly", "DUMBBELL SQUEEZE PRESS", "INCLINE DUMBBELL BENCH PRESS", "WEIGHTED PUSHUP", "Board Press", "Floor Press", "Guillotine", "Decline Bench Press", "Chest Dip", "Decline Fly", "Lying Fly", "Seated Fly", "Standing Fly", "Cable Bar", "Chest Press", "Cable Bar", "Decline Chest Press", "Chest Dip", "Wide Grip Chest Press", "Decline Fly", "Lying Fly", "Push-up"};
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                for( int i = 0; i < Chest .length; i++ )
                {
                    TextView textView = new TextView(this);
                    textView.setText(Chest [i]);
                    textView.setTextColor(Color.WHITE);
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,32);
                    textView.setGravity(Gravity.CENTER);
                    textView.setPadding(0, 50, 0, 50);
                    textView.setClickable(true);
                    iterator = i;
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            sendBack(v);
                        }
                    });
                    linearLayout.addView(textView);
                }
                break;
            case 2:
                linearLayout = (LinearLayout)findViewById(R.id.exercisesLinearLayout);
                linearLayout.removeAllViews();
                final String[] Legs  = {"Lunge", "Single Leg Split Squat", "Squat","Step-up", "Leg Extension", "Leg Presses", "Rear Lunge", "Barbell Machine", "Front Squat", "Full Squat", "Hack Squat", "V-Squat", "Sissy Squat", "Smith", "Single Leg Squat", "Weighted Glute-Ham Raise", "Leg Curls", "power wheel", "Glute-Ham Raise", "Kneeling Leg Curl", "Lying Leg Curl", "Good-morning", "Straight-leg Deadlift", "Inverse Leg Curl", "Back Raise", };
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                for( int i = 0; i < Legs .length; i++ )
                {
                    TextView textView = new TextView(this);
                    textView.setText(Legs [i]);
                    textView.setTextColor(Color.WHITE);
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,32);
                    textView.setGravity(Gravity.CENTER);
                    textView.setPadding(0, 50, 0, 50);
                    textView.setClickable(true);
                    iterator = i;
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            sendBack(v);
                        }
                    });
                    linearLayout.addView(textView);
                }
                break;
            case 3:
                linearLayout = (LinearLayout)findViewById(R.id.exercisesLinearLayout);
                linearLayout.removeAllViews();
                final String[] Abs  = {"Ab Crunches", "Bicycle Crunches", "Kneeling Cable Crunches","Floor Cable Crunches", "Swiss Ball Cable Crunches", "Hanging Leg Raises", "Incline Crunches", "Incline Sit Ups", "Jack Knife Maneuver", "Oblique Crunches", "Russian Twist", "Side Crunches", "Sit Ups", "Oblique Sit Ups", "Half Kneeling Rotations", "Cable Lifts", "Cable Chops", "Kneeling Rotation Cable Exercise", "Standing Side Bends for Abs", "1 Leg hip Hinge", "Bosu Ball Leg Raises", "Bosu Ball Russian Twists", "Bosu Ball Crunches Feet Up"};

                linearLayout.setOrientation(LinearLayout.VERTICAL);
                for( int i = 0; i < Abs .length; i++ )
                {
                    TextView textView = new TextView(this);
                    textView.setText(Abs [i]);
                    textView.setTextColor(Color.WHITE);
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,32);
                    textView.setGravity(Gravity.CENTER);
                    textView.setPadding(0, 50, 0, 50);
                    textView.setClickable(true);
                    iterator = i;
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            sendBack(v);
                        }
                    });
                    linearLayout.addView(textView);
                }
                break;
            case 4:
                linearLayout = (LinearLayout)findViewById(R.id.exercisesLinearLayout);
                linearLayout.removeAllViews();
                final String[] Arms  = {"Triceps Dip", "Close Grip Bench Press", "JM Press", "Lying Triceps Extension", "Triceps Extension", "Bent-over Triceps Extension", "Cable Bar", "Forward Triceps Extension", "Incline Triceps Extension", "Kneeling Triceps Extension", "Pushdown", "Alternating Seated", "forward leaning", "Incline", "Kickback", "Bench Dip", "Curl", "Alternating Curl", "Cable Bar", "Supine Curl", "Underhand Chin-up", "Doorway", "Preacher Curl", "Concentration Curl", "Underhand Supine Row", "between benches"};

                linearLayout.setOrientation(LinearLayout.VERTICAL);
                for( int i = 0; i < Arms .length; i++ )
                {
                    TextView textView = new TextView(this);
                    textView.setText(Arms [i]);
                    textView.setTextColor(Color.WHITE);
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,32);
                    textView.setGravity(Gravity.CENTER);
                    textView.setPadding(0, 50, 0, 50);
                    textView.setClickable(true);
                    iterator = i;
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            sendBack(v);
                        }
                    });
                    linearLayout.addView(textView);
                }
                break;
            case 5:
                linearLayout = (LinearLayout)findViewById(R.id.exercisesLinearLayout);
                linearLayout.removeAllViews();
                final String[] Back  = {"Bent-over Row", "Cambered Bar Lying Row","Incline Row","Kneeling Row","Lying Row","One Arm Bent-over Row","Seated High Row","Seated Wide Grip Row","One Arm Standing Row","T-bar Row","Seated High Row", "Parallel Close Grip Pull-up","Pull-up","Chin-up","Close Grip Pulldown","Rear Pull-up","Trap Bar Shrug","Gripless Shrug","Seated Shoulder External Rotation","Broomstick","Rear Pull-up"};

                linearLayout.setOrientation(LinearLayout.VERTICAL);
                for( int i = 0; i < Back .length; i++ )
                {
                    TextView textView = new TextView(this);
                    textView.setText(Back [i]);
                    textView.setTextColor(Color.WHITE);
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,32);
                    textView.setGravity(Gravity.CENTER);
                    textView.setPadding(0, 50, 0, 50);
                    textView.setClickable(true);
                    iterator = i;
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            sendBack(v);
                        }
                    });
                    linearLayout.addView(textView);
                }
                break;
            case 6:
                linearLayout = (LinearLayout)findViewById(R.id.exercisesLinearLayout);
                linearLayout.removeAllViews();
                final String[] Neck  = {"Neck Flexion", "Neck Rotation","Lateral Neck Flexion","Lying Neck Flexion","Front Neck Bridge","spinal twist","knees to chest","cat stretch","back extension","forward lunge","side lunge","sitting hip stretch","Isometric Neck Circuit","Lying Face Down Plate Neck Resistance","Neck Bridge Prone","Chin To Chest Stretch","Neck-SMR","DUMBBELL SHRUG","BARBELL SHRUG"};

                linearLayout.setOrientation(LinearLayout.VERTICAL);
                for( int i = 0; i < Neck.length; i++ )
                {
                    TextView textView = new TextView(this);
                    textView.setText(Neck [i]);
                    textView.setTextColor(Color.WHITE);
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,32);
                    textView.setGravity(Gravity.CENTER);
                    textView.setPadding(0, 50, 0, 50);
                    textView.setClickable(true);
                    iterator = i;
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            sendBack(v);
                        }
                    });
                    linearLayout.addView(textView);
                }
                break;
            case 7:
                linearLayout = (LinearLayout)findViewById(R.id.exercisesLinearLayout);
                linearLayout.removeAllViews();
                final String[] Shoulders  = {"Side Raise", "Dumbbell Rows","Dumbbell Upright Rows","Push Press","Dumbbell Shrugs","Clean and Press","Clean and Jerk","Standing Palms-In Dumbbell Press","Standing Military Press","Seated Barbell Military Press","Power Partials","Seated Dumbbell Press","Reverse Flyes","Alternating Deltoid Raise","Dumbbell Shoulder Press","Car Drivers","Front Plate Raise","Leverage Shoulder Press"};

                linearLayout.setOrientation(LinearLayout.VERTICAL);
                for( int i = 0; i < Shoulders .length; i++ )
                {
                    TextView textView = new TextView(this);
                    textView.setText(Shoulders [i]);
                    textView.setTextColor(Color.WHITE);
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,32);
                    textView.setGravity(Gravity.CENTER);
                    textView.setPadding(0, 50, 0, 50);
                    textView.setClickable(true);
                    iterator = i;
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            sendBack(v);
                        }
                    });
                    linearLayout.addView(textView);
                }
                break;
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void sendBack(View v){
        TextView sendBack = (TextView)v;

        Intent resultIntent = new Intent();
        resultIntent.putExtra(workoutPage.extraExercise, sendBack.getText());
        setResult(RESULT_OK, resultIntent);
        finish();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.search:

                linearLayout.removeAllViews();


                LayoutInflater inflator = getLayoutInflater();
                LayoutInflater inflator2 = getLayoutInflater();

                View newView = inflator.inflate(R.layout.edit_to_search, null);
                View newView2 = inflator2.inflate(R.layout.list_view, null);
                View newView3 = inflator2.inflate(R.layout.search_list_view, null);

                EditText editTosearch = (EditText)newView.findViewById(R.id.editToSearch);
                listView = (ListView)newView2.findViewById(R.id.listView);
                fornewList = (LinearLayout)newView3.findViewById(R.id.searchListView);
                linearLayout.addView(newView);
                ex.addView(newView2);
                ex.addView(fornewList);
                //fornewList.addView(newView3);
                //linearLayout.addView(newView2);

                editTosearch.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        initList();
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if(s.toString().equals("")){
                            //reset listview
                            initList();


                        }else {
                            //perform search
                            searchItem(s.toString());
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });



                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void searchItem(String textToSearch){
        for(String item : All){
            if(!item.toLowerCase().contains(textToSearch.toLowerCase())){
                listItems.remove(item);
            }

        }

        fornewList.removeAllViews();
        LinearLayout innerlinearLayout = (LinearLayout)findViewById(R.id.exercisesLinearLayout);


        fornewList.setOrientation(LinearLayout.VERTICAL);
        for( int i = 0; i < listItems.size(); i++ )
        {
            TextView textView = new TextView(this);
            textView.setText(listItems.get(i));
            textView.setTextColor(Color.WHITE);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,32);
            textView.setGravity(Gravity.CENTER);
            textView.setPadding(0, 50, 0, 50);
            textView.setClickable(true);
            iterator = i;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sendBack(v);
                }
            });
            fornewList.addView(textView);
    }
}

    public void initList(){
        listItems = new ArrayList<>(Arrays.asList(All));

        adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.txtitem, listItems);
        Toast.makeText(getBaseContext(), listItems.get(1), Toast.LENGTH_SHORT).show();
        listView.setAdapter(adapter);
    }

}
