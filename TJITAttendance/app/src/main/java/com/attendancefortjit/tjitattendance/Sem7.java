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
public class Sem7 extends Activity {
    Button internal7,external7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sem7);
        ParseUser currentUser = ParseUser.getCurrentUser();
        final String struser = currentUser.getUsername().toString();
        internal7 = (Button) findViewById(R.id.bsem7int);
        external7 = (Button) findViewById(R.id.bsem7ext);

        external7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sem7.this,External7.class);
                intent.putExtra("message",struser);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Sem7.this,Marks.class);
        startActivity(intent);
        finish();
    }
}
