package com.example.finalpjtimemanage;

import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import entity.Work;

import static com.example.finalpjtimemanage.MainActivity.index;
import static com.example.finalpjtimemanage.MainActivity.keys;
import static com.example.finalpjtimemanage.MainActivity.note;
import static com.example.finalpjtimemanage.MainActivity.tics;
import static com.example.finalpjtimemanage.MainActivity.spinnerWork;
import static com.example.finalpjtimemanage.MainActivity.works;

public class MainSpinnerInteractListener implements AdapterView.OnItemSelectedListener, View.OnTouchListener {


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

        Work work = works.get(keys.get(index));
        work.setTime(tics);

        index = spinnerWork.getSelectedItemPosition();

        work = works.get(keys.get(index));
        note.setText(work.getNote());
        tics = work.getTime() == 0 ? System.currentTimeMillis() : work.getTime();


    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
