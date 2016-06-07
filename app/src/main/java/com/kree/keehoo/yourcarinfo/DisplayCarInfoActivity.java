package com.kree.keehoo.yourcarinfo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.DateTimeKeyListener;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.iwgang.countdownview.CountdownView;

public class DisplayCarInfoActivity extends AppCompatActivity {

    private static String p = "  DisplayCarActivityInfo  ";

    private Button deleteButton;
    private long currentDaoId;
    private CountdownView countDownInsurance;
    private CountdownView countDownTechnical;
    private final long milis = 18000000;

    TextView textViewInsuranceStart;
    TextView textViewTechnicalStart;
    TextView carName;
    TextView carModel;
    TextView carRegNum;
    CarDao carDao;
    Car currentObject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_car_info);
        carDao = initializeDaoSession();
        Intent intent = getIntent();
        //carName.setText(intent.getStringExtra("car_name"));
        currentDaoId = intent.getLongExtra("car_id", 100000);  //TODO: Need to add exemption handling when the value is 100000;

        Log.d(p, "current dao ID :  " + currentDaoId);
        Log.d(p, "current object :  " + currentObject);
        textViewInsuranceStart = (TextView) findViewById(R.id.insurance_date_textView_id);
        textViewTechnicalStart = (TextView) findViewById(R.id.technical_date_textView_id);
        countDownTechnical = (CountdownView) findViewById(R.id.count_down_ins_id);
        countDownInsurance = (CountdownView) findViewById(R.id.count_down_tech_id);
        carName = (TextView) findViewById(R.id.display_car_name);
        carModel = (TextView) findViewById(R.id.display_car_model);
        carRegNum = (TextView) findViewById(R.id.insurance);
        deleteButton = (Button) findViewById(R.id.displ_button_delete_id);
        DateTimeFormatter fmt = DateTimeFormat.forPattern("d MMMM, yyyy");
        carModel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DisplayCarInfoActivity.this, "TEST", Toast.LENGTH_SHORT).show();
            }
        });


        currentObject = carDao.load(currentDaoId);

        if (currentObject == null) Log.d("DisplayActivity", "Curent object is null");
        else {
            Log.d("DisplayCarActivity", "current object isn't null" + currentObject.toString());
            Log.d("DisplayCarActivity  ", " current object brand "+currentObject.getBrand());
            Log.d("DisplayCarActivity  ", " current object model "+currentObject.getModel());
            //Log.d("")
            carName.setText(currentObject.getBrand());
            carModel.setText(currentObject.getModel());
            carRegNum.setText(currentObject.getRegNum());
            textViewInsuranceStart.setText(new DateTime(currentObject.getDateOfInsuranceStart()).toString(fmt));
            textViewTechnicalStart.setText(new DateTime(currentObject.getDateOfTechStart()).toString(fmt));
        }


        Log.d("Display car acti ", "  !!!!!!!!!Powinno sie uruchomic display car activity!!!!!!!!!!!!!!");


        countDownInsurance.start(currentObject.getDateOfInsuranceEnd() - DateTime.now().getMillis());
        countDownTechnical.start(currentObject.getDateOfTechEnd() - DateTime.now().getMillis());

        Log.d("DisplayCar", "date of insurance end: "+ currentObject.getDateOfInsuranceEnd() +" now it's :" + DateTime.now().getMillis());

        DateTime insEnd = new DateTime(currentObject.getDateOfInsuranceEnd());
        DateTime now = new DateTime(DateTime.now());
        Log.d("DisplayCar", "date of insurance end: "+ insEnd.toString(fmt) +" now it's :" + now.toString(fmt));
        Log.d("DisplayCar", "Difference is : " +(currentObject.getDateOfInsuranceEnd()-DateTime.now().getMillis()));


        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayCarInfoActivity.this, MainActivity.class);
                intent.putExtra("currentDaoId", currentDaoId);
                setResult(RESULT_OK, intent);
                Log.d("DisplayCarActivity", "Delete button clicked with id to delete = " + currentDaoId);
                carDao.deleteByKey(currentDaoId);
                startActivity(intent);
                finish();
            }
        });
    }

    public CarDao initializeDaoSession() {
        DaoSession daoSession = ((CarApplication) getApplicationContext()).getDaoSession();
        CarDao carDao = daoSession.getCarDao();
        return carDao;
    }


}
