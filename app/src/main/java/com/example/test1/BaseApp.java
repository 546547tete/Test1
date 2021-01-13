package com.example.test1;

import android.app.Application;

import com.example.test1.db.DaoMaster;

public class BaseApp extends Application {
//    private static volatile BaseApp ourInstance = new BaseApp();
//
//    public static BaseApp getInstance() {
//        if (ourInstance == null) {
//            synchronized (BaseApp.class) {
//                if (ourInstance == null) {
//                    ourInstance = new BaseApp();
//                }
//            }
//        }
//        return ourInstance;
//    }
    private static BaseApp baseApp;
    private BaseApp() {
        baseApp=this;
    }

    public static BaseApp getBaseApp() {
        return baseApp;
    }
}
