package com.example.lance.navigationbar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Lance on 3/12/2017.
 */

public class Setup2 extends AppCompatActivity {

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
        else
        {
            Intent nextSetup = new Intent (this, Setup3.class);
            nextSetup.putExtra("number", number.getText().toString());
            startActivity(nextSetup);
        }


    }

    public void backToSetup (View buttonControl)
    {
        Intent backSetup = new Intent (this, Setup.class);
        startActivity(backSetup);
    }
}