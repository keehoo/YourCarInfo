package com.kree.keehoo.yourcarinfo.DialogFragments;


import android.app.DialogFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

import com.kree.keehoo.yourcarinfo.Activities.AddCarActivity;
import com.kree.keehoo.yourcarinfo.Activities.DisplayCarInfoActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditFieldFragment extends DialogFragment {

    static Context mContext;
    //static DisplayCarInfoActivity.EditFieldListener mListener;
    public static final String TAG = "EditFieldFragment";
    static String text;
    int insurance;
    public SharedPreferences sharedPreferences;

   /* public NumberPickerFragment() {
        // Required empty public constructor
    }*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //sharedPreferences = getActivity().getSharedPreferences(AddCarActivity.SHARED_PREFS_NAME, Context.MODE_PRIVATE);

        //TextView textView = new TextView(getActivity());
        //textView.setText("TEXT");
        //textView.setTextColor(124);

        LinearLayout linLayoutH =
                new LinearLayout(getActivity());
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
        linLayoutH.setLayoutParams(params);
        final EditText editText =
                new EditText(getActivity());

        linLayoutH.addView(editText);
        LinearLayout linLayoutV =
                new LinearLayout(getActivity());
        linLayoutV.setOrientation(LinearLayout.VERTICAL);
        linLayoutV.addView(linLayoutH);

        Button okButton = new Button(getActivity());

        okButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        text = editText.getText().toString();
                        DisplayCarInfoActivity callingActivity = (DisplayCarInfoActivity) getActivity();
                        callingActivity.onUserSelectValue(text);
                        dismiss();
                    }
                });

        params.gravity = Gravity.CENTER_HORIZONTAL;
        okButton.setLayoutParams(params);
        okButton.setText("Done");

        linLayoutV.addView(okButton);
        return linLayoutV;
    }

    public static EditFieldFragment newInstance(Context context, int insurance) {
        EditFieldFragment dialog = new EditFieldFragment();
        Log.d("NumberPickerFragment", "New instance method called inside the NumberPickerFragment");
        mContext = context;

        //mListener = listener;

        //Calendar c = Calendar.getInstance();

        /*I dont really see the purpose of the below*/
        Bundle args = new Bundle();
        args.putString("title", "Set Date");
        args.putInt("insurance", insurance);
        dialog.setArguments(args);//setArguments can only be called before fragment is attached to an activity, so right after the fragment is created
        return dialog;
    }

}

