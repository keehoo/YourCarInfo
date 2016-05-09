package com.kree.keehoo.yourcarinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
                intent.putExtra("ins_start_long", 100000);  // DUMMY LONG of ins start;
                intent.putExtra("ins_stop_long", 190000);   // DUMMY data
                intent.putExtra("tech_start_long", 1000000);  //DUMMY
                intent.putExtra("tech_stop_long", 190000); // DUMMY


                //Car car = new Car(carBrand, carModel, regNum);  // this doesn't work due to lack of construtor, there's no point creating one, because the GreenDao takes care of that, inlcuding assigning id
                startActivity(intent);
            }
        });


        //DaoSession daoSession = ((CarApplication) getApplicationContext()).getDaoSession();
        //CarDao carDao = daoSession.getCarDao();
    }


}
