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
public class Sem3 extends Activity {
    Button internal3,external3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sem3);
        ParseUser currentUser = ParseUser.getCurrentUser();
        final String struser = currentUser.getUsername().toString();
        internal3 = (Button) findViewById(R.id.bsem3int);
        external3 = (Button) findViewById(R.id.bsem3ext);

        external3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sem3.this,External3.class);
                intent.putExtra("message",struser);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Sem3.this,Marks.class);
        startActivity(intent);
        finish();
    }
}
