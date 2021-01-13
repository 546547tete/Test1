package com.example.test1;

import android.app.Application;

public class BaseApp extends Application {

    private static BaseApp baseApp;
    @Override
    public void onCreate() {
        super.onCreate();
        baseApp = this;
    }

    public static BaseApp getBaseApp() {
        return baseApp;
    }
}
