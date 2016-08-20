package com.attendancefortjit.tjitattendance;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by jbran on 27-09-2015.
 */
public class ChPassword extends Activity {

    EditText newpass,conpass;
    String newpassstr,conpassstr;
    Button reset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chpassword);
        newpass = (EditText) findViewById(R.id.etnewpass);
        reset = (Button) findViewById(R.id.breset);
        conpass = (EditText) findViewById(R.id.etconpass);
        newpass.setTextColor(Color.parseColor("#ffffff"));
        conpass.setTextColor(Color.parseColor("#ffffff"));
        newpass.getBackground().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);
        conpass.getBackground().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);
        newpass.setHintTextColor(getResources().getColor(R.color.white));
        conpass.setHintTextColor(getResources().getColor(R.color.white));
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newpassstr = newpass.getText().toString();
                conpassstr = conpass.getText().toString();
                if(conpassstr.equals(newpassstr)){
                    ParseUser parseuser = ParseUser.getCurrentUser();
                    parseuser.setPassword(newpassstr);
                    parseuser.saveInBackground();
                    Toast.makeText(getApplicationContext(),
                            "new password set",
                            Toast.LENGTH_SHORT).show();
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {

                        public void run() {

                            Intent intent = new Intent(ChPassword.this, Welcome.class);
                            startActivity(intent);
                            finish();
                        }

                    }, 1900);

                }
                else{
                    Toast.makeText(getApplicationContext(),
                            "Your Passwords Do Not Match. Please Reenter Your Passwords",
                            Toast.LENGTH_LONG).show();
                }



            }
        });
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent intent = new Intent(ChPassword.this , Welcome.class);
        startActivity(intent);
        finish();
    }
}
