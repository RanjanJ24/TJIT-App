package com.attendancefortjit.tjitattendance;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.regex.Pattern;

/**
 * Created by jbran on 26-09-2015.
 */
public class LoginandSignupActivity extends Activity {
    // Declare Variables
    Button loginbutton;
    TextView signup;
    TextView forgotpass;
    String regnotxt;
    String passwordtxt;
    EditText password;
    EditText regno;
    private static final Pattern sPattern
            = Pattern.compile("^[1-4][A-Z]{2}[1-9]{2}[A-Z]{2}[0-9]{3}$");

    private boolean isValid(CharSequence reg) {
        return sPattern.matcher(reg).matches();
    }


    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from main.xml
        setContentView(R.layout.loginsignup);
        // Locate EditTexts in main.xml
        regno = (EditText) findViewById(R.id.etregno);
        password = (EditText) findViewById(R.id.etpassword);
        regno.setTextColor(Color.parseColor("#ffffff"));
        password.setTextColor(Color.parseColor("#ffffff"));
        regno.getBackground().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);
        regno.setHintTextColor(getResources().getColor(R.color.white));
        password.getBackground().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);
        password.setHintTextColor(getResources().getColor(R.color.white));
        // Locate Buttons in main.xml
        loginbutton = (Button) findViewById(R.id.blogin);
        signup = (TextView) findViewById(R.id.bsignup);
        forgotpass = (TextView) findViewById(R.id.bforgotpass);



        // Login Button Click Listener
        loginbutton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                // Retrieve the text entered from the EditText
                regnotxt = regno.getText().toString();
                passwordtxt = password.getText().toString();

                // Send data to Parse.com for verification
                ParseUser.logInInBackground(regnotxt, passwordtxt,
                        new LogInCallback() {
                            public void done(ParseUser user, ParseException e) {
                                if (user != null) {
                                    // If user exist and authenticated, send user to Welcome.class
                                    Intent intent = new Intent(LoginandSignupActivity.this, Welcomename.class);
                                    startActivity(intent);
                                    Toast.makeText(getApplicationContext(),
                                            "Successfully Logged in",
                                            Toast.LENGTH_LONG).show();
                                    finish();
                                } else if (e != null) {
                                    Toast.makeText(
                                            getApplicationContext(),
                                            "username/password incorrect. Please try again or if " +
                                                    "not Signedup, please signup",
                                            Toast.LENGTH_LONG).show();
                                }

                            }
                        });
            }
        });
        // Sign up Button Click Listener
        signup.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                Intent intent = new Intent(LoginandSignupActivity.this , Signup.class);
                startActivity(intent);
                finish();
            }
        });

        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent1 = new Intent (LoginandSignupActivity.this , Forgotpass.class);
                startActivity(intent1);
                finish();
            }
        });
    }
}
