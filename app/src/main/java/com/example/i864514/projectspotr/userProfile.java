package com.example.i864514.projectspotr;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;
import java.util.List;

import static android.R.id.list;
import static com.example.i864514.projectspotr.R.array.spinner_regiment_array;

public class userProfile extends AppCompatActivity {

    EditText firstNameInput;
    EditText lastNameInput;
    EditText birthdayInput;
    EditText usernameInput;
    EditText ageInput;
    EditText heightInput;
    Spinner liftingRegimentInput;
    EditText maxBenchInput;
    EditText maxSquatInput;
    EditText maxDeadliftInput;
    EditText fastestMileInput;
    Button submitButton;

    private int getIndex(Spinner spinner, String myString)
    {
        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                index = i;
                break;
            }
            else
            {
                index = 0;
            }
        }
        return index;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);


        FirebaseMessaging.getInstance().subscribeToTopic("spotR_Reminders");



        firstNameInput = (EditText)findViewById(R.id.editFirstName);
        lastNameInput = (EditText)findViewById(R.id.editLastName);
        birthdayInput = (EditText)findViewById(R.id.editBirthday);
        usernameInput = (EditText)findViewById(R.id.editUsername);
        ageInput = (EditText)findViewById(R.id.editAge);
        heightInput = (EditText)findViewById(R.id.editHeight);
        liftingRegimentInput = (Spinner)findViewById(R.id.editLiftingRegiment_spinner);
        maxBenchInput = (EditText)findViewById(R.id.editMbench);
        maxSquatInput = (EditText)findViewById(R.id.editMsquat);
        maxDeadliftInput = (EditText)findViewById(R.id.editMaxDeadLift);
        fastestMileInput = (EditText)findViewById(R.id.editFastestMile);
        submitButton = (Button)findViewById(R.id.Submit);

        String userID = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("userID","No String Found.");


        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Users").child(userID);

        ref.addValueEventListener(new ValueEventListener() {



            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                HashMap<String,String> dataSnapshotValue = (HashMap) dataSnapshot.getValue();

                String firstName = dataSnapshotValue.get("firstNameInputString");
                String lastName = dataSnapshotValue.get("lastNameInputString");
                String birthday = dataSnapshotValue.get("birthdayInputString");
                String username = dataSnapshotValue.get("usernameInputString");
                String height = dataSnapshotValue.get("heightInputString");
                String age = dataSnapshotValue.get("ageInputString");
                String liftingRegiment = dataSnapshotValue.get("liftingRegimentInputString");
                String fastestMile = dataSnapshotValue.get("fastestMileInputString");
                String maxBench = dataSnapshotValue.get("maxBenchInputString");
                String maxSquat = dataSnapshotValue.get("maxSquatInputString");
                String maxDeadlift = dataSnapshotValue.get("maxDeadliftInputString");

                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("liftingRegiment",liftingRegiment).apply();


                firstNameInput.setText(firstName);
                lastNameInput.setText(lastName);
                birthdayInput.setText(birthday);
                usernameInput.setText(username);
                heightInput.setText(height);
                liftingRegimentInput.setSelection(getIndex(liftingRegimentInput, liftingRegiment));
                ageInput.setText(age);
                fastestMileInput.setText(fastestMile);
                maxBenchInput.setText(maxBench);
                maxSquatInput.setText(maxSquat);
                maxDeadliftInput.setText(maxDeadlift);

                submitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String firstNameInputString = firstNameInput.getText().toString();
                        String lastNameInputString = lastNameInput.getText().toString();
                        String birthdayInputString = birthdayInput.getText().toString();
                        String usernameInputString = usernameInput.getText().toString();
                        String ageInputString = ageInput.getText().toString();
                        String heightInputString = heightInput.getText().toString();
                        String liftingRegimentInputString = liftingRegimentInput.getSelectedItem().toString();
                        String maxBenchInputString = maxBenchInput.getText().toString();
                        String maxSquatInputString = maxSquatInput.getText().toString();
                        String maxDeadliftInputString = maxDeadliftInput.getText().toString();
                        String fastestMileInputString = fastestMileInput.getText().toString();

                        final int AVERAGEREPS1 = 8;
                        final int AVERAGEREPS2 = 8;
                        final int AVERAGEREPS3 = 6;
                        int reps1 = AVERAGEREPS1;
                        int reps2 = AVERAGEREPS2;
                        int reps3 = AVERAGEREPS3;
                        int armWeight = 1;
                        int olympicWeight = 1;
                        int legWeight = 1;

                        int prweightbench = Integer.parseInt(maxBenchInputString);
                        int prweightsquat = Integer.parseInt(maxSquatInputString);
                        int prweightdeadlift = Integer.parseInt(maxDeadliftInputString);

                        float hypertrophyRepsMultiplier = (float)1.75;
                        float strengthRepsMultiplier = (float)0.80;
                        float powerRepsMultiplier = (float)1.2;
                        float hypertrophyWeightMultiplier = (float)0.7;
                        float strengthWeightMultiplier = (float)0.60;
                        float powerWeightMultiplier = (float)1.0;

                        Log.d("liftin string is:", liftingRegimentInputString);

                        if(liftingRegimentInputString.equalsIgnoreCase("Hypertrophy"))
                        {
                            FirebaseMessaging.getInstance().subscribeToTopic("Hypertrophy_Reminders");
                            FirebaseMessaging.getInstance().unsubscribeFromTopic("Power_Reminders");
                            FirebaseMessaging.getInstance().unsubscribeFromTopic("Strength_Reminders");

                            reps1 = (int)(hypertrophyRepsMultiplier * AVERAGEREPS1);
                            reps2 = (int)(hypertrophyRepsMultiplier * AVERAGEREPS2);
                            reps3 = (int)(hypertrophyRepsMultiplier * AVERAGEREPS3);

                            armWeight = (int)(hypertrophyWeightMultiplier * prweightbench);
                            legWeight = (int)(hypertrophyWeightMultiplier * prweightsquat);
                            olympicWeight = (int)(hypertrophyWeightMultiplier * prweightdeadlift);

                        }
                        else if(liftingRegimentInputString.equalsIgnoreCase("Strength"))
                        {
                            FirebaseMessaging.getInstance().subscribeToTopic("Strength_Reminders");
                            FirebaseMessaging.getInstance().unsubscribeFromTopic("Power_Reminders");
                            FirebaseMessaging.getInstance().unsubscribeFromTopic("Hypertrophy_Reminders");

                            reps1 = (int)(strengthRepsMultiplier * AVERAGEREPS1);
                            reps2 = (int)(strengthRepsMultiplier * AVERAGEREPS2);
                            reps3 = (int)(strengthRepsMultiplier * AVERAGEREPS3);

                            armWeight = (int)(strengthWeightMultiplier * prweightbench);
                            legWeight = (int)(strengthWeightMultiplier * prweightsquat);
                            olympicWeight = (int)(strengthWeightMultiplier * prweightdeadlift);
                        }
                        else if(liftingRegimentInputString.equalsIgnoreCase("Power"))
                        {
                            FirebaseMessaging.getInstance().subscribeToTopic("Power_Reminders");
                            FirebaseMessaging.getInstance().unsubscribeFromTopic("Hypertrophy_Reminders");
                            FirebaseMessaging.getInstance().unsubscribeFromTopic("Strength_Reminders");

                            reps1 = (int)(powerRepsMultiplier * AVERAGEREPS1);
                            reps2 = (int)(powerRepsMultiplier * AVERAGEREPS2);
                            reps3 = (int)(powerRepsMultiplier * AVERAGEREPS3);

                            armWeight = (int)(powerWeightMultiplier * prweightbench);
                            legWeight = (int)(powerWeightMultiplier * prweightsquat);
                            olympicWeight = (int)(powerWeightMultiplier * prweightdeadlift);
                        }

                        String text = Integer.toString(armWeight);
                        Log.d("bla", text);

                        String userID = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("userID","No String Found.");

                        Log.d("this is the userID:",userID);

//                        ref.addValueEventListener(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(DataSnapshot dataSnapshot) {
//                                HashMap<String,String> dataSnapshotValue = (HashMap) dataSnapshot.getValue();
//
//                                String firstName = dataSnapshotValue.get("firstNameInputString");
//                                String lastName = dataSnapshotValue.get("lastNameInputString");
//                                String birthday = dataSnapshotValue.get("birthdayInputString");
//                                String username = dataSnapshotValue.get("usernameInputString");
//                                String height = dataSnapshotValue.get("heightInputString");
//                                String age = dataSnapshotValue.get("ageInputString");
//                                String fastestMile = dataSnapshotValue.get("fastestMileInputString");
//                                String maxBench = dataSnapshotValue.get("maxBenchInputString");
//                                String maxSquat = dataSnapshotValue.get("maxSquatInputString");
//                                String maxDeadlift = dataSnapshotValue.get("maxDeadliftInputString");
//
//
//                                firstNameInput.setText(firstName);
//                                lastNameInput.setText(lastName);
//                                birthdayInput.setText(birthday);
//                                usernameInput.setText(username);
//                                heightInput.setText(height);
//                                ageInput.setText(age);
//                                fastestMileInput.setText(fastestMile);
//                                maxBenchInput.setText(maxBench);
//                                maxSquatInput.setText(maxSquat);
//                                maxDeadliftInput.setText(maxDeadlift);
//                            }

                        User u = new User(firstNameInputString,lastNameInputString,birthdayInputString,
                                usernameInputString,ageInputString,heightInputString,liftingRegimentInputString,maxBenchInputString,
                                maxSquatInputString,maxDeadliftInputString,fastestMileInputString, userID, reps1,reps2,reps3,armWeight,olympicWeight,legWeight);

                        u.writeNewUser(firstNameInputString, lastNameInputString, birthdayInputString,
                                usernameInputString, ageInputString, heightInputString, liftingRegimentInputString, maxBenchInputString,
                                maxSquatInputString, maxDeadliftInputString, fastestMileInputString, userID, reps1, reps2, reps3, armWeight, olympicWeight, legWeight);

                        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putInt("armWeight",armWeight).apply();
                        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putInt("legWeight",legWeight).apply();
                        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putInt("olympicWeight",olympicWeight).apply();
                        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putInt("reps1", reps1).apply();
                        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putInt("reps2", reps2).apply();
                        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putInt("reps3", reps3).apply();


//                            @Override
//                            public void onCancelled(DatabaseError databaseError) {
//                                System.out.println("The read failed: " + databaseError.getCode());
//                            }
//                        });



                        Toast.makeText(getApplicationContext(),"Details Submitted.",Toast.LENGTH_SHORT);

                        Intent i = new Intent(userProfile.this, Main2Activity.class);
                        startActivity(i);
                    }
                });


            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.back_to_home_layout, menu);

        Drawable homeDrawable = menu.findItem(R.id.goBackHome).getIcon();
        homeDrawable.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        return true;
    }





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.goBackHome:
                Intent returnHome = new Intent(userProfile.this, Main2Activity.class);
                startActivity(returnHome);

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
