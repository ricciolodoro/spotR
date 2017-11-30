package com.example.i864514.projectspotr;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Michael on 10/17/2017.
 */

public class DatesByUserID {

    private DatabaseReference mDatabase;

    private int workoutcount;

    public DatesByUserID() {

    }

    public DatesByUserID(int workoutcount) {
        this.workoutcount = workoutcount;
    }

    public int getWorkoutcount() {
        return workoutcount;
    }

    public void setWorkoutcount(int workoutcount) {
        this.workoutcount = workoutcount;
    }

    public void writeNewDate(String userID, String date, int workoutcount)     {
        mDatabase = FirebaseDatabase.getInstance().getReference();

        DatesByUserID d = new DatesByUserID(workoutcount);

        mDatabase.child("Workouts").child(userID).child(date).setValue(d);

    }

    public void deleteDate(String userID, String date)    {
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("Workouts").child(userID).child(date).removeValue();
    }
}
