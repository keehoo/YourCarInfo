package com.kree.keehoo.yourcarinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    public RecyclerView recyclerViewOfTheCarList; // RECYCLER VIEW!!!!!
    public Button addCarButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaoSession daoSession = ((CarApplication) getApplicationContext()).getDaoSession();
        checkForNewCar(daoSession);
        CarDao carDao = daoSession.getCarDao();
        List<Car> carList = carDao.loadAll();
        //carDao.deleteAll(); //                                   UNCOMMENT IF YOU NEED TO REMOVE ALL ITEMS FROM THE LIST.
        //addDummyCars(daoSession);
        Log.d("MainActivity", "" + carList.size());
        recyclerViewOfTheCarList = (RecyclerView) findViewById(R.id.main_activity_recyclerView);
        recyclerViewOfTheCarList.setLayoutManager(new LinearLayoutManager(this));
        MainActivityAdapter adapter = new MainActivityAdapter(this, carList);
        recyclerViewOfTheCarList.setAdapter(adapter);
        Log.d("recyclerViewStrt method", "RecyclerView started with " + recyclerViewOfTheCarList + " and list of " + carList.toString());
        Log.d("MainActivity", "Rozmiar Listy samochodow :  " + carList.size() + " Hash code : " + carList.hashCode());


      /*  addCarButton = (Button) findViewById(R.id.add_car_button_id);
        addCarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCarActivity.class);
                startActivity(intent);
            }
        });*/


    }


    public void addDummyCars(DaoSession daoSession) {

        Car car = new Car();
        car.setBrand("Ford---PUMA");
        car.setModel("Focus");
        car.setRegNum("GD481HJ");
        CarDao carDao = daoSession.getCarDao();
        carDao.insertOrReplace(car);
    }

    public void checkForNewCar(DaoSession daoSession) {

        Intent intent = getIntent();
        if (intent.hasExtra("carBrand")) {
            String carBrand = intent.getStringExtra("carBrand");
            Log.d("New car    ", carBrand);
            String carModel = intent.getStringExtra("carModel");
            Log.d("New car model   ", carModel);
            String regNum = intent.getStringExtra("regNum");
            Log.d("New car regNum      ", regNum);
            long ins_start_long = intent.getLongExtra("ins_start_long", 10L);
            long ins_stop_long = intent.getLongExtra("ins_stop_long", 20L);
            long tech_start_long = intent.getLongExtra("tech_start_long", 30L);
            long tech_stop_long = intent.getLongExtra("tech_stop_long", 40L);
            Car car = new Car();
            car.setBrand(carBrand);
            Log.d("car.setBrand   ", car.getBrand());
            car.setModel(carModel);
            car.setRegNum(regNum);
            car.setDateOfInsuranceStart(ins_start_long);
            car.setDateOfInsuranceEnd(ins_stop_long);
            car.setDateOfTechStart(tech_start_long);
            car.setDateOfTechEnd(tech_stop_long);

            CarDao carDao = daoSession.getCarDao();
            carDao.insertOrReplace(car);
            Log.d("  !!!New CAR added !!!", "Car : " + car.toString());

        } else {
            Log.d("NewCar?", " There's no carBrand id in the received intent Extras");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.new_car) {
            Intent intent = new Intent(this, AddCarActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
