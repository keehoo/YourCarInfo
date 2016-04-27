package com.kree.keehoo.yourcarinfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    public RecyclerView carList; // RECYCLER VIEW!!!!!



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carList = (RecyclerView) findViewById(R.id.carList);


    }
}
