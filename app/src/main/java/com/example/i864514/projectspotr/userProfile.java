package com.example.i864514.projectspotr;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
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
import com.google.firebase.database.ValueEventListener;

public class userProfile extends AppCompatActivity {

    EditText firstNameInput = (EditText)findViewById(R.id.editFirstName);
    EditText lastNameInput = (EditText)findViewById(R.id.editLastName);
    EditText birthdayInput = (EditText)findViewById(R.id.editBirthday);
    EditText usernameInput = (EditText)findViewById(R.id.editUsername);
    EditText ageInput = (EditText)findViewById(R.id.editAge);
    EditText heightInput = (EditText)findViewById(R.id.editHeight);
    EditText maxBenchInput = (EditText)findViewById(R.id.editMbench);
    EditText maxSquatInput = (EditText)findViewById(R.id.editMsquat);
    EditText maxDeadliftInput = (EditText)findViewById(R.id.editMaxDeadLift);
    EditText fastestMileInput = (EditText)findViewById(R.id.editFastestMile);
    Button submitButton = (Button)findViewById(R.id.Submit);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




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


        FirebaseDatabase database = FirebaseDatabase.getInstance();


        DatabaseReference firstName = database.getReference("firstName");
        firstName.setValue(firstNameInput);
        DatabaseReference lastName = database.getReference("lastName");
        lastName.setValue(lastNameInput);
        DatabaseReference birthday = database.getReference("birthday");
        birthday.setValue(birthdayInput);
        DatabaseReference username = database.getReference("username");
        username.setValue(usernameInput);
        DatabaseReference age = database.getReference("age");
        age.setValue(ageInput);
        DatabaseReference height = database.getReference("height");
        height.setValue(heightInput);
        DatabaseReference maxBench = database.getReference("maxBench");
        maxBench.setValue(maxBenchInput);
        DatabaseReference maxSquat = database.getReference("maxSquat");
        maxSquat.setValue(maxSquatInput);
        DatabaseReference maxDeadlift = database.getReference("maxDeadlift");
        maxDeadlift.setValue(maxDeadliftInput);
        DatabaseReference fastestMile = database.getReference("fastestMile");
        fastestMile.setValue(fastestMileInput);
        DatabaseReference loginCount = database.getReference("loginCount");
        loginCount.setValue(loginCount);

        firstName.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d("EventListener says:", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("EventListener says:", "Failed to read value.", error.toException());
            }
        });

        lastName.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d("EventListener says:", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("EventListener says:", "Failed to read value.", error.toException());
            }
        });

        birthday.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d("EventListener says:", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("EventListener says:", "Failed to read value.", error.toException());
            }
        });

        username.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d("EventListener says:", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("EventListener says:", "Failed to read value.", error.toException());
            }
        });

        age.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d("EventListener says:", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("EventListener says:", "Failed to read value.", error.toException());
            }
        });

        height.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d("EventListener says:", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("EventListener says:", "Failed to read value.", error.toException());
            }
        });

        maxBench.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d("EventListener says:", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("EventListener says:", "Failed to read value.", error.toException());
            }
        });

        maxSquat.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d("EventListener says:", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("EventListener says:", "Failed to read value.", error.toException());
            }
        });

        maxDeadlift.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d("EventListener says:", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("EventListener says:", "Failed to read value.", error.toException());
            }
        });

        fastestMile.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d("EventListener says:", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("EventListener says:", "Failed to read value.", error.toException());
            }
        });

                Toast.makeText(getApplicationContext(),"Details Submitted.",Toast.LENGTH_SHORT);
                Intent i = new Intent(userProfile.this, Main2Activity.class);
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
