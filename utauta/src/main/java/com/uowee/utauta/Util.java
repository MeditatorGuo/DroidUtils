package com.uowee.utauta;

import android.app.Application;

/**
 * Created by GuoWee on 2018/1/17.
 */

public final class Util {

    private Util() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    private static Application mApp;

    private static void init(Application app) {
        mApp = app;
    }

    public static Application getApp() {
        if (mApp != null) return mApp;
        throw new NullPointerException("u should init first");
    }


}
