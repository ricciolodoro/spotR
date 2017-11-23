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
    public String userID;
    public int reps1;
    public int reps2;
    public int reps3;
    public int armWeight;
    public int legWeight;
    public int olympicWeight;


    public User() {

    }

    public User(String firstNameInputString,String lastNameInputString,
                String birthdayInputString, String usernameInputString,
                String ageInputString, String heightInputString, String liftingRegimentInputString,
                String maxBenchInputString, String maxSquatInputString,
                String maxDeadliftInputString, String fastestMileInputString, String userID, int reps1, int reps2, int reps3, int armWeight, int legWeight, int olympicWeight) {
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
        this.armWeight=armWeight;
        this.legWeight=legWeight;
        this.olympicWeight=olympicWeight;

    }

    public void writeNewUser(String firstNameInputString,String lastNameInputString,
                              String birthdayInputString, String usernameInputString,
                              String ageInputString, String heightInputString, String liftingRegimentInputString,
                              String maxBenchInputString, String maxSquatInputString,
                              String maxDeadliftInputString, String fastestMileInputString,
                              String userID, int reps1, int reps2, int reps3, int armWeight, int legWeight, int olympicWeight)
    {
        User user = new User(firstNameInputString, lastNameInputString, birthdayInputString, usernameInputString,
                ageInputString, heightInputString, liftingRegimentInputString, maxBenchInputString, maxSquatInputString, maxDeadliftInputString,
                fastestMileInputString, userID, reps1, reps2, reps3, armWeight, legWeight, olympicWeight);

        mDatabase = FirebaseDatabase.getInstance().getReference();


        mDatabase.child("Users").child(userID).setValue(user);

    }
}
