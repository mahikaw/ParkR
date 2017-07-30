package com.example.mananwason.parkr.Activities;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by mananwason on 7/28/17.
 */

public class ParkR extends Application {

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }
}
