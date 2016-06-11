package com.kree.keehoo.yourcarinfo.Activities;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.kree.keehoo.yourcarinfo.DaoGeneratedFiles.Car;
import com.kree.keehoo.yourcarinfo.DaoGeneratedFiles.CarApplication;
import com.kree.keehoo.yourcarinfo.DaoGeneratedFiles.CarDao;
import com.kree.keehoo.yourcarinfo.DaoGeneratedFiles.DaoSession;
import com.kree.keehoo.yourcarinfo.DialogFragments.EditDatesAndDurationFragment;
import com.kree.keehoo.yourcarinfo.DialogFragments.EditFieldFragment;
import com.kree.keehoo.yourcarinfo.DialogFragments.NumberPickerFragment;
import com.kree.keehoo.yourcarinfo.R;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import cn.iwgang.countdownview.CountdownView;

public class DisplayCarInfoActivity extends AppCompatActivity {

    private static String p = "  DisplayCarActivityInfo  ";

    private Button deleteButton;
    private Button editButton;
    private CountdownView countDownInsurance;
    private CountdownView countDownTechnical;
    EditFieldFragment fragString;

    private TextView textViewInsuranceStart;
    private TextView textViewTechnicalStart;
    private TextView carName;
    private TextView carModel;
    private TextView carRegNum;
    private CarDao carDao;
    private Car currentObject;

    private long currentDaoId;

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
        editButton = (Button) findViewById(R.id.edit_button_id);
        DateTimeFormatter fmt = DateTimeFormat.forPattern("d MMMM, yyyy");


        currentObject = carDao.load(currentDaoId);

        if (currentObject == null) Log.d("DisplayActivity", "Curent object is null");
        else {
            Log.d("DisplayCarActivity", "current object isn't null" + currentObject.toString());
            Log.d("DisplayCarActivity  ", " current object brand " + currentObject.getBrand());
            Log.d("DisplayCarActivity  ", " current object model " + currentObject.getModel());
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

        Log.d("DisplayCar", "date of insurance end: " + currentObject.getDateOfInsuranceEnd() + " now it's :" + DateTime.now().getMillis());

        DateTime insEnd = new DateTime(currentObject.getDateOfInsuranceEnd());
        DateTime now = new DateTime(DateTime.now());
        Log.d("DisplayCar", "date of insurance end: " + insEnd.toString(fmt) + " now it's :" + now.toString(fmt));
        Log.d("DisplayCar", "Difference is : " + (currentObject.getDateOfInsuranceEnd() - DateTime.now().getMillis()));


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

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allowEdit();

            }
        });
    }

    public CarDao initializeDaoSession() {
        DaoSession daoSession = ((CarApplication) getApplicationContext()).getDaoSession();
        CarDao carDao = daoSession.getCarDao();
        return carDao;
    }

    private void declineEdit() {
        carName.setOnClickListener(null);
        carModel.setOnClickListener(null);
        carRegNum.setOnClickListener(null);
        countDownTechnical.setOnClickListener(null);
        countDownInsurance.setOnClickListener(null);

    }


    private void allowEdit2(View view) {

    }


    private void allowEdit() {


        Toast.makeText(DisplayCarInfoActivity.this, "Klikanie dozwolone", Toast.LENGTH_SHORT).show();
        carName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(DisplayCarInfoActivity.this, "Car name clicked: ", Toast.LENGTH_SHORT).show();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                fragString = EditFieldFragment.newInstance(DisplayCarInfoActivity.this, "carBrand");
                fragString.show(ft, "carBrand");
                declineEdit();
            }
        });

        carModel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                fragString = EditFieldFragment.newInstance(DisplayCarInfoActivity.this, "carModel");
                fragString.show(ft, "carModel");
                declineEdit();
            }
        });

        carRegNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                fragString = EditFieldFragment.newInstance(DisplayCarInfoActivity.this, "carRegNum");

                /// this argument assigning should most probably be done with hashtag or hashcde etc... need to investigate further with these.
                fragString.show(ft, "carRegNum");
                declineEdit();
            }
        });

        countDownInsurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                EditDatesAndDurationFragment fragString = EditDatesAndDurationFragment.newInstance(DisplayCarInfoActivity.this, "insuranceDateStart");
                /// this argument assigning should most probably be done with hashtag or hashcde etc... need to investigate further with these.
                fragString.show(ft, "insuranceDateStart");
                declineEdit();
            }
        });

        countDownTechnical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                EditDatesAndDurationFragment fragString = EditDatesAndDurationFragment.newInstance(DisplayCarInfoActivity.this, "technicalDateStart");
                fragString.show(ft, "technicalDateStart");
                declineEdit();
            }
        });


    }


   /* public interface EditFieldListener() {
        public void doSomething();
    }*/


    public void onUserSelectValue(String text, String string) {
        Toast.makeText(DisplayCarInfoActivity.this, "Wybrano.....  " + text + "A dodatkowo przekazano argument...." + string, Toast.LENGTH_SHORT).show();
        updateInformation(text, string);  // TODO: These variables names MUST be renamed and refactored.!! This method is pointlless anyways...
    }

    public void updateInformation(String newValue, String currentFieldTag) {


        if (currentFieldTag == "carBrand") {
            currentObject.setBrand(newValue);

            Log.d(p, "Current Object Brand value:  " + currentObject.getBrand());
            CarDao carDao = initializeDaoSession();
            carDao.insertOrReplace(currentObject);
            carName.setText(currentObject.getBrand());
        }
        if (currentFieldTag == "carModel") {
            currentObject.setModel(newValue);
            Log.d(p, "Current Object Brand value:  " + currentObject.getModel());
            CarDao carDao = initializeDaoSession();
            carDao.insertOrReplace(currentObject);
            carModel.setText(currentObject.getBrand());
        }
        if (currentFieldTag == "carRegNum") {
            currentObject.setRegNum(newValue);
            Log.d(p, "Current Object Brand value:  " + currentObject.getRegNum());
            CarDao carDao = initializeDaoSession();
            carDao.insertOrReplace(currentObject);
            carRegNum.setText(currentObject.getRegNum());
        }
    }

    public void onUserSelectValueDates(Long date, int durationMonths, String prop) {

        //Toast.makeText(DisplayCarInfoActivity.this, "Long przesłany: " + date + " |||||  int przesłany:" + durationMonths, Toast.LENGTH_SHORT).show();
        if (prop == "insuranceDateStart") {
            currentObject.setDateOfInsuranceStart(date);
            DateTime endDate = (new DateTime(date)).plusMonths(durationMonths);
            currentObject.setDateOfInsuranceEnd(endDate.getMillis());
            CarDao cd = initializeDaoSession();
            cd.insertOrReplace(currentObject);
            countDownInsurance.start(currentObject.getDateOfInsuranceEnd() - DateTime.now().getMillis());
        }

        if (prop == "technicalDateStart") {
            currentObject.setDateOfTechStart(date);
            DateTime endDate = (new DateTime(date)).plusMonths(durationMonths);
            currentObject.setDateOfTechEnd(endDate.getMillis());
            CarDao cd = initializeDaoSession();
            cd.insertOrReplace(currentObject);
            countDownTechnical.start(currentObject.getDateOfInsuranceEnd() - DateTime.now().getMillis());
        }


    }
}
