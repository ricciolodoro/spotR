package com.example.i864514.projectspotr;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Michael on 10/17/2017.
 */

public class WorkoutsByDate {

    private DatabaseReference mDatabase;

    public String Date;
    public int exerciseCount;



    public WorkoutsByDate() {

    }

    public WorkoutsByDate() {
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

    public void writeNewDate(String firstNameInputString,String lastNameInputString,
                             String birthdayInputString, String usernameInputString,
                             String ageInputString, String heightInputString, String liftingRegimentInputString,
                             String maxBenchInputString, String maxSquatInputString,
                             String maxDeadliftInputString, String fastestMileInputString,
                             String userID, int reps1, int reps2, int reps3, int armWeight, int legWeight, int olympicWeight)
    {
        WorkoutsByDate workoutByDate = new WorkoutsByDate(firstNameInputString, lastNameInputString, birthdayInputString, usernameInputString,
                ageInputString, heightInputString, liftingRegimentInputString, maxBenchInputString, maxSquatInputString, maxDeadliftInputString,
                fastestMileInputString, userID, reps1, reps2, reps3, armWeight, legWeight, olympicWeight);

        mDatabase = FirebaseDatabase.getInstance().getReference();


        mDatabase.child("Users").child(userID).child(Date).setValue(workoutByDate);

    }
}
