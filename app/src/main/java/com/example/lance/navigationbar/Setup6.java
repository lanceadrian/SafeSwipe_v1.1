package com.example.lance.navigationbar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Lance on 3/12/2017.
 */

public class Setup6 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup_pg6);
    }

    public void sendToNextPage6 (View buttonControl)
    {

        //dito lalagay values para ipasa
        Intent nextSetup = new Intent (this, Setup7.class);
        startActivity(nextSetup);
    }

    public void backToSetup5 (View buttonControl)
    {
        Intent backSetup = new Intent (this, Setup5.class);
        startActivity(backSetup);
    }

}