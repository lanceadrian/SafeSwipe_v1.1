package com.example.lance.navigationbar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import static com.example.lance.navigationbar.R.id.radioButton;

/**
 * Created by Lance on 3/12/2017.
 */

public class Setup4 extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button btnDisplay;
    public String mediaType = null;

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
        radioButton = (RadioButton) findViewById(selectedId);

        if (radioButton.getText().equals("Photo")) {
            mediaType = "Photo";
        } else if (radioButton.getText().equals("Video")) {
            mediaType = "Video";
        }


        Toast.makeText(getApplicationContext(),
                mediaType,
                Toast.LENGTH_SHORT).show();



        Intent receivedIntent = getIntent();

        String number = receivedIntent.getStringExtra("number");
        String custommessage = receivedIntent.getStringExtra("custommessage");


        //dito lalagay values para ipasa
        Intent nextSetup = new Intent(this, Setup5.class);

        nextSetup.putExtra("number",number);
        nextSetup.putExtra("custommmessage",custommessage);
        nextSetup.putExtra("mediatype",mediaType);

        startActivity(nextSetup);

    }

    public void backToSetup3 (View buttonControl)
    {
        Intent backSetup = new Intent (this, Setup3.class);
        startActivity(backSetup);
    }
}