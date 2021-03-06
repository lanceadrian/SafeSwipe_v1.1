package com.example.lance.navigationbar;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by Lance on 2/5/2017.
 */

public class ChangeMediaRecording extends Fragment implements View.OnClickListener
{
    private RadioButton defaultRadioBtn;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button btnDisplay;
    public String mediaType = "";
    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        myView = inflater.inflate(R.layout.change_media_type, container, false);
        Button b = (Button) myView.findViewById(R.id.MediaSettingsButton);
        b.setOnClickListener(this);
        return myView;
    }

    @Override
    public void onClick(View view) {
        radioGroup = (RadioGroup) getActivity().findViewById(R.id.radioGroupForMediaSettings);

        // get selected radio button from radioGroup
        int selectedId = radioGroup.getCheckedRadioButtonId();

        // find the radio button by returned id
        if(selectedId == -1) {
            Toast.makeText(getActivity(),
                    "You have to choose one.",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            radioButton = (RadioButton) getActivity().findViewById(selectedId);
            if (radioButton.getText().equals("Photo")) {
                mediaType = "Photo";
            } else if (radioButton.getText().equals("Video")) {
                mediaType = "Video";
            }
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("mediaType", mediaType);
            editor.commit();
            Toast.makeText(getActivity(),
                    "Media Type has been changed successfully!",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
