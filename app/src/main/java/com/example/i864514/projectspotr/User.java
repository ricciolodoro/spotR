package com.example.i864514.projectspotr;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Michael on 10/17/2017.
 */

public class User {

    private DatabaseReference mDatabase;

    public String firstNameInputString;
    public String lastNameInputString;
    public String birthdayInputString;
    public String usernameInputString;
    public String ageInputString;
    public String heightInputString;
    public String maxBenchInputString;
    public String maxSquatInputString;
    public String maxDeadliftInputString;
    public String fastestMileInputString;


    public User() {

    }

    public User(String firstNameInputString,String lastNameInputString,
                String birthdayInputString, String usernameInputString,
                String ageInputString, String heightInputString,
                String maxBenchInputString, String maxSquatInputString,
                String maxDeadliftInputString, String fastestMileInputString) {
        this.firstNameInputString = firstNameInputString;
        this.lastNameInputString = lastNameInputString;
        this.birthdayInputString = birthdayInputString;
        this.usernameInputString = usernameInputString;
        this.ageInputString = ageInputString;
        this.heightInputString = heightInputString;
        this.maxBenchInputString = maxBenchInputString;
        this.maxSquatInputString = maxSquatInputString;
        this.maxDeadliftInputString = maxDeadliftInputString;
        this.fastestMileInputString = fastestMileInputString;

    }

    public void writeNewUser(String firstNameInputString,String lastNameInputString,
                              String birthdayInputString, String usernameInputString,
                              String ageInputString, String heightInputString,
                              String maxBenchInputString, String maxSquatInputString,
                              String maxDeadliftInputString, String fastestMileInputString,
                              String email) {
        User user = new User(firstNameInputString, lastNameInputString, birthdayInputString, usernameInputString,
                ageInputString, heightInputString, maxBenchInputString, maxSquatInputString, maxDeadliftInputString,
                fastestMileInputString);

        mDatabase = FirebaseDatabase.getInstance().getReference();


        mDatabase.child("users").child(email).setValue(user);

    }
}
