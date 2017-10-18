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
    public String liftingRegimentInputString;
    public String maxBenchInputString;
    public String maxSquatInputString;
    public String maxDeadliftInputString;
    public String fastestMileInputString;
    public int reps1;
    public int reps2;
    public int reps3;
    public float weight1;
    public float weight2;
    public float weight3;


    public User() {

    }

    public User(String firstNameInputString,String lastNameInputString,
                String birthdayInputString, String usernameInputString,
                String ageInputString, String heightInputString, String liftingRegimentInputString,
                String maxBenchInputString, String maxSquatInputString,
                String maxDeadliftInputString, String fastestMileInputString, int reps1, int reps2, int reps3, int weight1, int weight2, int weight3) {
        this.firstNameInputString = firstNameInputString;
        this.lastNameInputString = lastNameInputString;
        this.birthdayInputString = birthdayInputString;
        this.usernameInputString = usernameInputString;
        this.ageInputString = ageInputString;
        this.heightInputString = heightInputString;
        this.liftingRegimentInputString = liftingRegimentInputString;
        this.maxBenchInputString = maxBenchInputString;
        this.maxSquatInputString = maxSquatInputString;
        this.maxDeadliftInputString = maxDeadliftInputString;
        this.fastestMileInputString = fastestMileInputString;
        this.reps1=reps1;
        this.reps2=reps2;
        this.reps3=reps3;
        this.weight1=weight1;
        this.weight2=weight2;
        this.weight3=weight3;

    }

    public void writeNewUser(String firstNameInputString,String lastNameInputString,
                              String birthdayInputString, String usernameInputString,
                              String ageInputString, String heightInputString, String liftingRegimentInputString,
                              String maxBenchInputString, String maxSquatInputString,
                              String maxDeadliftInputString, String fastestMileInputString,
                              String email, int reps1, int reps2, int reps3, float weight1, float weight2, float weight3) {
        User user = new User(firstNameInputString, lastNameInputString, birthdayInputString, usernameInputString,
                ageInputString, heightInputString, liftingRegimentInputString, maxBenchInputString, maxSquatInputString, maxDeadliftInputString,
                fastestMileInputString, reps1, reps2, reps3, weight1, weight2, weight3);

        mDatabase = FirebaseDatabase.getInstance().getReference();


        mDatabase.child("users").child(email).setValue(user);

    }
}
