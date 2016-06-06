package com.kree.keehoo.yourcarinfo;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Calendar;

public class AddCarActivity extends FragmentActivity {


    EditText carBrand;
    EditText carModel;
    EditText regNum;
    Button sendButton;
    Button insuranceButton;
    Button technicalButton;
    Button insuranceDurationButton;
    Button technicalDurationButton;
    LinearLayout lyMain;
    DatePickerFragment frag;
    NumberPickerFragment fragNumber;
    Calendar now;


    long dateOfInsuranceStart;
    long insuranceDuration;
    long dateOfInsuranceStop;
    long dateOfTechnicalStart;
    long technicalDuration;
    long dateOfTechnicalStop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        Log.d("AddCarActivity", " Sprawdzam czy onCreate został wywolany tutaj przy dodawaniu samochodu");
        now = Calendar.getInstance();
        carBrand = (EditText) findViewById(R.id.car_brand_editText_id);
        lyMain = (LinearLayout) findViewById(R.id.linear_layout_main_info_id);
        carModel = (EditText) findViewById(R.id.car_model_editText_id);
        regNum = (EditText) findViewById(R.id.car_registration_number_editText_id);
        sendButton = (Button) findViewById(R.id.sendButton_id);
        //dpInsurance = (DatePicker) findViewById(R.id.date_picker_insurance_id);
        insuranceButton = (Button) findViewById(R.id.pick_insurance_start_date_button_id);
        technicalButton = (Button) findViewById(R.id.pick_tec_start_date_button_id);
        insuranceDurationButton = (Button) findViewById(R.id.insurance_duration_button_id);
        technicalDurationButton = (Button) findViewById(R.id.technical_duration_button_id);


        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddCarActivity.this, MainActivity.class);
                intent.putExtra("carBrand", carBrand.getText().toString());
                intent.putExtra("carModel", carModel.getText().toString());
                intent.putExtra("regNum", regNum.getText().toString());
                intent.putExtra("ins_start_long", getDateOfInsuranceStart());
                Log.d("GetDateOfInsurance", ""+getDateOfInsuranceStart());

                intent.putExtra("ins_stop_long", 100000L); //DUMMY

                Log.d("getDateOfTechnical", ""+getDateOfTechnicalStart());

                intent.putExtra("tech_start_long", getDateOfTechnicalStart());
                intent.putExtra("tech_stop_long", 190000l); // DUMMY
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        insuranceButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("Main AddCarActivity", "Button ON CLICK!!!");
                showDialog();
            }
        });

        technicalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("AddCarActivity", " Button for technical date clicked!");
                showDialogTechnical();
            }
        });

        insuranceDurationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNumberPicker();
            }
        });






    }

    public void showDialogTechnical() {
        Log.d("Main Activity", " Show Dialog method called!!!");
        FragmentTransaction ft = getFragmentManager().beginTransaction(); //get the fragment
        frag = DatePickerFragment.newInstance(this,
                new DateDialogFragmentListener() {   // TO TUTAJ POJAWIA SIE NOWA INSTAJC EINTERFEJSU????
                    public void updateChangedDate(int year, int month, int day) {
                        Log.d("showDialog", String.valueOf(month + 1) + "-" + String.valueOf(day) + "-" + String.valueOf(year));
                        now.set(year, month, day);
                        setDateOfTechnicalStart(now.getTimeInMillis());
                        Log.d("Data technical ", "  data rozpoczecia przegladu:  "+getDateOfTechnicalStart());

                    }
                },
                now);
        frag.show(ft, "DateDialogFragment");
    }


    public void showNumberPicker() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        fragNumber = NumberPickerFragment.newInstance(this, new NumberPickerFragmentListener() {
            @Override
            public void updateChangeDate(int number) {
                Log.d("show Nber Pcker", " ShowNumberPicker method called!!!!!");

            }
        });
        fragNumber.show(ft, "NumberPickerFragment");
    }

    public void showDialog() {
        Log.d("Main Activity", " Show Dialog method called!!!");
        FragmentTransaction ft = getFragmentManager().beginTransaction(); //get the fragment
        frag = DatePickerFragment.newInstance(this,
                new DateDialogFragmentListener() {   // TO TUTAJ POJAWIA SIE NOWA INSTAJC EINTERFEJSU????
                    public void updateChangedDate(int year, int month, int day) {
                        Log.d("showDialog", String.valueOf(month + 1) + "-" + String.valueOf(day) + "-" + String.valueOf(year));
                        now.set(year, month, day);
                        setDateOfInsuranceStart(now.getTimeInMillis());
                        Log.d("Date of insuwance", "   in millis "+getDateOfInsuranceStart());
                    }
                },
                now);
        frag.show(ft, "DateDialogFragment");
          }

    public interface DateDialogFragmentListener {
        //this interface is a listener between the Date Dialog fragment and the activity to update the buttons date
        void updateChangedDate(int year, int month, int day);
    }

    public interface NumberPickerFragmentListener {
        void updateChangeDate(int number);
    }


    public long getDateOfInsuranceStart() {
        return dateOfInsuranceStart;
    }

    public void setDateOfInsuranceStart(long dateOfInsuranceStart) {
        this.dateOfInsuranceStart = dateOfInsuranceStart;
    }


    public long getDateOfTechnicalStart() {
        return dateOfTechnicalStart;
    }

    public void setDateOfTechnicalStart(long dateOfTechnicalStart) {
        this.dateOfTechnicalStart = dateOfTechnicalStart;
    }

    public void onUserSelectValue(String selectedValue) {

        // TODO add your implementation.
        Toast.makeText(getBaseContext(), ""+ selectedValue, Toast.LENGTH_LONG).show();
    }

}
