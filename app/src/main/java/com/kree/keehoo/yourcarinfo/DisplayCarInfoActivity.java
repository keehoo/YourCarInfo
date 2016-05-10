package com.kree.keehoo.yourcarinfo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DisplayCarInfoActivity extends AppCompatActivity {


    private TextView carName;
    private Button deleteButton;
    private Long id_to_be_deleted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_car_info);

        carName = (TextView) findViewById(R.id.display_car_name);
        deleteButton = (Button)findViewById(R.id.displ_button_delete_id);

        Intent intent = getIntent();
        carName.setText(intent.getStringExtra("car_name"));
        id_to_be_deleted = intent.getLongExtra("car_id", 100000);


        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayCarInfoActivity.this, MainActivity.class);
                intent.putExtra("id_to_be_deleted", id_to_be_deleted);
                setResult(RESULT_OK, intent);
                //Car car = new Car(carBrand, carModel, regNum);  // this doesn't work due to lack of construtor, there's no point creating one, because the GreenDao takes care of that, inlcuding assigning id
                //startActivity(intent);
                finish();

            }
        });



    }
}
