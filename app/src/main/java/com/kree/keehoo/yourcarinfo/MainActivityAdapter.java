package com.kree.keehoo.yourcarinfo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.Holder> {

    List<Car> mData;

    LayoutInflater layoutInflater;

    public MainActivityAdapter(Context context, List<Car> mData) {
        this.mData = mData;
        this.layoutInflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MainActivityAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        // ON CREATE WIDOKU Z XML-a - tworzymy obiekt V i inflatujemy tam layout itemu z XML-a - jednego wiersza, potem uzupelnimy ten widok wartosciami, które przechowuje holder
        View v = layoutInflater.inflate(R.layout.recycler_view_item, parent, false);
        return new Holder(v, this);  // parametrami są widok oraz adapter (this);
    }

    @Override
    public void onBindViewHolder(MainActivityAdapter.Holder holder, int position) {

        // teraz tutaj uzupelnimy uprzednio utowrzony widok wartosciami, które przechowuje holder (nad dole);
        Car car = mData.get(position);  // pobieramy dane dla okreslonej pozycji - okresleniem pozycji zajmie sie recyclerview (chyab);

        holder.brand.setText(car.getBrand());
        holder.model.setText(car.getModel());
        holder.regNum.setText(car.getRegNum());
    }

    @Override
    public int getItemCount() {
        return mData.size();  // zwraca ile jest elementów na liscie mData;
    }



public class Holder extends RecyclerView.ViewHolder {

    TextView model;
    TextView brand;
    TextView regNum;
    MainActivityAdapter adapter;

    public Holder(View itemView, MainActivityAdapter adapter) {
        super(itemView);

        this.adapter = adapter;
        model = (TextView) itemView.findViewById(R.id.recycler_view_item_model_id);
        brand = (TextView) itemView.findViewById(R.id.recycler_view_item_brand_id);
        regNum = (TextView) itemView.findViewById(R.id.recycler_view_item_regNum_id);


    }
}}


