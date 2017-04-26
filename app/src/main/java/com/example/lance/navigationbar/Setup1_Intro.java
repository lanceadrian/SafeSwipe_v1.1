package com.example.lance.navigationbar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Lance on 3/12/2017.
 */

public class Setup1_Intro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup_pg1);
    }

    public void sendToNextPage (View buttonControl)
    {

        //dito lalagay values para ipasa
        Intent nextSetup = new Intent (this, Setup2_Number.class);
        startActivity(nextSetup);
    }


}