package com.example.lance.navigationbar;

import android.app.Fragment;
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

public class ChangeIncorrectTries extends Fragment implements View.OnClickListener
{
    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        myView = inflater.inflate(R.layout.change_incorrect_tries, container, false);

        Button b = (Button) myView.findViewById(R.id.changeIncorrectTriesButton);
        b.setOnClickListener(this);
        return myView;
    }

    public void onClick(View view) {
        EditText incorrectTries = (EditText) getActivity().findViewById(R.id.changeIncorrectTries);
        incorrectTries.setOnClickListener(this);

        if (incorrectTries.getText().toString().trim().equals("")) {
            Toast.makeText(getActivity(),
                    "Field Required",
                    Toast.LENGTH_SHORT).show();
        } else {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("tries", incorrectTries.getText().toString().trim());
            editor.commit();
            Toast.makeText(getActivity(),
                    "The number of incorrect tries required to activate the application has been successfully changed!",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
