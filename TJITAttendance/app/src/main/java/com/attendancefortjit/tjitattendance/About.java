package com.attendancefortjit.tjitattendance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by jbran on 28-01-2016.
 */
public class About extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(About.this,Welcome.class);
        startActivity(intent);
        finish();
    }
}
