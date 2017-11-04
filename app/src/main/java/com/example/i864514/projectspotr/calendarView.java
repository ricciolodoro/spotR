package com.example.i864514.projectspotr;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.icu.util.Calendar;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import static android.app.Activity.RESULT_OK;

@SuppressLint("ValidFragment")
public class calendarView extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    static String extraDate = "EXTRA_DATE";
    String date = "";

    public Dialog onCreateDialog(Bundle savedInstanceState) {
// Use the current date as the default date in the dialog
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);


        String extraDate = "EXTRA_DATE";
        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);


    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        //show to the selected date in the text box
        date = (month+1) + "-" + day + "-" + year;




        Intent resultIntent2 = new Intent(getContext(), workoutPage.class);
        resultIntent2.putExtra(workoutPage.extraDate, date);

        getContext().startActivity(resultIntent2);


    }



}