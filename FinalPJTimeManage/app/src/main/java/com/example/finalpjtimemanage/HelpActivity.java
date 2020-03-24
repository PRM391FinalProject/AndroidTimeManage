package com.example.finalpjtimemanage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
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
