package com.example.finalpjtimemanage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
                finish();
                return true;
            case R.id.menu_daily:
                startActivity(new Intent(this, DailyWorkActivity.class));
                finish();
                return true;
            case R.id.menu_help:
                startActivity(new Intent(this, HelpActivity.class));
                finish();
                return true;
            case R.id.menu_manage_work:
                startActivity(new Intent(this, ManageWorkActivity.class));
                finish();
                return true;
            case R.id.menu_report:
                startActivity(new Intent(this, ReportActivity.class));
                finish();
                return true;
            case R.id.menu_login_logout:
                if (item.getTitle().equals(getString(R.string.menu_login))) {
                    startActivity(new Intent(this, LoginActivity.class));
                }
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

}
