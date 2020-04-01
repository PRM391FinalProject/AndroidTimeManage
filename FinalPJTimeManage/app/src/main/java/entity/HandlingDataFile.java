package entity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.example.finalpjtimemanage.MainActivity;
import com.example.finalpjtimemanage.MainSpinnerInteractListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Timer;

import static com.example.finalpjtimemanage.MainActivity.note;
import static com.example.finalpjtimemanage.MainActivity.spinnerWork;
import static com.example.finalpjtimemanage.MainActivity.timer;

public class HandlingDataFile {
    ////Local variable
    private ArrayList<Work> works;
    ArrayList<Work> allWorks;

    public ArrayList<Work> getWorks() {
        return works;
    }

    public void setWorks(ArrayList<Work> works) {
        this.works = works;
    }


    public ArrayList<Work> GetDataWork() {

        allWorks = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference allU1 = database.getReference().child("Works");

        allU1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    U1Work u1Work = item.getValue(U1Work.class);
                    System.out.println(u1Work);
                    allWorks.add(u1Work);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
//        allWorks.add(new U1Work("work", 0, "something", 1, "2020/2/3", true, true));
//        allWorks.add(new U1Work("work2", 0, "Someone", 1, "2020/2/3", true, true));
//        allWorks.add(new U1Work("work3", 0, "somewhere", 1, "2020/2/3", true, true));
//        allWorks.add(new U1Work("work4", 0, "sometime", 1, "2020/2/3", true, true));
//        allWorks.add(new U1Work("work5", 0, "something", 1, "2020/2/3", true, true));
//        allWorks.add(new U1Work("work6", 0, "something", 1, "2020/2/3", true, true));
        return allWorks;
    }

    public boolean SaveDataWork(ArrayList<Work> works) {
        for (Work u1:works
             ) {
            FirebaseDatabase.getInstance().getReference().child("Works").push().setValue(u1);
        }
        return true;
    }

    public boolean ExportData() {
        return true;
    }

    public void ImportData() {
    }
}

