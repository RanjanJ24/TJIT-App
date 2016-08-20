package com.attendancefortjit.tjitattendance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by RJ on 01/04/16.
 */
public class Timetable extends Activity {

    TextView timetable, timetablelink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timetable);
        timetable = (TextView) findViewById(R.id.tvtimetable);
        timetablelink = (TextView) findViewById(R.id.tvtimetablelink);
        timetable.setText("Please click on the link below to download the current timetable:");
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Timetable.this,Welcome.class);
        startActivity(intent);
        finish();
    }

}
