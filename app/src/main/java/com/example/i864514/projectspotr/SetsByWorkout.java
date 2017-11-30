package com.example.i864514.projectspotr;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Michael on 11/30/2017.
 */

public class SetsByWorkout {

    private DatabaseReference mDatabase;

    private int weight;
    private int reps;
    private String viduri;

    public SetsByWorkout() {

    }

    public SetsByWorkout(int weight, int reps, String viduri) {
        this.viduri = viduri;
        this.weight = weight;
        this.reps = reps;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public String getViduri() {
        return viduri;
    }

    public void setViduri(String viduri) {
        this.viduri = viduri;
    }


    public void writeNewSet(String date, String userID, int weight, int reps, String viduri, int wIndex, int sIndex) {

        mDatabase = FirebaseDatabase.getInstance().getReference();

        SetsByWorkout s = new SetsByWorkout(reps,weight,viduri);

        mDatabase.child("Workouts").child(userID).child(date).child("workouts").child(Integer.toString(wIndex)).child("sets").child(Integer.toString(sIndex)).setValue(s);

    }

    public void deleteSet(String date, String userID, int wIndex, int sIndex) {

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("Workouts").child(userID).child(date).child("workouts").child(Integer.toString(wIndex)).child("sets").child(Integer.toString(sIndex)).removeValue();


    }

}
