package com.example.i864514.projectspotr;

import android.Manifest;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class exercises extends AppCompatActivity {

    Spinner bodyPartsSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

        Spinner bodyPartsSpinner = (Spinner) findViewById(R.id.bodyPartsSpinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.body_parts, R.layout.custom_text_for_spinner);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.custom_spinner_drop_down);
// Apply the adapter to the spinner
        bodyPartsSpinner.setAdapter(adapter);
    }
    public void onClick(View v) {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.exercises_search_and_home, menu);

        Drawable previousScreenDrawable = menu.findItem(R.id.search).getIcon();
        previousScreenDrawable.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);

        Drawable homeDrawable = menu.findItem(R.id.backHome).getIcon();
        homeDrawable.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        return true;
    }
}
