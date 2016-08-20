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
public class Sem1 extends Activity {
    Button internal,external;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sem1);
        ParseUser currentUser = ParseUser.getCurrentUser();
        final String struser = currentUser.getUsername().toString();
        internal = (Button) findViewById(R.id.bsem1int);
        external = (Button) findViewById(R.id.bsem1ext);
        
        internal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sem1.this,Internal1.class);
                intent.putExtra("message",struser);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Sem1.this,Marks.class);
        startActivity(intent);
        finish();
    }
}
