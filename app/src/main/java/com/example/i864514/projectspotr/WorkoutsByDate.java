package com.example.i864514.projectspotr;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Michael on 10/17/2017.
 */

public class WorkoutsByDate {

    private DatabaseReference mDatabase;

    public String userID;
    public String Date;
    public String workoutCount;



    public WorkoutsByDate() {

    }

    public WorkoutsByDate(String userID, String Date, String workoutCount) {
        this.userID = userID;
        this.Date = Date;
        this.workoutCount = workoutCount;

    }

    public void writeNewDate(String userID, String Date, String workoutCount)
    {
        mDatabase = FirebaseDatabase.getInstance().getReference();

        WorkoutsByDate workout= new WorkoutsByDate(userID, Date, workoutCount);

        mDatabase.child("Workouts").child(userID).child("WorkoutsByDate").child(Date).child("Workout Info").setValue(workout);

    }

    public void deleteDate(String userID, String Date, String workoutCount)
    {
        mDatabase = FirebaseDatabase.getInstance().getReference();

        WorkoutsByDate workout= new WorkoutsByDate(userID, Date, workoutCount);

        mDatabase.child("Workouts").child(userID).child("WorkoutsByDate").child(Date).setValue("");
    }
}
