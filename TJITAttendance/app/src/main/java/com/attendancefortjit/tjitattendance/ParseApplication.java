package com.attendancefortjit.tjitattendance;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

/**
 * Created by jbran on 26-09-2015.
 */
public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Add your initialization code here
        Parse.initialize(this,"4HA8KdHwIUSNNspmfFEwpVhnA6U7NTStRCQcMgDs", "j2TjWinq3AgERhR2gb7yesA7nDpIcyx02bZ99tBb");

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        // If you would like all objects to be private by default, remove this
        // line.
        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);
    }
}
