package com.example.lance.navigationbar;

import android.app.Fragment;
import android.content.DialogInterface;
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
import android.widget.Toast;

/**
 * Created by Lance on 2/5/2017.
 */

public class ChangeNumber extends Fragment implements View.OnClickListener
{
    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        myView = inflater.inflate(R.layout.change_number, container, false);
        Button b = (Button) myView.findViewById(R.id.changeNumberSettingsButton);
        b.setOnClickListener(this);
        return myView;

    }

    @Override
    public void onClick(View view) {

        EditText number =(EditText) getView().findViewById(R.id.changeNumberSettings);
        number.setOnClickListener(this);

        if( number.getText().toString().trim().equals("")) {
            Toast.makeText(getActivity(),
                    "Field Required",
                    Toast.LENGTH_SHORT).show();
        }
        else if (number.getText().toString().length() != 11) {
            Toast.makeText(getActivity(),
                    "Must input 11 numbers",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("number", number.getText().toString().trim());
            editor.commit();
            Toast.makeText(getActivity(),
                    "Set number has been successfully changed!",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
