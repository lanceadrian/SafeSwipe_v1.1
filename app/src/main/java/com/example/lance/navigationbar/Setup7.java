package com.example.lance.navigationbar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Lance on 3/12/2017.
 */

public class Setup7 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup_pg7);
    }

    public void sendToMain (View buttonControl)
    {

        //dito lalagay values para ipasa
        Intent nextSetup = new Intent (this, MainActivity.class);
        startActivity(nextSetup);
    }

    public void backToSetup6 (View buttonControl)
    {
        Intent backSetup = new Intent (this, Setup6.class);
        startActivity(backSetup);
    }
}