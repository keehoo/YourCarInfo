package com.kree.keehoo.yourcarinfo.DaoGeneratedFiles;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import net.danlew.android.joda.JodaTimeAndroid;

/**
 * Created by keehoo on 27.04.2016.
 */
public class CarApplication extends Application {

    public DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "lease-db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        JodaTimeAndroid.init(this);
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
