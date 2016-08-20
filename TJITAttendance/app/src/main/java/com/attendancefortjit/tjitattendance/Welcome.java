package com.attendancefortjit.tjitattendance;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.ViewDragHelper;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseUser;

import java.lang.reflect.Field;
import java.sql.Time;


/**
 * Created by jbran on 26-09-2015.
 */

public class Welcome extends Activity implements AdapterView.OnItemClickListener {

    // Declare Variable
    Button marks;
    Button attendance;
    Button timetable;

    private DrawerLayout drawerLayout;
    private ListView listView;
    private String[] content;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from singleitemview.xml
        setContentView(R.layout.welcome);


        // Retrieve current user from Parse.com
        ParseUser currentUser = ParseUser.getCurrentUser();

        // Convert currentUser into String
        final String struser = currentUser.getUsername().toString();




        marks = (Button) findViewById(R.id.bmarks);

        attendance = (Button) findViewById(R.id.battendance);

        timetable = (Button) findViewById(R.id.btimetable);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);

        setDrawerLeftEdgeSize(this, drawerLayout, 0.75f);

        content = getResources().getStringArray(R.array.content);
        listView = (ListView) findViewById(R.id.drawerList);
        listView.bringToFront();
        drawerLayout.requestLayout();
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, content));
        listView.setOnItemClickListener(this);



        marks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Welcome.this, Marks.class);
                startActivity(intent);
                finish();
            }
        });

        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Welcome.this , Data.class);
                intent.putExtra("message",struser);
                startActivity(intent);
                finish();
            }
        });

        timetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Welcome.this , Timetable.class);
                intent.putExtra("message",struser);
                startActivity(intent);
                finish();
            }
        });
    }
    public static void setDrawerLeftEdgeSize(Activity activity, DrawerLayout drawerLayout, float displayWidthPercentage) {
        if (activity == null || drawerLayout == null)
            return;

        try {
            // find ViewDragHelper and set it accessible
            Field leftDraggerField = drawerLayout.getClass().getDeclaredField("mLeftDragger");
            leftDraggerField.setAccessible(true);
            ViewDragHelper leftDragger = (ViewDragHelper) leftDraggerField.get(drawerLayout);
            // find edgesize and set is accessible
            Field edgeSizeField = leftDragger.getClass().getDeclaredField("mEdgeSize");
            edgeSizeField.setAccessible(true);
            int edgeSize = edgeSizeField.getInt(leftDragger);
            // set new edgesize
            Point displaySize = new Point();
            activity.getWindowManager().getDefaultDisplay().getSize(displaySize);
            edgeSizeField.setInt(leftDragger, Math.max(edgeSize, (int) (displaySize.x * displayWidthPercentage)));
        } catch (NoSuchFieldException e) {
            // ignore
        } catch (IllegalArgumentException e) {
            // ignore
        } catch (IllegalAccessException e) {
            // ignore
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position)
        {
            case 0: Intent intent = new Intent (Welcome.this , ChPassword.class);
                    startActivity(intent);
                    finish();
                    break;
            case 1: Intent intentnew = new Intent (Welcome.this , About.class);
                    startActivity(intentnew);
                    finish();
                    break;
            case 2: Intent intentcontact = new Intent (Welcome.this , Contactus.class);
                    startActivity(intentcontact);
                    finish();
                    break;
            case 3: ParseUser.logOut();
                    finish();
                    break;
        }
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("QUIT")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        // continue with delete
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }
}
