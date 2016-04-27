package com.kree.keehoo.yourcarinfo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    public RecyclerView carList; // RECYCLER VIEW!!!!!


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaoSession daoSession = ((CarApplication) getApplicationContext()).getDaoSession();
        CarDao carDao = daoSession.getCarDao();
        List<Car> carList = carDao.loadAll();
        addDummyCars(daoSession);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.main_activity_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MainActivityAdapter adapter = new MainActivityAdapter(this, carList);
        recyclerView.setAdapter(adapter);

    }


    public void addDummyCars(DaoSession daoSession) {

        Car car = new Car();
        car.setBrand("Ford");
        car.setModel("Focus");
        car.setRegNum("GD481HJ");
        CarDao carDao = daoSession.getCarDao();
        carDao.insertOrReplace(car);
    }
}
