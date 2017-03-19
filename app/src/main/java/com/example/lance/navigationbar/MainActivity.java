package com.example.lance.navigationbar;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

   /* RelativeLayout kailangangihide;*/
    private static final String SHARED_PREFERENCES_FILE = "SharePreferences";
    public static final String PREFS_NAME = "MyPrefsFile";
    public static final String KEY_FIRST_TIME = "firstTime";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        boolean firstTime = prefs.getBoolean(KEY_FIRST_TIME, true);

        if (!firstTime) {
            /*SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean(KEY_FIRST_TIME, false);
            editor.commit();*/
        } else {
            Intent sendToSetup = new Intent (this, Setup.class);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean(KEY_FIRST_TIME, false);
            editor.commit();
            startActivity(sendToSetup);
            finish();
        }

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        android.app.FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, new HomeScreen()).commit();

/*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //setMobileDataEnabled();
    }

    private void setMobileDataEnabled(Context context, boolean enabled) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final ConnectivityManager conman = (ConnectivityManager)  context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final Class conmanClass = Class.forName(conman.getClass().getName());
        final Field connectivityManagerField = conmanClass.getDeclaredField("mService");
        connectivityManagerField.setAccessible(true);
        final Object connectivityManager = connectivityManagerField.get(conman);
        final Class connectivityManagerClass =  Class.forName(connectivityManager.getClass().getName());
        final Method setMobileDataEnabledMethod = connectivityManagerClass.getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
        setMobileDataEnabledMethod.setAccessible(true);

        setMobileDataEnabledMethod.invoke(connectivityManager, enabled);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    private void saveSharedPref()
    {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        //Save values from Edit Text Controls
        preferences.edit().putString("firstTime", "1234").apply();
        /*SharedPreferences preferences = getSharedPreferences(SHARED_PREFERENCES_FILE, 0);
        SharedPreferences.Editor editor = preferences.edit();

        //Save values from Edit Text Controls
        editor.putString("firstTime", "1234");
        editor.commit();*/
    }

    private void loadSharedPreferences()
    {
        SharedPreferences preferences = getSharedPreferences(SHARED_PREFERENCES_FILE, 0);

        //Null check in case file does not exist

        if(preferences.contains("firstTime")) {
            String checkFirst = preferences.getString("firstTime", "");
            if (!checkFirst.equalsIgnoreCase("1234"))
            {
                setContentView(R.layout.setup_pg1);
                /*Intent sendToSetup = new Intent (this, Setup.class);
                startActivity(sendToSetup);*/
            }
            else
            {
                saveSharedPref();
            }
        }
       /* if(preferences != null) {
            String checkFirst = preferences.getString("firstTime", "");
            if (!checkFirst.equalsIgnoreCase("1234"))
            {
                Intent sendToSetup = new Intent (this, Setup.class);
                startActivity(sendToSetup);
            }
            else
            {
                saveSharedPref();
            }
        }*/
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();
        android.app.FragmentManager fragmentManager = getFragmentManager();

        if (id == R.id.nav_first_layout) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new ChangeNumber()).commit();
        } else if (id == R.id.nav_second_layout) {
           fragmentManager.beginTransaction().replace(R.id.content_frame, new ChangeMediaRecording()).commit();
        } else if (id == R.id.nav_third_layout) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new ChangeCustomMessage()).commit();
        } else if (id == R.id.nav_change_swipe) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new ChangeSwipe()).commit();
        } else if (id == R.id.nav_change_notification) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new ChangeNotification()).commit();
        } else if (id == R.id.nav_about) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new About()).commit();
        } else if (id == R.id.nav_facebook_connect) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new FacebookConnect()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

/*      kailangangihide = (RelativeLayout) findViewById(R.id.content_relative);
        kailangangihide.setVisibility(View.GONE);*/

        return true;
    }
}
