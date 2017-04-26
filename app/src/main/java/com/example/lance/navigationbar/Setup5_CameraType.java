package com.example.lance.navigationbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by Lance on 3/12/2017.
 */

public class Setup5_CameraType extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button btnDisplay;
    public String cameraType = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup_pg5);
    }

    public void sendToNextPage5 (View buttonControl) {
        radioGroup = (RadioGroup) findViewById(R.id.setup5radiogrp);
        btnDisplay = (Button) findViewById(R.id.cameraButton);

        // get selected radio button from radioGroup
        int selectedId = radioGroup.getCheckedRadioButtonId();
        // find the radio button by returned id

        if (selectedId == -1) {
            Toast.makeText(getApplicationContext(),
                    "You have to choose one.",
                    Toast.LENGTH_SHORT).show();
        } else {
            radioButton = (RadioButton) findViewById(selectedId);

            if (radioButton.getText().equals("Front")) {
                cameraType = "Front";
                Toast.makeText(getApplicationContext(),
                        cameraType,
                        Toast.LENGTH_SHORT).show();
            } else if (radioButton.getText().equals("Back")) {
                cameraType = "Back";
                Toast.makeText(getApplicationContext(),
                        cameraType,
                        Toast.LENGTH_SHORT).show();
            }
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("cameraType", cameraType);
            editor.commit();
            Intent nextSetup = new Intent(this, Setup6_Passcode.class);
            startActivity(nextSetup);
        }
    }

    public void backToSetup4 (View buttonControl)
    {
        Intent backSetup = new Intent (this, Setup4_MediaType.class);
        startActivity(backSetup);
    }
}