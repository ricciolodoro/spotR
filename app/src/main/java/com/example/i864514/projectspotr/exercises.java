package com.example.i864514.projectspotr;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class exercises extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner bodyPartsSpinner;
    LinearLayout linearLayout;
    int iterator = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

        Spinner bodyPartsSpinner = (Spinner) findViewById(R.id.bodyPartsSpinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.body_parts, R.layout.custom_text_for_spinner);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.custom_spinner_drop_down);
// Apply the adapter to the spinner
        bodyPartsSpinner.setAdapter(adapter);
        bodyPartsSpinner.setOnItemSelectedListener(this);
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
                final String[] All = {"one", "one", "one", "one", "one", "one", "one", "one", "one", "one", "one", "one", "one", "one", "one", "one", "one", "one", "one", "one", "one", "one", "one"};
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                for( int i = 0; i < All.length; i++ )
                {
                    TextView textView = new TextView(this);
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
                            Intent resultIntent = new Intent();
                            resultIntent.putExtra(workoutPage.extraExercise, All[iterator]);
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
                final String[] Chest  = {"two", "two", "two", "two", "two","two","two", "two", "two", "two", "two", "two", "two", "two", "two", "two", "two", "two", "two", "two", "two"};
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
                            Intent resultIntent = new Intent();
                            resultIntent.putExtra(workoutPage.extraExercise, Chest[iterator]);
                            setResult(RESULT_OK, resultIntent);
                            finish();
                        }
                    });
                    linearLayout.addView(textView);
                }
                break;
            case 2:
                linearLayout = (LinearLayout)findViewById(R.id.exercisesLinearLayout);
                linearLayout.removeAllViews();
                final String[] Legs  = {"three", "three", "three","three", "three", "three", "three", "three", "three", "three", "three", "three", "three", "three", "three", "three", "three", "three", "three", "three", "three", "three", "three", "three", "three", };
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
                            Intent resultIntent = new Intent();
                            resultIntent.putExtra(workoutPage.extraExercise, Legs[iterator]);
                            setResult(RESULT_OK, resultIntent);
                            finish();
                        }
                    });
                    linearLayout.addView(textView);
                }
                break;
            case 3:
                linearLayout = (LinearLayout)findViewById(R.id.exercisesLinearLayout);
                linearLayout.removeAllViews();
                final String[] Abs  = {"four", "four", "four","four", "four", "four", "four", "four", "four", "four", "four", "four", "four", "four", "four", "four", "four", "four", "four", "four", "four", "four", "four"};

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
                            Intent resultIntent = new Intent();
                            resultIntent.putExtra(workoutPage.extraExercise, Abs[iterator]);
                            setResult(RESULT_OK, resultIntent);
                            finish();
                        }
                    });
                    linearLayout.addView(textView);
                }
                break;
            case 4:
                linearLayout = (LinearLayout)findViewById(R.id.exercisesLinearLayout);
                linearLayout.removeAllViews();
                final String[] Arms  = {"five", "five", "five", "five", "five", "five", "five", "five", "five", "five", "five", "five", "five", "five", "five", "five", "five", "five", "five", "five", "five", "five", "five", "five", "five", "five"};

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
                            Intent resultIntent = new Intent();
                            resultIntent.putExtra(workoutPage.extraExercise, Arms[iterator]);
                            setResult(RESULT_OK, resultIntent);
                            finish();
                        }
                    });
                    linearLayout.addView(textView);
                }
                break;
            case 5:
                linearLayout = (LinearLayout)findViewById(R.id.exercisesLinearLayout);
                linearLayout.removeAllViews();
                final String[] Back  = {"six", "six","six","six","six","six","six","six","six","six","six", "six","six","six","six","six","six","six","six","six","six"};

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
                            Intent resultIntent = new Intent();
                            resultIntent.putExtra(workoutPage.extraExercise, Back[iterator]);
                            setResult(RESULT_OK, resultIntent);
                            finish();
                        }
                    });
                    linearLayout.addView(textView);
                }
                break;
            case 6:
                linearLayout = (LinearLayout)findViewById(R.id.exercisesLinearLayout);
                linearLayout.removeAllViews();
                final String[] Neck  = {"seven", "seven","seven","seven","seven","seven","seven","seven","seven","seven","seven","seven","seven","seven","seven","seven","seven","seven","seven"};

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
                            Intent resultIntent = new Intent();
                            resultIntent.putExtra(workoutPage.extraExercise, Neck[iterator]);
                            setResult(RESULT_OK, resultIntent);
                            finish();
                        }
                    });
                    linearLayout.addView(textView);
                }
                break;
            case 7:
                linearLayout = (LinearLayout)findViewById(R.id.exercisesLinearLayout);
                linearLayout.removeAllViews();
                final String[] Shoulders  = {"eight", "eight","eight","eight","eight","eight","eight","eight","eight","eight","eight","eight","eight","eight","eight","eight","eight","eight"};

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
                            Intent resultIntent = new Intent();
                            resultIntent.putExtra(workoutPage.extraExercise, Shoulders[iterator]);
                            setResult(RESULT_OK, resultIntent);
                            finish();
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
}
