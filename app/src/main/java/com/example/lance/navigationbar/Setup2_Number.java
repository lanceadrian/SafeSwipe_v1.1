package com.example.lance.navigationbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Lance on 3/12/2017.
 */

public class Setup2_Number extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup_pg2);
    }

    public void sendToNextPage2 (View buttonControl)
    {
        EditText number =(EditText) findViewById(R.id.number);

        if( number.getText().toString().trim().equals("")) {
            Toast.makeText(getApplicationContext(),
                    "Field Required",
                    Toast.LENGTH_SHORT).show();
        }
        else if (number.getText().toString().length() != 11) {
            Toast.makeText(getApplicationContext(),
                    "Must input 11 numbers",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("number", number.getText().toString().trim());
            editor.commit();

            Intent nextSetup = new Intent(this, Setup3_CustomMessage.class);
            startActivity(nextSetup);
        }
    }

    public static String getDefaults(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }

    public void backToSetup (View buttonControl)
    {
        Intent backSetup = new Intent (this, Setup1_Intro.class);
        startActivity(backSetup);
    }

    @Override
    public void onBackPressed() {
        return; //Do nothing!
    }
}