package com.example.finalpjtimemanage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class CreateNewWorkActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_work);

        Spinner spinner = (Spinner) findViewById(R.id.spCreateNewType);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.work_type, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        wrapContent(spinner.getSelectedItemPosition());
    }

    private void wrapContent(int index){
        TextInputLayout deadline = findViewById(R.id.tilCreateNewDeadLine);
        CheckBox rest = findViewById(R.id.cbCreateNewRestTime);
        CheckBox notice = findViewById(R.id.cbCreateNewNotice);
        RadioGroup group = findViewById(R.id.rgCreateNewDailies);
        if(index==0){
            group.setVisibility(View.GONE);
            deadline.setVisibility(View.VISIBLE);
            rest.setVisibility(View.VISIBLE);
            notice.setVisibility(View.VISIBLE);
        }else{
            group.setVisibility(View.VISIBLE);
            deadline.setVisibility(View.GONE);
            rest.setVisibility(View.GONE);
            notice.setVisibility(View.GONE);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner = (Spinner) findViewById(R.id.spCreateNewType);
        spinner.setOnItemSelectedListener(this);
        wrapContent(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
