package com.example.finalpjtimemanage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import entity.HandlingDataFile;
import entity.Work;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HandlingDataFile handle = new HandlingDataFile();

        //get data from file
        ArrayList<Work> works = handle.GetDataWork();
        if(!works.isEmpty()){
            Spinner spinner = findViewById(R.id.spWorks);

            spinner.setOnItemSelectedListener(this);
            handle.works = works;
            List<String> workTitles = new ArrayList<>();
            for(int i=0;i<works.size();i++){
                workTitles.add(works.get(i).getName());
            }
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, R.layout.activity_main, workTitles);

            dataAdapter.setDropDownViewResource(R.layout.activity_main);

            spinner.setAdapter(dataAdapter);
        }
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
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
                startActivity(new Intent(this, MainActivity.class));
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
