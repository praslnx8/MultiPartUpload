package com.prasilabs.multipartupload;

import android.app.Application;

/**
 * Created by prasi on 1/2/16.
 */
import android.app.Application;
import android.content.Context;

import java.util.List;

public class MyApp extends Application {
    private static MyApp mInstance;
    private static Context mAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        this.setAppContext(getApplicationContext());
    }

    public static MyApp getInstance(){
        return mInstance;
    }
    public static Context getAppContext() {
        return mAppContext;
    }
    public void setAppContext(Context mAppContext) {
        this.mAppContext = mAppContext;
    }
}
