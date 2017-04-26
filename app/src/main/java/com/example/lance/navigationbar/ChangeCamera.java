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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by Lance on 2/5/2017.
 */

public class ChangeCamera extends Fragment implements View.OnClickListener
{
    View myView;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button btnDisplay;
    public String cameraType = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        myView = inflater.inflate(R.layout.change_camera, container, false);
        Button b = (Button) myView.findViewById(R.id.changeCameraSettingsButton);
        b.setOnClickListener(this);
        return myView;
    }

    @Override
    public void onClick(View view) {
        radioGroup = (RadioGroup) getView().findViewById(R.id.radioGrpChangeCamera);

        // get selected radio button from radioGroup
        int selectedId = radioGroup.getCheckedRadioButtonId();
        // find the radio button by returned id

        if (selectedId == -1) {
            Toast.makeText(getActivity(),
                    "You have to choose one.",
                    Toast.LENGTH_SHORT).show();
        } else {
            radioButton = (RadioButton) getView().findViewById(selectedId);

            if (radioButton.getText().equals("Front")) {
                cameraType = "Front";
            } else if (radioButton.getText().equals("Back")) {
                cameraType = "Back";
            }
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("cameraType", cameraType);
            editor.commit();

            Toast.makeText(getActivity(),
                    "The camera used has been successfully changed!",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
