package com.example.franc;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by gravityshouldbenaut on 6/14/15.
 */
public class Franc extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "ZhouHQ0hatdaxxGNpbEMobcD3uLen8L4EAeR3Chj", "LFjKTjtVt4IlrVtIbRautrJWWdrBL5L2QSavrqRS");
    }
}
