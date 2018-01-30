package com.uowee.droid.app;

import android.app.Application;

import com.uowee.utauta.Util;

/**
 * Created by GuoWee on 2018/1/30.
 */

public class DroidApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Util.init(this);
    }
}
