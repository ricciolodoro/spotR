package com.example.i864514.projectspotr;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

/**
 * Created by Michael on 11/20/2017.
 */

public class WorkoutsByDate {

    private DatabaseReference mDatabase;

    private String workout;
    private int setcount;

    public WorkoutsByDate(){

    }

    public WorkoutsByDate(String workout, int setcount){

        this.workout = workout;
        this.setcount = setcount;


    }

    public String getWorkout() {
        return workout;
    }

    public void setWorkout(String workout) {
        this.workout = workout;
    }

    public int getSetcount() {
        return setcount;
    }

    public void setSetcount(int setcount) {
        this.setcount = setcount;
    }

    public void writeNewWorkout(String userID, String date, String workout, int setcount, int workoutcount)
    {
        mDatabase = FirebaseDatabase.getInstance().getReference();

        WorkoutsByDate w = new WorkoutsByDate(workout, setcount);

        mDatabase.child("Workouts").child(userID).child(date).child("workouts").child(Integer.toString(workoutcount)).setValue(w);

    }

    public void deleteWorkout(String userID, String date, int wIndex)
    {
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("Workouts").child(userID).child(date).child("workouts").child(Integer.toString(wIndex)).removeValue();
    }

}
