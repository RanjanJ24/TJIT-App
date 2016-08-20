package com.attendancefortjit.tjitattendance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by jbran on 01-11-2015.
 */
public class Marks extends Activity {

    Button sem1,sem2,sem3,sem4,sem5,sem6,sem7,sem8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.marks);
        sem1 = (Button) findViewById(R.id.b1sem);
        sem3 = (Button) findViewById(R.id.b3sem);
        sem5 = (Button) findViewById(R.id.b5sem);
        sem7 = (Button) findViewById(R.id.b7sem);


        sem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Marks.this, Sem1.class);
                startActivity(intent);
                finish();
            }
        });
        sem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Marks.this, Sem3.class);
                startActivity(intent);
                finish();
            }
        });
        sem5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Marks.this, Sem5.class);
                startActivity(intent);
                finish();
            }
        });
        sem7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Marks.this, Sem7.class);
                startActivity(intent);
                finish();
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Marks.this, Welcome.class);
        startActivity(intent);
        finish();
    }
}
