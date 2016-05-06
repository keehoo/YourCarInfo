package com.kree.keehoo.yourcarinfo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    public RecyclerView recyclerViewOfTheCarList; // RECYCLER VIEW!!!!!


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaoSession daoSession = ((CarApplication) getApplicationContext()).getDaoSession();
        CarDao carDao = daoSession.getCarDao();
        List<Car> carList = carDao.loadAll();
        addDummyCars(daoSession);
        Log.d("MainActivity", ""+carList.size());
        startRecyclerView(recyclerViewOfTheCarList, carList);


    }


    public void addDummyCars(DaoSession daoSession) {

        Car car = new Car();
        car.setBrand("Ford---PUMA");
        car.setModel("Focus");
        car.setRegNum("GD481HJ");
        CarDao carDao = daoSession.getCarDao();
        carDao.insertOrReplace(car);
    }

    private void startRecyclerView(RecyclerView rv, List<Car> carList){
        Log.d("recyclerViewStrt method", "RecyclerView started with "+rv + " and list of "+carList.toString());
        rv = (RecyclerView) findViewById(R.id.main_activity_recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(this));
        MainActivityAdapter adapter = new MainActivityAdapter(this, carList);
        rv.setAdapter(adapter);
        Log.d("MainActivity", ""+carList.size());

    }
}
