package com.example.lance.navigationbar;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by Lance on 2/5/2017.
 */

public class ChangePasscode extends Fragment implements View.OnClickListener
{
    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        myView = inflater.inflate(R.layout.change_passcode, container, false);
        Button b = (Button) myView.findViewById(R.id.changePasscodeButton);
        b.setOnClickListener(this);
        return myView;
    }

    public void onClick(View view) {
        EditText passcode = (EditText) getActivity().findViewById(R.id.Passcode);
        passcode.setOnClickListener(this);

        if (passcode.getText().toString().trim().equals("")) {
            Toast.makeText(getActivity(),
                    "Field Required",
                    Toast.LENGTH_SHORT).show();
        } else if (passcode.getText().toString().length() < 4 || passcode.getText().toString().length() > 6) {
            Toast.makeText(getActivity(),
                    "Must input 4-6 digits",
                    Toast.LENGTH_SHORT).show();
        } else {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("passcode", passcode.getText().toString().trim());
            editor.commit();
            Toast.makeText(getActivity(),
                    "Passcode has been successfully changed!",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
