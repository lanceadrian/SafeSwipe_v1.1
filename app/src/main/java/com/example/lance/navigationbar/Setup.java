package com.example.lance.navigationbar;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by Lance on 3/12/2017.
 */

public class Setup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup_pg1);
    }

    public void sendToNextPage (View buttonControl)
    {

        //dito lalagay values para ipasa
        Intent nextSetup = new Intent (this, Setup2.class);
        startActivity(nextSetup);
    }


}