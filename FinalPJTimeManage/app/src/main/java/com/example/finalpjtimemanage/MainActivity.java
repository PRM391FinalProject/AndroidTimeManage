package com.example.finalpjtimemanage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import entity.HandlingDataFile;
import entity.Work;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public static int index;
    public static boolean EDIT_FLAG = false;
    public static HandlingDataFile handle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (handle == null) handle = new HandlingDataFile();

        setContentView(R.layout.activity_main);

        ArrayList<Work> works;

        //check current data
        if (handle.getWorks() != null) {
            works = handle.getWorks();
        } else {
            //get data from file
            works = handle.GetDataWork();
        }
        if (!works.isEmpty()) {

            Spinner spinner = (Spinner) findViewById(R.id.spWorks);

            spinner.setOnItemSelectedListener(this);
            handle.setWorks(works);
            List<String> workTitles = new ArrayList<>();
            for (int i = 0; i < works.size(); i++) {
                workTitles.add(works.get(i).getName());
            }
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, workTitles);

            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinner.setAdapter(dataAdapter);
            changeSelectItem();
        }
    }

    private void changeSelectItem() {

        Spinner spinner = findViewById(R.id.spWorks);

        if (index == 0) index = spinner.getSelectedItemPosition();
        else spinner.setSelection(index);
        double time = handle.getWorks().get(index).getTime();
        TextView timer = findViewById(R.id.tvTimer);
//        timer.setText(time%3600 + ":" + time/60%60 + ":" + time%3600);

        TextView tvNote = findViewById(R.id.tvNote);
        tvNote.setText(handle.getWorks().get(index).getNote());
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

        changeSelectItem();
    }

    public void btnNextOnClick(View view) {
        if (index < handle.getWorks().size()-1) {
            index++;
            Spinner spinner = findViewById(R.id.spWorks);
            spinner.setSelection(index);
            TextView tvNote = findViewById(R.id.tvNote);
            tvNote.setText(handle.getWorks().get(index).getNote());
        }
    }

    public void btnPrevOnClick(View view) {
        if (index > 0) {
            index--;
            Spinner spinner = findViewById(R.id.spWorks);
            spinner.setSelection(index);

            TextView tvNote = findViewById(R.id.tvNote);
            tvNote.setText(handle.getWorks().get(index).getNote());
        }
    }

    public void btnCreateNew(View view) {
        EDIT_FLAG = false;
        startActivity(new Intent(this, CreateEditWorkActivity.class));
    }

    public void btnEditOnClick(View view) {
        EDIT_FLAG = true;
        startActivity(new Intent(this, CreateEditWorkActivity.class));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    ////Add and handle menu bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_screen_menu_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //respond to menu item selection
        switch (item.getItemId()) {
            case R.id.menu_home:
                Intent intent= new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                return true;
            case R.id.menu_daily:
                startActivity(new Intent(this, DailyWorkActivity.class));
                return true;
            case R.id.menu_help:
                startActivity(new Intent(this, HelpActivity.class));
                return true;
            case R.id.menu_manage_work:
                startActivity(new Intent(this, ManageWorkActivity.class));
                return true;
            case R.id.menu_report:
                startActivity(new Intent(this, ReportActivity.class));
                return true;
            case R.id.menu_login_logout:
                if (item.getTitle().equals(getString(R.string.menu_login))) {
                    startActivity(new Intent(this, LoginActivity.class));
                }
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }


}
