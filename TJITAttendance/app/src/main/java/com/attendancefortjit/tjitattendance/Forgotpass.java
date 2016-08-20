package com.attendancefortjit.tjitattendance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;

/**
 * Created by RJ on 16/04/16.
 */
public class Forgotpass extends Activity {


    EditText resetemail;
    Button reset;
    TextView resetext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgotpass);
        resetemail = (EditText) findViewById(R.id.etresetemail);
        reset = (Button) findViewById(R.id.bresetpass);
        resetext = (TextView) findViewById(R.id.tvresetresult);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = resetemail.getText().toString();
                ParseUser.requestPasswordResetInBackground(text,
                        new RequestPasswordResetCallback() {
                            public void done(ParseException e) {
                                if (e == null) {
                                    // An email was successfully sent with reset instructions.
                                    resetext.setText("Reset Email has been sent");
                                } else {
                                    // Something went wrong. Look at the ParseException to see what's up.
                                    resetext.setText("Reset Email has not been sent due to some error");
                                }
                            }
                        });
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Forgotpass.this , LoginandSignupActivity.class);
        startActivity(intent);
        finish();
    }
}
