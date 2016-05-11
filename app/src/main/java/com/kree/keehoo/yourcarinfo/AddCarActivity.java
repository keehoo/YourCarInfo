package com.kree.keehoo.yourcarinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddCarActivity extends AppCompatActivity {


    EditText carBrand;
    EditText carModel;
    EditText regNum;
    Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        Log.d("AddCarActivity", " Sprawdzam czy onCreate został wywolany tutaj przy dodawaniu samochodu");

        carBrand = (EditText) findViewById(R.id.car_brand_editText_id);
        carModel = (EditText) findViewById(R.id.car_model_editText_id);
        regNum = (EditText) findViewById(R.id.car_registration_number_editText_id);
        sendButton = (Button) findViewById(R.id.sendButton_id);

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
                //Car car = new Car(carBrand, carModel, regNum);  // this doesn't work due to lack of construtor, there's no point creating one, because the GreenDao takes care of that, inlcuding assigning id
                //startActivity(intent);
                finish();
            }
        });


        //DaoSession daoSession = ((CarApplication) getApplicationContext()).getDaoSession();
        //CarDao carDao = daoSession.getCarDao();
    }


}
