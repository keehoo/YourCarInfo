package com.kree.keehoo.yourcarinfo;

import android.app.DatePickerDialog;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.Calendar;
import java.util.Date;

public class AddCarActivity extends FragmentActivity {


    EditText carBrand;
    EditText carModel;
    EditText regNum;
    Button sendButton;
    Button insuranceButton;
    Button technicalButton;
    DatePicker dpInsurance;
    LinearLayout lyMain;

    DatePickerFragment_Insurance frag;
    Button button;
    Calendar now;



    long dateOfInsuranceStart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        Log.d("AddCarActivity", " Sprawdzam czy onCreate został wywolany tutaj przy dodawaniu samochodu");

        carBrand = (EditText) findViewById(R.id.car_brand_editText_id);
        lyMain = (LinearLayout)findViewById(R.id.linear_layout_main_info_id);
        carModel = (EditText) findViewById(R.id.car_model_editText_id);
        regNum = (EditText) findViewById(R.id.car_registration_number_editText_id);
        sendButton = (Button) findViewById(R.id.sendButton_id);
        //dpInsurance = (DatePicker) findViewById(R.id.date_picker_insurance_id);
        insuranceButton = (Button) findViewById(R.id.pick_insurance_start_date_button_id);


        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddCarActivity.this, MainActivity.class);
                intent.putExtra("carBrand", carBrand.getText().toString());
                intent.putExtra("carModel", carModel.getText().toString());
                intent.putExtra("regNum", regNum.getText().toString());
                intent.putExtra("ins_start_long", 100000l);  // DUMMY LONG of ins start;
                intent.putExtra("ins_stop_long", 190000l);   // DUMMY data
                intent.putExtra("tech_start_long", 1000000l);  //DUMMY
                intent.putExtra("tech_stop_long", 190000l); // DUMMY
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        insuranceButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("Main Activity", "Button ON CLICK!!!");
                showDialog();
            }
        });


    }


    public void showDialog() {
        Log.d("Main Activity", " Show Dialog method called!!!");
        FragmentTransaction ft = getFragmentManager().beginTransaction(); //get the fragment
        frag = DatePickerFragment_Insurance.newInstance(this, new DateDialogFragmentListener() {   // TO TUTAJ POJAWIA SIE NOWA INSTAJC EINTERFEJSU????
            public void updateChangedDate(int year, int month, int day) {
                button.setText(String.valueOf(month + 1) + "-" + String.valueOf(day) + "-" + String.valueOf(year));
                now.set(year, month, day);
            }
        }, now);

        frag.show(ft, "DateDialogFragment");

    }

    public interface DateDialogFragmentListener {
        //this interface is a listener between the Date Dialog fragment and the activity to update the buttons date
        void updateChangedDate(int year, int month, int day);
    }


    public long getDateOfInsuranceStart() {
        return dateOfInsuranceStart;
    }

    public void setDateOfInsuranceStart(long dateOfInsuranceStart) {
        this.dateOfInsuranceStart = dateOfInsuranceStart;
    }
}
