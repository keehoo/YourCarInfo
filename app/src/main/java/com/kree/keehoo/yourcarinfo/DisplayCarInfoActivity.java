package com.kree.keehoo.yourcarinfo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.DateTimeKeyListener;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.iwgang.countdownview.CountdownView;

public class DisplayCarInfoActivity extends AppCompatActivity {

    private static String p = "  DisplayCarActivityInfo  ";
    private TextView carName;
    private Button deleteButton;
    private long currentDaoId;
    private CountdownView countDownInsurance;
    private CountdownView countDownTechnical;
    private final long milis = 18000000;
    DateTime dateTimeInsuranceStart;
    DateTime dateTimeTechnicalStart;
    DateTime dateTimeTechnicalStop;
    DateTime dateTimeInsuranceStop;
    TextView textViewInsuranceStart;
    TextView textViewTechnicalStart;
    CarDao carDao;
    Car currentObject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_car_info);
        Intent intent = getIntent();
        //carName.setText(intent.getStringExtra("car_name"));
        currentDaoId = intent.getLongExtra("car_id", 100000);
        Log.d(p, "current dao ID :  "+currentDaoId);
        Log.d(p, "current object :  "+currentObject);


        //initializeDaoSession();

        carDao = initializeDaoSession();

        //currentObject = carDao.load(currentDaoId);
        //Log.d(p, ""+currentObject.getBrand().toString());


        


        textViewInsuranceStart = (TextView) findViewById(R.id.insurance_date_textView_id);
        textViewTechnicalStart = (TextView) findViewById(R.id.technical_date_textView_id);
        countDownTechnical = (CountdownView) findViewById(R.id.count_down_ins_id);
        countDownInsurance = (CountdownView) findViewById(R.id.count_down_tech_id);
        carName = (TextView) findViewById(R.id.display_car_name);
        deleteButton = (Button) findViewById(R.id.displ_button_delete_id);


        Log.d("Display car acti ", "  !!!!!!!!!Powinno sie uruchomic display car activity!!!!!!!!!!!!!!");


        countDownInsurance.start(milis * 2); // Millisecond
        countDownTechnical.start(milis + 10000);  //TODO: this needs to be the proper milisecond value - end of insurance (minus) start of insurance;


        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayCarInfoActivity.this, MainActivity.class);
                intent.putExtra("currentDaoId", currentDaoId);
                setResult(RESULT_OK, intent);
                Log.d("DisplayCarActivity", "Delete button clicked with id to delete = "+currentDaoId);
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

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(p, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(p, "-----onRESUME!!!");
    }
}
