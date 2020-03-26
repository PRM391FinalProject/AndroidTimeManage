package com.example.finalpjtimemanage;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import entity.U1Work;
import entity.U234Work;
import entity.Work;

public class CreateEditWorkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String btnText;
        int btnImg;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_edit_work);


        Button button = findViewById(R.id.btnCreateNewCreate);
        if (!MainActivity.EDIT_FLAG) {
            btnText = getResources().getString(R.string.btnCreateNew);
            btnImg = R.drawable.ic_add;
        } else {
            btnText = getResources().getString(R.string.btnEdit);
            btnImg = R.drawable.ic_edit;
        }


        button.setText(btnText);
        button.setCompoundDrawablesWithIntrinsicBounds(0, 0, btnImg, 0);

        Spinner spinner = (Spinner) findViewById(R.id.spCreateNewType);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.work_type, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
//        wrapContent();
    }

//    private void wrapContent(){
//        TextInputLayout deadline = findViewById(R.id.tilCreateNewDeadLine);
//        CheckBox rest = findViewById(R.id.cbCreateNewRestTime);
//        CheckBox notice = findViewById(R.id.cbCreateNewNotice);
//        RadioGroup group = findViewById(R.id.rgCreateNewDailies);
//        Spinner spinner = findViewById(R.id.spCreateNewType);
//        if(spinner.getSelectedItemPosition()==0){
//            group.setVisibility(View.GONE);
//            deadline.setVisibility(View.VISIBLE);
//            rest.setVisibility(View.VISIBLE);
//            notice.setVisibility(View.VISIBLE);
//        }else{
//            group.setVisibility(View.VISIBLE);
//            deadline.setVisibility(View.GONE);
//            rest.setVisibility(View.GONE);
//            notice.setVisibility(View.GONE);
//        }
//    }

    public void btnNewOnClick(View view) {
        Spinner spinner = findViewById(R.id.spCreateNewType);
        TextInputEditText name = findViewById(R.id.tieCreateNewName);
        TextInputEditText note = findViewById(R.id.tieCreateNewNote);
        TextInputEditText date = findViewById(R.id.tieCreateNewDeadline);
        CheckBox notice = findViewById(R.id.cbCreateNewNotice);
        CheckBox rest = findViewById(R.id.cbCreateNewRestTime);
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

        switch (spinner.getSelectedItemPosition()) {
            case 1:
                if (MainActivity.EDIT_FLAG) {
                    try {
                        U1Work work = new U1Work(name.getText().toString(), 0, note.getText().toString(), 1, format.parse(date.getText().toString()), notice.isChecked(), rest.isChecked());
                        MainActivity.handle.getWorks().add(work);
                        finish();
                    } catch (ParseException e) {
                        e.printStackTrace();
                        break;
                    }
                }else{
                    try {

                        U1Work work = (U1Work) MainActivity.handle.getWorks().get(MainActivity.index);
                        work.setName(name.getText().toString());
                        work.setNote(note.getText().toString());
                        work.setNotice(notice.isChecked());
                        work.setRest(rest.isChecked());
                        work.setDeadLine(format.parse(date.getText().toString()));
                    }catch (Exception e){

                    }
                }

            case 2:
            case 3:
            case 4:
                break;
        }
    }

    public void btnBackOnClick(View view) {
        finish();
    }

//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        // On selecting a spinner item
//        String item = parent.getItemAtPosition(position).toString();
//
//        // Showing selected spinner item
//        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
//
////        wrapContent();
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//
//    }

}
