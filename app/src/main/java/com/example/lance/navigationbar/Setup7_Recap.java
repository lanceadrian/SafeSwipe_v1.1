package com.example.lance.navigationbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Lance on 3/12/2017.
 */

public class Setup7_Recap extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup_pg7);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        if (preferences != null) {
            String savedNumber = preferences.getString("number", "");
            String savedCustomMessage = preferences.getString("customMessage", "");
            String savedMediaType = preferences.getString("mediaType", "");
            String savedPasscode = preferences.getString("passcode", "");
            String savedCameraType = preferences.getString("cameraType","");

            TextView recapNumber = (TextView) findViewById(R.id.RecapNumber);
            TextView recapCustomMessage = (TextView) findViewById(R.id.RecapMessage);
            TextView recapMediaType = (TextView) findViewById(R.id.RecapMedia);
            TextView recapPasscode = (TextView) findViewById(R.id.RecapPasscode);
            TextView recapCameraType = (TextView) findViewById(R.id.RecapCamera);


            recapNumber.setText(savedNumber);
            recapCustomMessage.setText(savedCustomMessage);
            recapMediaType.setText(savedMediaType);
            recapPasscode.setText(savedPasscode);
            recapCameraType.setText(savedCameraType);
        }
    }

    public void sendToMain(View buttonControl) {
        //dito lalagay values para ipasa
        Intent nextSetup = new Intent(this, MainActivity.class);
        startActivity(nextSetup);
    }

    public void backToSetup6(View buttonControl) {
        Intent backSetup = new Intent(this, Setup6_Passcode.class);
        startActivity(backSetup);
    }

    @Override
    public void onBackPressed() {
        return; //Do nothing!
    }

}