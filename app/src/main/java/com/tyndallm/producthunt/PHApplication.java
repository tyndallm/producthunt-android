package com.tyndallm.producthunt;

import android.app.Application;

import net.danlew.android.joda.JodaTimeAndroid;

/**
 * Created by tyndallm on 7/14/15.
 */
public class PHApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        JodaTimeAndroid.init(this);
    }

}
