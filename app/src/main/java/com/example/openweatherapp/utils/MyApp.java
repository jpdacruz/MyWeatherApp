package com.example.openweatherapp.utils;

import android.app.Application;
import android.content.Context;

/**
 * for get Instance or context in model
 */
public class MyApp extends Application {

    private static MyApp instance;

    public static MyApp getInstance(){

        return instance;
    }

    public static Context getContext(){

        return instance;
    }

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }
}