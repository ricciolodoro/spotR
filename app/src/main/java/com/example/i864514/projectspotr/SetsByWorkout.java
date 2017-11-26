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

public class SetsByWorkout {

    private DatabaseReference mDatabase;

    public String userID;
    public String Date;
    public String workoutCount;
    public String vidURI;
    public String reps;
    public String weight;
    public String setCount;



    public SetsByWorkout(){

    }

    public SetsByWorkout(String userID, String Date, String workoutCount, String vidURI, String reps, String weight, String setCount){
        this.userID = userID;
        this.Date = Date;
        this.workoutCount = workoutCount;
        this.vidURI = vidURI;
        this.reps = reps;
        this.weight = weight;
        this.setCount = setCount;

    }

    public void writeNewSet(String userID, String Date, String workoutCount, String vidURI, String reps, String weight, String setCount)
    {
        mDatabase = FirebaseDatabase.getInstance().getReference();

        SetsByWorkout set = new SetsByWorkout(userID, Date, workoutCount, vidURI, reps, weight, setCount);

        mDatabase.child("Workouts").child(userID).child("WorkoutsByDate").child(Date).child(workoutCount).child(setCount).setValue(set);

    }

    public void writeNewVidUri(String userID, String Date, String workoutCount, String vidURI, String setCount) {
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("Workouts").child(userID).child("WorkoutsByDate").child(Date).child(workoutCount).child(setCount).child("vidURI").setValue(vidURI);
    }

    public void writeNewWeight(String userID, String Date, String workoutCount, String weight, String setCount) {
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("Workouts").child(userID).child("WorkoutsByDate").child(Date).child(workoutCount).child(setCount).child("weight").setValue(weight);
    }

    public void writeNewReps(String userID, String Date, String workoutCount, String reps, String setCount) {
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("Workouts").child(userID).child("WorkoutsByDate").child(Date).child(workoutCount).child(setCount).child("reps").setValue(reps);
    }



}
