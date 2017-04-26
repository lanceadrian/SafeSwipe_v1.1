package com.example.lance.navigationbar;


import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;

public class LocationFragment extends Fragment implements
        ConnectionCallbacks, OnConnectionFailedListener {

    //09153384007 - johnny

    protected static final String TAG = "LocationFragment";
    protected GoogleApiClient mGoogleApiClient;
    private static Location mLastLocation;
    private double lastLat = 0;
    private double lastLong = 0;
    private String msg = "SafeSwipe Activated Location Marked: ";
    private String mapLink = "http://maps.google.com/?q=";
    private String savedNumber = "";
    private String savedCustomMessage = "";
    private String savedPicURL = "";

    public LocationFragment() {}

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);

            return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        buildGoogleApiClient();
        mGoogleApiClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onConnected(Bundle connectionHint) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        if (preferences != null) {
            savedNumber = preferences.getString("number", "");
            savedCustomMessage = preferences.getString("customMessage", "");
            savedPicURL = preferences.getString("picURL","");
        }

        if (ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        }
        if (mLastLocation != null) {
            lastLat = mLastLocation.getLatitude();
            lastLong = mLastLocation.getLongitude();

            SmsManager sm = SmsManager.getDefault();
            msg += mapLink + lastLat + "," + lastLong + "\n" + savedCustomMessage + "\n" + savedPicURL;
            sm.sendTextMessage(savedNumber, null, msg, null, null);

        } else {
            SmsManager sm = SmsManager.getDefault();
            msg += "No Location Detected.";
            sm.sendTextMessage(savedNumber, null, msg, null, null);
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + result.getErrorCode());
    }

    @Override
    public void onConnectionSuspended(int cause) {
        Log.i(TAG, "Connection suspended");
        mGoogleApiClient.connect();
    }
}
