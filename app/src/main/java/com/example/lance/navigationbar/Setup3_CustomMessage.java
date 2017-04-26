package com.example.lance.navigationbar;

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

public class Setup3_CustomMessage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup_pg3);
    }

    public void sendToNextPage3 (View buttonControl)
    {
        EditText customMessage =(EditText) findViewById(R.id.customMessage);

        if( customMessage.getText().toString().trim().equals("")) {
            Toast.makeText(getApplicationContext(),
                    "Field Required",
                    Toast.LENGTH_SHORT).show();
        }
        else if (customMessage.getText().toString().length() > 140) {
            Toast.makeText(getApplicationContext(),
                    "Maximum of 140 characters only.",
                    Toast.LENGTH_SHORT).show();
        }else
        {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("customMessage", customMessage.getText().toString().trim());
            editor.commit();

            Intent nextSetup = new Intent(this, Setup4_MediaType.class);
            startActivity(nextSetup);
        }
    }

    public void backToSetup2 (View buttonControl)
    {
        Intent backSetup = new Intent (this, Setup2_Number.class);
        startActivity(backSetup);
    }

    @Override
    public void onBackPressed() {
        return; //Do nothing!
    }

}