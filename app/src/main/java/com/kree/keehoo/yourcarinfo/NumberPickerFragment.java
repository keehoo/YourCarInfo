package com.kree.keehoo.yourcarinfo;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class NumberPickerFragment extends DialogFragment {

    static Context mContext;
    static AddCarActivity.NumberPickerFragmentListener mListener;
    public static final String TAG = "NumberPickerFragmentDialog";
    static int number;

   /* public NumberPickerFragment() {
        // Required empty public constructor
    }*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        TextView textView = new TextView(getActivity());
        textView.setText(R.string.hello_blank_fragment);


        LinearLayout linLayoutH =
                new LinearLayout(getActivity());

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
        linLayoutH.setLayoutParams(params);

        final NumberPicker np =
                new NumberPicker(getActivity());
        np.setMaxValue(9);
        np.setMinValue(0);
        linLayoutH.addView(np);
        LinearLayout linLayoutV =
                new LinearLayout(getActivity());
        linLayoutV.setOrientation(LinearLayout.VERTICAL);
        linLayoutV.addView(linLayoutH);

        Button okButton = new Button(getActivity());
        okButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        number = np.getValue();
                        AddCarActivity callingActivity = (AddCarActivity) getActivity();
                        callingActivity.onUserSelectValue("insert selected value here");
                        dismiss();

                    }
                });

        params.gravity = Gravity.CENTER_HORIZONTAL;
        okButton.setLayoutParams(params);
        okButton.setText("Done");

        linLayoutV.addView(okButton);
        return linLayoutV;
    }

    public static NumberPickerFragment newInstance(Context context, AddCarActivity.NumberPickerFragmentListener listener) {
        NumberPickerFragment dialog = new NumberPickerFragment();
        Log.d("NumberPickerFragment", "New instance method called inside the NumberPickerFragment");
        mContext = context;
        mListener = listener;

        //Calendar c = Calendar.getInstance();

        /*I dont really see the purpose of the below*/
        Bundle args = new Bundle();
        args.putString("title", "Set Date");
        dialog.setArguments(args);//setArguments can only be called before fragment is attached to an activity, so right after the fragment is created


        return dialog;
    }

}
