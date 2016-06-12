package com.kree.keehoo.yourcarinfo.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.kree.keehoo.yourcarinfo.DaoGeneratedFiles.Car;
import com.kree.keehoo.yourcarinfo.DaoGeneratedFiles.CarApplication;
import com.kree.keehoo.yourcarinfo.DaoGeneratedFiles.CarDao;
import com.kree.keehoo.yourcarinfo.DaoGeneratedFiles.DaoSession;
import com.kree.keehoo.yourcarinfo.R;
import com.kree.keehoo.yourcarinfo.RecyclerViewAdapters.MainActivityAdapter;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public RecyclerView recyclerViewOfTheCarList; // RECYCLER VIEW!!!!!
    public MainActivityAdapter adapter;
    public List<Car> carList;

    public CarDao initializeDaoSession() {
        DaoSession daoSession = ((CarApplication) getApplicationContext()).getDaoSession();
        CarDao carDao = daoSession.getCarDao();
        return carDao;
    }

    public void allowClicking() {
        adapter.setOnClickListener(new MainActivityAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position, Car object) {
                Intent intent = new Intent(MainActivity.this, DisplayCarInfoActivity.class);
                Log.d("MainActivity", "Kliknieto pozycje na liscie  db:  " + object.getId());
                intent.putExtra("car_position", position);  // position on the LIST
                intent.putExtra("car_id", object.getId());  // ID from the SQLite db
                intent.putExtra("car_name", object.getBrand()); // Car brand ...
                startActivityForResult(intent, 2);

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        carList = initializeDaoSession().loadAll();
        Log.d("MainActivity", "onCREATE    " + carList.size());
        recyclerViewOfTheCarList = (RecyclerView) findViewById(R.id.main_activity_recyclerView);
        recyclerViewOfTheCarList.setLayoutManager(new LinearLayoutManager(this));
        if (carList.size() == 0) {
            Log.d("Main Activity", " car list equals zero!!!!");
            Toast.makeText(MainActivity.this, "Proszę dodać samochody do listy", Toast.LENGTH_LONG).show();

        }
        adapter = new MainActivityAdapter(this, carList);
        allowClicking();
        recyclerViewOfTheCarList.setAdapter(adapter);
        Log.d("recyclerViewStrt method", "RecyclerView started with " + recyclerViewOfTheCarList + " and list of " + carList.toString());
        Log.d("MainActivity", "Rozmiar Listy samochodow :  " + carList.size() + " Hash code : " + carList.hashCode());
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
            startActivityForResult(intent, 1);
            return true;
        }

        if (item.getItemId() == R.id.delete_all) {
            CarDao carDao = initializeDaoSession();
            carDao.deleteAll();
            recreate();  // NOT SURE WHETHER THIS IS THE RIGHT APPROACH TO RE-DRAW THE ACTIVITY / LIST ETC...
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);

        Log.d("MainActivity", "     Method:   onActivityResult");

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                String carBrand = data.getStringExtra("carBrand");
                DateTimeFormatter fmt = DateTimeFormat.forPattern("d MMMM, yyyy");
                Log.d("New car    ", carBrand);
                String carModel = data.getStringExtra("carModel");
                Log.d("New car model   ", carModel);
                String regNum = data.getStringExtra("regNum");
                Log.d("New car regNum      ", regNum);
                Long ins_start_long = data.getLongExtra("ins_start_long", 10L);
                Log.d("New car insurance start", "Insurance start = " + (new DateTime(ins_start_long)).toString(fmt));
                Long ins_stop_long = data.getLongExtra("ins_stop_long", 20L);
                Log.d("New car insurance end", "Insurance start = " + (new DateTime(ins_stop_long)).toString(fmt));
                Long tech_start_long = data.getLongExtra("tech_start_long", 30L);
                Long tech_stop_long = data.getLongExtra("tech_stop_long", 40L);

                Car car = new Car();
                car.setBrand(carBrand);
                Log.d("car.setBrand   ", car.getBrand());
                car.setModel(carModel);
                car.setRegNum(regNum);
                car.setDateOfInsuranceStart(ins_start_long);
                car.setDateOfInsuranceEnd(ins_stop_long);
                car.setDateOfTechStart(tech_start_long);
                car.setDateOfTechEnd(tech_stop_long);

                CarDao carDao = initializeDaoSession();
                carDao.insertOrReplace(car);
                carList = carDao.loadAll();
                adapter = new MainActivityAdapter(this, carList);
                //adapter.notifyDataSetChanged();
                recyclerViewOfTheCarList.setAdapter(adapter);
            }
            /*if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }*/
        }  //onActivityResult
        allowClicking();
    }

}
