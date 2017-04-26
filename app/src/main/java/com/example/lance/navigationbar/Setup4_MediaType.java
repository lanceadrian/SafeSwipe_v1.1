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

public class Setup4_MediaType extends AppCompatActivity {

    private RadioButton defaultRadioBtn;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button btnDisplay;
    public String mediaType = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup_pg4);
    }

    public void sendToNextPage4(View buttonControl) {

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
        btnDisplay = (Button) findViewById(R.id.mediaBtn);

        // get selected radio button from radioGroup
        int selectedId = radioGroup.getCheckedRadioButtonId();

        // find the radiobutton by returned id
        if(selectedId == -1) {
            Toast.makeText(getApplicationContext(),
                    "You have to choose one.",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            radioButton = (RadioButton) findViewById(selectedId);
            if (radioButton.getText().equals("Photo")) {
                mediaType = "Photo";
                Toast.makeText(getApplicationContext(),
                        mediaType,
                        Toast.LENGTH_SHORT).show();
            } else if (radioButton.getText().equals("Video")) {
                mediaType = "Video";
                Toast.makeText(getApplicationContext(),
                        mediaType,
                        Toast.LENGTH_SHORT).show();
            }
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("mediaType", mediaType);
            editor.commit();
            Intent nextSetup = new Intent(this, Setup5_CameraType.class);
            startActivity(nextSetup);
        }
    }

    public void backToSetup3 (View buttonControl)
    {
        Intent backSetup = new Intent (this, Setup3_CustomMessage.class);
        startActivity(backSetup);
    }

    @Override
    public void onBackPressed() {
        return; //Do nothing!
    }
}