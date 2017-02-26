package com.guyuga.databaseproject;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import io.realm.Realm;

/**
 * Created by guyug on 23/2/2017.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //start Realm
        Realm.init(this);
    }
}
