package com.kree.keehoo.yourcarinfo.JavaClasses;

import android.app.Application;
import android.content.Context;

import com.kree.keehoo.yourcarinfo.DaoGeneratedFiles.CarApplication;
import com.kree.keehoo.yourcarinfo.DaoGeneratedFiles.CarDao;
import com.kree.keehoo.yourcarinfo.DaoGeneratedFiles.DaoSession;

/**
 * Created by keehoo on 12.06.2016.
 */
public class DefaultMethods extends CarApplication {

    public CarDao getDao() {
        DaoSession daoSession = ((CarApplication)getApplicationContext()).getDaoSession();
        CarDao carDao = daoSession.getCarDao();
        return carDao;
    }


}
