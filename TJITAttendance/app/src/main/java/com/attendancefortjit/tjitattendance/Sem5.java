package com.attendancefortjit.tjitattendance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.parse.ParseUser;

/**
 * Created by jbran on 01-11-2015.
 */
public class Sem5 extends Activity {
    Button internal5,external5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sem5);
        ParseUser currentUser = ParseUser.getCurrentUser();
        final String struser = currentUser.getUsername().toString();
        internal5 = (Button) findViewById(R.id.bsem5int);
        external5 = (Button) findViewById(R.id.bsem5ext);

        external5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sem5.this,External5.class);
                intent.putExtra("message",struser);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Sem5.this,Marks.class);
        startActivity(intent);
        finish();
    }
}
