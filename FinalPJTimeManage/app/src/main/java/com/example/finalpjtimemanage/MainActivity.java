package com.example.finalpjtimemanage;

import androidx.annotation.NonNull;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import entity.U1Work;
import entity.Work;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<String> keys;
    public static boolean EDIT_FLAG = false;
    //public static HandlingDataFile handle;
    public static long tics = 0;
    public static Timer timer;
    public static TextView note;
    public static TextView time;
    public static Spinner spinnerWork;
    public static DatabaseReference db;
    public static int index;
    public static LinkedHashMap<String, Work> works;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        works = new LinkedHashMap<>();
        db = FirebaseDatabase.getInstance().getReference().child("Works");

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                works.clear();
                final List<String> name = new ArrayList<>();
                for (DataSnapshot item : dataSnapshot.getChildren()
                ) {
                    works.put(item.getKey(), item.getValue(U1Work.class));
                    name.add(item.getValue(U1Work.class).getName());
                }
                keys = new ArrayList<>(works.keySet());
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, name);
                for(int i=0;i<arrayAdapter.getCount();i++){
                    System.out.println(arrayAdapter.getItem(i));
                }
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerWork.setAdapter(arrayAdapter);

                index = 0;

                //get key
                String key = keys.get(index);
                note.setText(works.get(key).getNote());

                //Timer
                tics = works.get(key).getTime() == 0 ? System.currentTimeMillis() : works.get(key).getTime();
                timer = new Timer();
                timer.schedule(new firstTask(), 0, 500);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        note = findViewById(R.id.tvNote);
        time = findViewById(R.id.tvTimer);

        //Spinner action
        spinnerWork = findViewById(R.id.spWorks);
        spinnerWork.post(new Runnable() {
            @Override
            public void run() {
                MainSpinnerInteractListener listener = new MainSpinnerInteractListener();
                spinnerWork.setOnItemSelectedListener(listener);

            }
        });

    }


    public void btnNextOnClick(View view) {
        index = spinnerWork.getSelectedItemPosition();
        String key = keys.get(index);
        if (index < works.size() - 1) {

            //save time
            works.get(key).setTime(tics);
            index++;
            key = keys.get(index);
            Work work = works.get(key);
            spinnerWork.setSelection(index);
            note.setText(work.getNote());
            tics = work.getTime() == 0 ? System.currentTimeMillis() : work.getTime();
        }
    }



    public void btnPrevOnClick(View view) {
        index = spinnerWork.getSelectedItemPosition();
        String key = keys.get(index);
        if (index > 0) {
            //save time
            works.get(key).setTime(tics);
            index--;
            key = keys.get(index);
            Work work = works.get(key);
            spinnerWork.setSelection(index);
            note.setText(work.getNote());
            tics = work.getTime() == 0 ? System.currentTimeMillis() : work.getTime();
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
    static final Handler h = new Handler(new Callback() {

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

    public static class firstTask extends TimerTask {

        @Override
        public void run() {
            h.sendEmptyMessage(0);
        }
    }

    ;

}
