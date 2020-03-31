package com.example.finalpjtimemanage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import entity.HandlingDataFile;
import entity.Work;

public class MainActivity extends AppCompatActivity {
    public static int index;
    public static boolean EDIT_FLAG = false;
    public static HandlingDataFile handle;
    public static long tics = 0;
    Timer timer;
    public static TextView note;
    public static TextView time;
    public static Spinner spinnerWork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (handle == null) handle = new HandlingDataFile();

        note = findViewById(R.id.tvNote);
        time = findViewById(R.id.tvTimer);
        spinnerWork = findViewById(R.id.spWorks);

        ArrayList<Work> works;

        //check current data
        if (handle.getWorks() != null) {
            works = handle.getWorks();
        } else {
            //get data from file
            works = handle.GetDataWork();
        }
        if (!works.isEmpty()) {
            spinnerWork.post(new Runnable() {
                @Override
                public void run() {
                    MainSpinnerInteractListener listener = new MainSpinnerInteractListener();
                    spinnerWork.setOnItemSelectedListener(listener);

                }
            });
            //spinnerWork.setOnTouchListener(listener);
            handle.setWorks(works);
            List<String> workTitles = new ArrayList<>();
            for (int i = 0; i < works.size(); i++) {
                workTitles.add(works.get(i).getName());
            }
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, workTitles);

            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinnerWork.setAdapter(dataAdapter);

            note.setText(handle.getWorks().get(index).getNote());
        }

        //Timer
        tics = handle.getWorks().get(index).getTime() == 0 ? System.currentTimeMillis() : handle.getWorks().get(index).getTime();

        timer = new Timer();
        timer.schedule(new firstTask(), 0, 500);

    }


    public void btnNextOnClick(View view) {
        if (index < handle.getWorks().size() - 1) {
            handle.getWorks().get(index).setTime(tics);
            index++;
            spinnerWork.setSelection(index);
            note.setText(handle.getWorks().get(index).getNote());
            tics = handle.getWorks().get(index).getTime() == 0 ? System.currentTimeMillis() : handle.getWorks().get(index).getTime();

        }
    }

    public void btnPrevOnClick(View view) {
        if (index > 0) {
            handle.getWorks().get(index).setTime(tics);
            index--;
            spinnerWork.setSelection(index);
            note.setText(handle.getWorks().get(index).getNote());
            tics = handle.getWorks().get(index).getTime() == 0 ? System.currentTimeMillis() : handle.getWorks().get(index).getTime();
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
                Intent intent = new Intent(this, MainActivity.class);
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


    //timer
    final Handler h = new Handler(new Callback() {

        @Override
        public boolean handleMessage(Message msg) {
            double millis = System.currentTimeMillis() - tics;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;

            time.setText(String.format("Current work time: %d:%02d", minutes, seconds));
            return false;
        }
    });

    class firstTask extends TimerTask {

        @Override
        public void run() {
            h.sendEmptyMessage(0);
        }
    }

    ;

}
