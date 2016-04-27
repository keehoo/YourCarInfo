package com.kree.keehoo.yourcarinfo;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by keehoo on 27.04.2016.
 */
public class CarApplication extends Application{

public DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "lease-db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
