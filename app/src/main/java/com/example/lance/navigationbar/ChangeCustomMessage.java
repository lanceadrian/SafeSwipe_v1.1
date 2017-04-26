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
import android.widget.Toast;

/**
 * Created by Lance on 2/5/2017.
 */

public class ChangeCustomMessage extends Fragment implements View.OnClickListener
{
    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        myView = inflater.inflate(R.layout.change_custom_message, container, false);
        Button b = (Button) myView.findViewById(R.id.changeCustomMessageButton);
        b.setOnClickListener(this);
        return myView;

    }

    @Override
    public void onClick(View view) {

        EditText number =(EditText) getView().findViewById(R.id.changeCustomMessage);
        number.setOnClickListener(this);

        if( number.getText().toString().trim().equals("")) {
            Toast.makeText(getActivity(),
                    "Field Required",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("customMessage", number.getText().toString());
            editor.commit();
            Toast.makeText(getActivity(),
                    "Custom message has been successfully changed!",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
