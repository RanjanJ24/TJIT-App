package com.attendancefortjit.tjitattendance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by jbran on 29-01-2016.
 */
public class Contactus extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contactus);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Contactus.this,Welcome.class);
        startActivity(intent);
        finish();
    }
}
