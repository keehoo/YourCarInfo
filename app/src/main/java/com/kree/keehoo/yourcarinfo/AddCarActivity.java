package com.kree.keehoo.yourcarinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.Calendar;
import java.util.Date;

public class AddCarActivity extends AppCompatActivity {


    EditText carBrand;
    EditText carModel;
    EditText regNum;
    Button sendButton;
    Button insuranceButton;
    Button technicalButton;
    DatePicker dpInsurance;
    LinearLayout lyMain;


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
        dpInsurance = (DatePicker) findViewById(R.id.date_picker_insurance_id);
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
            @Override
            public void onClick(View v) {
                lyMain.setVisibility(View.GONE);
                dpInsurance.setVisibility(View.VISIBLE);
                getDateFromDatePicket(dpInsurance);
                lyMain.setVisibility(View.VISIBLE);
            }
        });


    }
    public long getDateFromDatePicket(DatePicker datePicker) {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTimeInMillis();
    }


}
