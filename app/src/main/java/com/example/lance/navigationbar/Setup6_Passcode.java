package com.example.lance.navigationbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by Lance on 3/12/2017.
 */

public class Setup6_Passcode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup_pg6);
    }

    public void sendToNextPage6 (View buttonControl) {

        EditText passcode =(EditText) findViewById(R.id.Passcode);

        if( passcode.getText().toString().trim().equals("")) {
            Toast.makeText(getApplicationContext(),
                    "Field Required",
                    Toast.LENGTH_SHORT).show();
        }
        else if (passcode.getText().toString().length() < 4 || passcode.getText().toString().length() > 6) {
            Toast.makeText(getApplicationContext(),
                    "Must input 4-6 digits",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("passcode", passcode.getText().toString().trim());
            editor.putString("tries", "2");
            editor.commit();

            Intent nextSetup = new Intent(this, Setup7_Recap.class);
            startActivity(nextSetup);
        }
    }

    public void backToSetup5 (View buttonControl)
    {
        Intent backSetup = new Intent (this, Setup5_CameraType.class);
        startActivity(backSetup);
    }

    @Override
    public void onBackPressed() {
        return; //Do nothing!
    }

}