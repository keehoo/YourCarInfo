package com.kree.keehoo.yourcarinfo;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by keehoo on 17.05.2016.
 */
public class DatePickerFragment extends DialogFragment {


    public static String TAG = "DateDialogFragment";
    static Context mContext; //I guess hold the context that called it. Needed when making a DatePickerDialog. I guess its needed when conncting the fragment with the context
    static int mYear;
    static int mMonth;
    static int mDay;
    static AddCarActivity.DateDialogFragmentListener mListener;

    public static DatePickerFragment newInstance(Context context, AddCarActivity.DateDialogFragmentListener listener, Calendar now) {
        DatePickerFragment dialog = new DatePickerFragment();
        Log.d(TAG, "New instance method called inside the " + TAG);
        mContext = context;
        mListener = listener;
        //Calendar c = Calendar.getInstance();

        mYear = now.get(Calendar.YEAR);
        mMonth = now.get(Calendar.MONTH);
        mDay = now.get(Calendar.DAY_OF_MONTH);
        /*I dont really see the purpose of the below*/
        Bundle args = new Bundle();
        args.putString("title", "Set Date");
        dialog.setArguments(args);//setArguments can only be called before fragment is attached to an activity, so right after the fragment is created


        return dialog;
    }


    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.d(TAG, "   onCreate Dialog  ---- returning DatePickerDialog with conetxt" + mContext.toString() + " mDateSetListener!= null " +
                +mYear + " " + mMonth + " " + mDay);
        return new DatePickerDialog(mContext, mDateSetListener, mYear, mMonth, mDay);

    }


    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            Log.d(TAG, "     onDateSet");
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;

            mListener.updateChangedDate(year, monthOfYear, dayOfMonth);
        }
    };


}
