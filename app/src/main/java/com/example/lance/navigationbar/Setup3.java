package com.example.lance.navigationbar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Lance on 3/12/2017.
 */

public class Setup3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup_pg3);
    }

    public void sendToNextPage3 (View buttonControl)
    {

        EditText custommessage =(EditText) findViewById(R.id.customMessage);

        if( custommessage.getText().toString().trim().equals("")) {
            Toast.makeText(getApplicationContext(),
                    "Field Required",
                    Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent receivedIntent = getIntent();

            String number = receivedIntent.getStringExtra("number");
            Intent nextSetup = new Intent (this, Setup4.class);

            nextSetup.putExtra("number", number);
            nextSetup.putExtra("custommessage", custommessage.getText().toString());
            startActivity(nextSetup);
        }
    }

    public void backToSetup2 (View buttonControl)
    {
        Intent backSetup = new Intent (this, Setup2.class);
        startActivity(backSetup);
    }

}