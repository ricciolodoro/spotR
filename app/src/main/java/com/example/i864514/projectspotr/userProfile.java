package com.example.i864514.projectspotr;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.HashMap;

public class userProfile extends AppCompatActivity {

    EditText firstNameInput;
    EditText lastNameInput;
    EditText birthdayInput;
    EditText usernameInput;
    EditText ageInput;
    EditText heightInput;
    EditText maxBenchInput;
    EditText maxSquatInput;
    EditText maxDeadliftInput;
    EditText fastestMileInput;
    Button submitButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("users");

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
                String fastestMile = dataSnapshotValue.get("fastestMileInputString");
                String maxBench = dataSnapshotValue.get("maxBenchInputString");
                String maxSquat = dataSnapshotValue.get("maxSquatInputString");
                String maxDeadlift = dataSnapshotValue.get("maxDeadliftInputString");

                firstNameInput.setText(firstName);
                lastNameInput.setText(lastName);
                birthdayInput.setText(birthday);
                usernameInput.setText(username);
                heightInput.setText(height);
                ageInput.setText(age);
                fastestMileInput.setText(fastestMile);
                maxBenchInput.setText(maxBench);
                maxSquatInput.setText(maxSquat);
                maxDeadliftInput.setText(maxDeadlift);
            }




            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        firstNameInput = (EditText)findViewById(R.id.editFirstName);
        lastNameInput = (EditText)findViewById(R.id.editLastName);
        birthdayInput = (EditText)findViewById(R.id.editBirthday);
        usernameInput = (EditText)findViewById(R.id.editUsername);
        ageInput = (EditText)findViewById(R.id.editAge);
        heightInput = (EditText)findViewById(R.id.editHeight);
        maxBenchInput = (EditText)findViewById(R.id.editMbench);
        maxSquatInput = (EditText)findViewById(R.id.editMsquat);
        maxDeadliftInput = (EditText)findViewById(R.id.editMaxDeadLift);
        fastestMileInput = (EditText)findViewById(R.id.editFastestMile);
        submitButton = (Button)findViewById(R.id.Submit);





        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            String firstNameInputString = firstNameInput.getText().toString();
            String lastNameInputString = lastNameInput.getText().toString();
            String birthdayInputString = birthdayInput.getText().toString();
            String usernameInputString = usernameInput.getText().toString();
            String ageInputString = ageInput.getText().toString();
            String heightInputString = heightInput.getText().toString();
            String maxBenchInputString = maxBenchInput.getText().toString();
            String maxSquatInputString = maxSquatInput.getText().toString();
            String maxDeadliftInputString = maxDeadliftInput.getText().toString();
            String fastestMileInputString = fastestMileInput.getText().toString();



        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            String uid = user.getUid();


        }

        String userEmail = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("userEmail","No String Found.");


        User u = new User(firstNameInputString,lastNameInputString,birthdayInputString,
                usernameInputString,ageInputString,heightInputString,maxBenchInputString,
                maxSquatInputString,maxDeadliftInputString,fastestMileInputString);

        u.writeNewUser(firstNameInputString, lastNameInputString, birthdayInputString, usernameInputString,
                ageInputString, heightInputString, maxBenchInputString, maxSquatInputString, maxDeadliftInputString,
                fastestMileInputString, userEmail);

                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ref = database.getReference("users");

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
                        String fastestMile = dataSnapshotValue.get("fastestMileInputString");
                        String maxBench = dataSnapshotValue.get("maxBenchInputString");
                        String maxSquat = dataSnapshotValue.get("maxSquatInputString");
                        String maxDeadlift = dataSnapshotValue.get("maxDeadliftInputString");

                        firstNameInput.setText(firstName);
                        lastNameInput.setText(lastName);
                        birthdayInput.setText(birthday);
                        usernameInput.setText(username);
                        heightInput.setText(height);
                        ageInput.setText(age);
                        fastestMileInput.setText(fastestMile);
                        maxBenchInput.setText(maxBench);
                        maxSquatInput.setText(maxSquat);
                        maxDeadliftInput.setText(maxDeadlift);
                    }




                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        System.out.println("The read failed: " + databaseError.getCode());
                    }
                });



                Toast.makeText(getApplicationContext(),"Details Submitted.",Toast.LENGTH_SHORT);
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
