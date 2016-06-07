package com.kree.keehoo.yourcarinfo;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import net.danlew.android.joda.JodaTimeAndroid;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Calendar;

public class AddCarActivity extends FragmentActivity {

    public static final String TAG = "AddCarActivity";

    public static final String INSURANCE = "insurance";
    public static final String SHARED_PREFS_NAME = "prefs";
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
    SharedPreferences sharedPreferences;
    Long dateOfInsuranceStop;
    Long dateOfTechnicalStart;


    int technicalDuration;
    Long dateOfTechnicalStop;
    Long dateOfInsuranceStart;


    int insuranceDuration;


    private Long calculateStopLong(Long insStart, int duration) {

        if (insStart == null && duration == 0) {
            Log.d("Same nulle", ".............SAME NULLE>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            Toast.makeText(AddCarActivity.this, "Proszę podać wszystkie dane", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            DateTime dt = new DateTime(insStart);
            DateTimeFormatter fmt = DateTimeFormat.forPattern("d MMMM, yyyy");
            Log.d(TAG, "     DateTime(insStart) = " + dt.toString(fmt));
            dt.plusMonths(duration);
            Log.d(TAG, " DateTime(insStart) + number of months :" + duration + " should equals:     " + (new DateTime(dt.plusMonths(duration)).toString(fmt)));
            return dt.plusMonths(duration).getMillis();
        }
        return 90L;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
        JodaTimeAndroid.init(this);

        sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE);
        Log.d("AddCarActivity", " Sprawdzam czy onCreate został wywolany tutaj przy dodawaniu samochodu");
        now = Calendar.getInstance();
        carBrand = (EditText) findViewById(R.id.car_brand_editText_id);
        lyMain = (LinearLayout) findViewById(R.id.linear_layout_main_info_id);
        carModel = (EditText) findViewById(R.id.car_model_editText_id);
        regNum = (EditText) findViewById(R.id.car_registration_number_editText_id);
        sendButton = (Button) findViewById(R.id.sendButton_id);
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
                Log.d("GetDateOfInsurance", "" + getDateOfInsuranceStart());
                Log.d("Insurance Duration", "Insurance duration   :   " + insuranceDuration);
                intent.putExtra("ins_stop_long", (calculateStopLong(getDateOfInsuranceStart(), insuranceDuration)));
                dateOfInsuranceStop = calculateStopLong(getDateOfInsuranceStart(), insuranceDuration);
                Log.d("getDateOfTechnical", "" + getDateOfTechnicalStart());
                intent.putExtra("tech_start_long", getDateOfTechnicalStart());
                intent.putExtra("tech_stop_long", calculateStopLong(getDateOfTechnicalStart(), technicalDuration));
                Log.d("Technical Duration", "Technical Duration .... " + technicalDuration);
                Log.d("Date of techStart", "Date of Technical Start ..." + (new DateTime(getDateOfTechnicalStart()).toString()));
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

        technicalDurationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNumberPickerTechnical();

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
                        Log.d("Data technical ", "  data rozpoczecia przegladu:  " + getDateOfTechnicalStart());

                    }
                },
                now);
        frag.show(ft, "DateDialogFragment");
    }


    public void showNumberPicker() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        fragNumber = NumberPickerFragment.newInstance(this, 10);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(INSURANCE, 10).apply();

        // 10 - for insurance, 20 for technical;
        fragNumber.show(ft, "NumberPickerFragment");
    }

    public void showNumberPickerTechnical() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        fragNumber = NumberPickerFragment.newInstance(this, 20);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(INSURANCE, 20).apply();
        // 10 - for insurance, 20 for technical;
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
                        Log.d("Date of insuwance", "   in millis " + getDateOfInsuranceStart());
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

    public int getTechnicalDuration() {
        return technicalDuration;
    }

    public void setTechnicalDuration(int technicalDuration) {
        this.technicalDuration = technicalDuration;
    }

    public int getInsuranceDuration() {
        return insuranceDuration;
    }

    public void setInsuranceDuration(int insuranceDuration) {
        this.insuranceDuration = insuranceDuration;
    }

    public Long getDateOfInsuranceStart() {
        return dateOfInsuranceStart;
    }

    public void setDateOfInsuranceStart(long dateOfInsuranceStart) {
        this.dateOfInsuranceStart = dateOfInsuranceStart;
    }


    public Long getDateOfTechnicalStart() {
        return dateOfTechnicalStart;
    }

    public void setDateOfTechnicalStart(long dateOfTechnicalStart) {
        this.dateOfTechnicalStart = dateOfTechnicalStart;
    }

    public void onUserSelectValue(int selectedValue) {
        int insurance = sharedPreferences.getInt(INSURANCE, 90);
        Toast.makeText(AddCarActivity.this, "Selected Value: " + selectedValue, Toast.LENGTH_LONG).show();

        if (insurance == 10) {
            setInsuranceDuration(selectedValue);
            Log.d("onUserSelectValue", selectedValue + "wartosc --INSURANCE-- zczytana z shared prefs = " + sharedPreferences.getInt(INSURANCE, 90));
            Log.d("onUserSelectValue", "wartosc ---insuranceDuration--- ustawoina na " + insuranceDuration);

        }
        if (insurance == 20) {
            setTechnicalDuration(selectedValue);
            Log.d("onUserSelectValue", "wartosc --INSURANCE-- zczytana z shared prefs = " + sharedPreferences.getInt(INSURANCE, 90));
            Log.d("onUserSelectValue", "wartosc ---technicalDuration--- ustawoina na " + technicalDuration);
        }

        if (insurance == 90) {
            Log.d("AddCarActivity", "method onUserSelectValue w shared prefs jest wartosc 90, czyli defaultowa!!!!!!");
            Toast.makeText(AddCarActivity.this, "Błędny kod ubezpieczenia - SharedPreferences - TAG: Insurance = 90 (incorrect, should be 10 for insurance, 20 for technical",
                    Toast.LENGTH_SHORT).show();
        }

    }

}
