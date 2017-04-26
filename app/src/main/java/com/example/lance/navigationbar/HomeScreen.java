package com.example.lance.navigationbar;

import android.app.Fragment;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

/**
 * Created by Lance on 3/12/2017.
 */
public class HomeScreen extends Fragment {

    View myView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.home_screen, container, false);


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());

        if (preferences != null) {
            String savedNumber = preferences.getString("number", "");
            String savedCustomMessage = preferences.getString("customMessage", "");
            String savedMediaType = preferences.getString("mediaType", "");
            String savedPasscode = preferences.getString("passcode", "");
            String savedCameraType = preferences.getString("cameraType", "");

            TextView recapNumber = (TextView) getActivity().findViewById(R.id.RecapNumber);
            TextView recapCustomMessage = (TextView) getActivity().findViewById(R.id.RecapMessage);
            TextView recapMediaType = (TextView) getActivity().findViewById(R.id.RecapMedia);
            TextView recapPasscode = (TextView) getActivity().findViewById(R.id.RecapPasscode);
            TextView recapCameraType = (TextView) getActivity().findViewById(R.id.RecapCamera);

//            recapNumber.setText(savedNumber);
//            recapCustomMessage.setText(savedCustomMessage);
//            recapMediaType.setText(savedMediaType);
//            recapPasscode.setText(savedPasscode);
//            recapCameraType.setText(savedCameraType);
        }
        return myView;
    }
}
