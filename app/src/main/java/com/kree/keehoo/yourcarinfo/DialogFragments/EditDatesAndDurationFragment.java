package com.kree.keehoo.yourcarinfo.DialogFragments;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

import com.kree.keehoo.yourcarinfo.Activities.DisplayCarInfoActivity;

import java.util.Calendar;

/**
 * Created by keehoo on 09.06.2016.
 */
public class EditDatesAndDurationFragment extends DialogFragment {

    public static Context mContext;
    private Long date;
    private int durationMonths;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final String string = getArguments().getString("title");
        LinearLayout linLayoutH =
                new LinearLayout(getActivity());
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
        linLayoutH.setLayoutParams(params);
       final DatePicker datePicker = new DatePicker(getActivity());


        linLayoutH.addView(datePicker);
        LinearLayout linLayoutV =
                new LinearLayout(getActivity());
        linLayoutV.setOrientation(LinearLayout.VERTICAL);
        linLayoutV.addView(linLayoutH);

        Button okButton = new Button(getActivity());
        final NumberPicker numberPicker
                 = new NumberPicker(getActivity());
        numberPicker.setMaxValue(24);
        numberPicker.setMinValue(0);
        linLayoutV.addView(numberPicker);


        okButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        durationMonths = numberPicker.getValue();
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
                        date = calendar.getTimeInMillis();
                        DisplayCarInfoActivity callingActivity = (DisplayCarInfoActivity) getActivity();

                        callingActivity.onUserSelectValueDates(date, durationMonths);
                        dismiss();
                    }
                });

        params.gravity = Gravity.CENTER_HORIZONTAL;
        okButton.setLayoutParams(params);
        okButton.setText("Done");

        linLayoutV.addView(okButton);
        return linLayoutV;
    }

    public static EditDatesAndDurationFragment newInstance(Context context, String prop) {
        EditDatesAndDurationFragment dialog = new EditDatesAndDurationFragment();
        Log.d("NumberPickerFragment", "New instance method called inside the NumberPickerFragment");
        mContext = context;
        Bundle args = new Bundle();
        args.putString("title", prop);
        dialog.setArguments(args);
        return dialog;
    }
}
