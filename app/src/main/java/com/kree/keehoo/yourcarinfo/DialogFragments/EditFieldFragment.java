package com.kree.keehoo.yourcarinfo.DialogFragments;


import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.kree.keehoo.yourcarinfo.Activities.DisplayCarInfoActivity;

public class EditFieldFragment extends DialogFragment {

    static Context mContext;
    static String text;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("asdasd", "asdasdas");
        final String string = getArguments().getString("title");
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

                        callingActivity.onUserSelectValue(text, string);
                        dismiss();
                    }
                });

        params.gravity = Gravity.CENTER_HORIZONTAL;
        okButton.setLayoutParams(params);
        okButton.setText("Done");

        linLayoutV.addView(okButton);
        return linLayoutV;
    }

    public static EditFieldFragment newInstance(Context context, String prop) {
        EditFieldFragment dialog = new EditFieldFragment();
        Log.d("NumberPickerFragment", "New instance method called inside the NumberPickerFragment");
        mContext = context;
        Bundle args = new Bundle();
        args.putString("title", prop);
        dialog.setArguments(args);
        return dialog;
    }
}

