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
import com.parse.SignUpCallback;

import java.util.regex.Pattern;

/**
 * Created by jbran on 24-10-2015.
 */
public class Signup extends Activity {

    Button register;

    String regnotxt;
    String usntxt;
    String passwordtxt;
    String emailtxt;
    EditText password;
    EditText regno;
    EditText usn;
    EditText email;

    private static final Pattern rPattern
            = Pattern.compile("^[A-Z]{3}[0-9]{6}$");

    private boolean isValidreg(CharSequence reg) {
        return rPattern.matcher(reg).matches();
    }

    private static final Pattern uPattern
            = Pattern.compile("^[1-4][A-Z]{2}[1-9]{2}[A-Z]{2}[0-9]{3}$");

    private boolean isValidusn(CharSequence usn) {
        return uPattern.matcher(usn).matches();
    }
    private static final Pattern ePattern
            = Pattern.compile("^[A-Za-z0-9]*@[a-z]*[.]com$");

    private boolean isValidemail(CharSequence eml) {
        return ePattern.matcher(eml).matches();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.signup);
        // Locate EditTexts in main.xml
        regno = (EditText) findViewById(R.id.etregno);
        password = (EditText) findViewById(R.id.etpassword);
        usn = (EditText) findViewById(R.id.etusn);
        email = (EditText) findViewById(R.id.etemail);

        regno.setTextColor(Color.parseColor("#ffffff"));
        regno.getBackground().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);
        regno.setHintTextColor(getResources().getColor(R.color.white));
        password.setTextColor(Color.parseColor("#ffffff"));
        password.getBackground().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);
        password.setHintTextColor(getResources().getColor(R.color.white));
        usn.setTextColor(Color.parseColor("#ffffff"));
        usn.getBackground().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);
        usn.setHintTextColor(getResources().getColor(R.color.white));
        email.setTextColor(Color.parseColor("#ffffff"));
        email.getBackground().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);
        email.setHintTextColor(getResources().getColor(R.color.white));

        // Locate Buttons in main.xml
        register= (Button) findViewById(R.id.bsignup);

        register.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                // Retrieve the text entered from the EditText
                regnotxt = regno.getText().toString();
                passwordtxt = password.getText().toString();
                usntxt = usn.getText().toString();
                emailtxt = email.getText().toString();

                    // Force user to fill up the form
                    if (regnotxt.equals("") && passwordtxt.equals("")) {
                        Toast.makeText(getApplicationContext(),
                                "Please complete the sign up form",
                                Toast.LENGTH_LONG).show();

                    } else if (isValidreg(regnotxt)&&isValidusn(usntxt) &&isValidemail(emailtxt)) {
                        // Save new user data into Parse.com Data Storage
                        ParseUser user = new ParseUser();
                        user.setUsername(usntxt);
                        user.setPassword(passwordtxt);
                        user.signUpInBackground(new SignUpCallback() {
                            public void done(ParseException e) {
                                if (e == null) {
                                    Intent intent = new Intent(Signup.this, LoginandSignupActivity.class);
                                    startActivity(intent);
                                    // Show a simple Toast message upon successful registration
                                    Toast.makeText(getApplicationContext(),
                                            "Successfully Signed up, please log in.",
                                            Toast.LENGTH_LONG).show();

                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(),
                                            "Username already existing. Try logging in again", Toast.LENGTH_LONG)
                                            .show();
                                }
                            }
                        });
                    } else if(!isValidreg(regnotxt)){
                        Toast.makeText(getApplicationContext(),
                                "StudentId Invalid. Enter the correct StudentId(eg:BEC123309)",
                                Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),
                                "usn or email Invalid. Enter the correct usn(eg:1TJ12CS075) " +
                                        "and the correct mail id(eg:example@mail.com)",
                                Toast.LENGTH_LONG).show();
                    }
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Signup.this, LoginandSignupActivity.class);
        startActivity(intent);
        finish();
    }
}
