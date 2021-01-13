package com.example.test1;

import android.app.Application;

public class BaseApp extends Application {

    private static BaseApp baseApp;
    private static final String LOGIN_TYPE = "login_type";
    @Override
    public void onCreate() {
        super.onCreate();
        baseApp = this;
        SpUtil.setParam(LOGIN_TYPE, 0);
    }

    public static BaseApp getBaseApp() {
        return baseApp;
    }
}
