package com.example.doourbest.Work;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.doourbest.R;
import com.example.doourbest.Work.TeamListActivity;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class WorkCreateActivity extends Activity {
    int year1;
    int month1;
    int day1;
    int hour1;
    int minute1;

    EditText teamName, workName, workContents, roomPW;
    Button btnDate;
    Button btnTime;
    Button workCreateBtn;
    TextView textDate;
    TextView textTime;
    Intent intent;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework);
        setTitle("과제 추가하기");


        teamName = (EditText) findViewById(R.id.teamName);
        workName = (EditText) findViewById(R.id.workName);
        workContents = (EditText) findViewById(R.id.workContents);
        roomPW = (EditText) findViewById(R.id.roomPW);
        btnDate = (Button) findViewById(R.id.btnDate);
        btnTime = (Button) findViewById(R.id.btnTime);
        workCreateBtn = (Button) findViewById(R.id.workCreateBtn);
        textDate = (TextView) findViewById(R.id.textDate);
        textTime = (TextView) findViewById(R.id.textTime);

        Calendar cal = new GregorianCalendar();
        year1 = cal.get(Calendar.YEAR);
        month1 = cal.get(Calendar.MONTH);
        day1 = cal.get(Calendar.DAY_OF_MONTH);
        hour1 = cal.get(Calendar.HOUR_OF_DAY);
        minute1 = cal.get(Calendar.MINUTE);

        UpdateNow();

        btnDate.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                new DatePickerDialog(WorkCreateActivity.this, mDateSetListener, year1, month1, day1).show();

            }
        });


        btnTime.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                new TimePickerDialog(WorkCreateActivity.this, mTimeSetListener, hour1, minute1, false).show();
            }
        });


        workCreateBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                intent = new Intent(WorkCreateActivity.this, TeamListActivity.class);
                intent.putExtra("teamName", teamName.getText().toString());
                intent.putExtra("workName", workName.getText().toString());
                intent.putExtra("workContents", workContents.getText().toString());
                intent.putExtra("roomPW", roomPW.getText().toString());
                intent.putExtra("text", textDate.getText().toString() + textTime.getText().toString());
                startActivityForResult(intent, 0);
                teamName.setText("");
                workName.setText("");
                workContents.setText("");
                roomPW.setText("");
                textDate.setText("");
                textTime.setText("");
            }
        });


    }

    DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            year1 = year;
            month1 = monthOfYear;
            day1 = dayOfMonth;
            UpdateNow();
        }
    };


    TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            hour1 = hourOfDay;
            minute1 = minute;
            UpdateNow();
        }
    };


    void UpdateNow() {
        textDate.setText(String.format(" %d.%d.%d ", year1, month1 + 1, day1));
        textTime.setText(String.format(" %d시 %d분", hour1, minute1));
    }
}