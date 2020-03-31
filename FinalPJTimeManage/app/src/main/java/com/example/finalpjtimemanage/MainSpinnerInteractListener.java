package com.example.finalpjtimemanage;

import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import static com.example.finalpjtimemanage.MainActivity.handle;
import static com.example.finalpjtimemanage.MainActivity.index;
import static com.example.finalpjtimemanage.MainActivity.note;
import static com.example.finalpjtimemanage.MainActivity.tics;
import static com.example.finalpjtimemanage.MainActivity.spinnerWork;

public class MainSpinnerInteractListener implements AdapterView.OnItemSelectedListener, View.OnTouchListener {

    boolean userSelect = false;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        userSelect = true;
        return false;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (userSelect) {

            // On selecting a spinner item
            String item = parent.getItemAtPosition(position).toString();

            // Showing selected spinner item
            Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
            handle.getWorks().get(index).setTime(tics);
            if (index == 0) index = spinnerWork.getSelectedItemPosition();
            else spinnerWork.setSelection(index);
            note.setText(handle.getWorks().get(index).getNote());
            tics = handle.getWorks().get(index).getTime() == 0 ? System.currentTimeMillis() : handle.getWorks().get(index).getTime();
            userSelect = false;
        }

    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
