package com.example.lance.navigationbar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Lance on 3/12/2017.
 */

public class Setup5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup_pg5);
    }

    public void sendToNextPage5 (View buttonControl)
    {

        //dito lalagay values para ipasa
        Intent nextSetup = new Intent (this, Setup6.class);
        startActivity(nextSetup);
    }

    public void backToSetup4 (View buttonControl)
    {
        Intent backSetup = new Intent (this, Setup4.class);
        startActivity(backSetup);
    }
}