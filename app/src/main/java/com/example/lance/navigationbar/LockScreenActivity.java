package com.example.lance.navigationbar;

import android.app.WallpaperManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.concurrent.locks.Lock;

public class LockScreenActivity extends AppCompatActivity {

    static int ctr = 0;
    private String savedPasscode = "";
    private String savedIncorrectTries = "";
    private String savedMediaType = "";
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set up our LockScreen
        makeFullScreen();
        setContentView(R.layout.activity_lockscreen);

        //get the wallpaper from home screen
        final WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
        final Drawable wallpaperDrawable = wallpaperManager.getDrawable();

        //set the wallpaper u got from home screen on ur layout
        RelativeLayout ll = (RelativeLayout) findViewById(R.id.myRelativeLayout);
        ll.setBackground(wallpaperDrawable);

    }

    public void onClickPins(View view){

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if (preferences != null) {
            savedPasscode = preferences.getString("passcode", "");
            savedIncorrectTries = preferences.getString("tries", "");
            savedMediaType = preferences.getString("mediaType", "");
        }

        EditText lockPassword = (EditText) findViewById(R.id.password);
        String textString = lockPassword.getText().toString();
        switch(view.getId())
        {
            case R.id.pin_btn0:
                lockPassword.append("0");
                lockPassword.setSelection(lockPassword.getText().length());
                break;
            case R.id.pin_btn1:
                lockPassword.append("1");
                lockPassword.setSelection(lockPassword.getText().length());
                break;
            case R.id.pin_btn2:
                lockPassword.append("2");
                lockPassword.setSelection(lockPassword.getText().length());
                break;
            case R.id.pin_btn3:
                lockPassword.append("3");
                lockPassword.setSelection(lockPassword.getText().length());
                break;
            case R.id.pin_btn4:
                lockPassword.append("4");
                lockPassword.setSelection(lockPassword.getText().length());
                break;
            case R.id.pin_btn5:
                lockPassword.append("5");
                lockPassword.setSelection(lockPassword.getText().length());
                break;
            case R.id.pin_btn6:
                lockPassword.append("6");
                lockPassword.setSelection(lockPassword.getText().length());
                break;
            case R.id.pin_btn7:
                lockPassword.append("7");
                lockPassword.setSelection(lockPassword.getText().length());
                break;
            case R.id.pin_btn8:
                lockPassword.append("8");
                lockPassword.setSelection(lockPassword.getText().length());
                break;
            case R.id.pin_btn9:
                lockPassword.append("9");
                lockPassword.setSelection(lockPassword.getText().length());
                break;
            case R.id.pin_delete:
                if (textString.length() > 0)
                {
                    lockPassword.setText(textString.substring(0, textString.length() - 1));
                    lockPassword.setSelection(lockPassword.getText().length());
                }
                break;
            case R.id.pin_ok:
                if (!textString.trim().equals(savedPasscode))
                {
                    ctr++;
                    lockPassword.setText("");
                    Toast.makeText(this, "Wrong passcode. Try again.", Toast.LENGTH_SHORT).show();
                    if(ctr >= Integer.parseInt(savedIncorrectTries))
                    {
                        if(savedMediaType.toString().equals("Photo")) {
                            startSafeswipe();
                        }
                        else if (savedMediaType.toString().equals("Video"))
                        {
                            startSafeSwipeVideo();
                        }
                    }
                }
                else
                {
                    ctr = 0;
                    lockPassword.setText("");
                    unlockScreen();
                }
                break;
        }
    }

    public void startSafeSwipeVideo()
    {
        startActivityForResult(new Intent(this, SafeSwipeVid.class),1);
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++){
                startActivityForResult(new Intent(LockScreenActivity.this, SafeSwipe.class),1);
                handler.postDelayed(this, 30000);
            }
        }
    };

    public void startSafeswipe(){
        startActivityForResult(new Intent(this, SafeSwipe.class),1);

//        handler.postDelayed(runnable, 30000);

//        new CountDownTimer(601000, 300000) {
//
//            @Override
//            public void onTick(long millisUntilFinished) {
//                startActivity(new Intent(LockScreenActivity.this, SafeSwipe.class));
//            }
//
//            @Override
//            public void onFinish() {
//
//            }
//        }.start();
    }

    public void onStartSafeSwipe(View view){
        startActivity(new Intent(this, SafeSwipe.class));
    }

    /**
     * A simple method that sets the screen to fullscreen.  It removes the Notifications bar,
     *   the Actionbar and the virtual keys (if they are on the phone)
     */
    public void makeFullScreen() {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if(Build.VERSION.SDK_INT < 19) { //View.SYSTEM_UI_FLAG_IMMERSIVE is only on API 19+
            this.getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE);
        } else {
            this.getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE);
        }
    }

    @Override
    public void onBackPressed() {
        return; //Do nothing!
    }

    public void unlockScreen() {
        //Instead of using finish(), this totally destroys the process
        //finish();
        //android.os.Process.killProcess(android.os.Process.myPid());
        //startActivity(new Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_HOME));

        this.finishAffinity();

        /*Intent intent = new Intent(getApplicationContext(), LockScreenService.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        startActivity(intent);*/
    }

    private boolean canToggleGPS() {
        PackageManager pacman = getPackageManager();
        PackageInfo pacInfo = null;

        try {
            pacInfo = pacman.getPackageInfo("com.android.settings", PackageManager.GET_RECEIVERS);
        } catch (PackageManager.NameNotFoundException e) {
            return false; //package not found
        }

        if(pacInfo != null){
            for(ActivityInfo actInfo : pacInfo.receivers){
                //test if receiver is exported. if so, we can toggle GPS.
                if(actInfo.name.equals("com.android.settings.widget.SettingsAppWidgetProvider") && actInfo.exported){
                    return true;
                }
            }
        }

        return false; //default
    }

    private void turnGPSOn(){
        String provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

        if(!provider.contains("gps")){ //if gps is disabled
            final Intent poke = new Intent();
            poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
            poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
            poke.setData(Uri.parse("3"));
            sendBroadcast(poke);
        }
    }

    private void turnGPSOff(){
        String provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

        if(provider.contains("gps")){ //if gps is enabled
            final Intent poke = new Intent();
            poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
            poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
            poke.setData(Uri.parse("3"));
            sendBroadcast(poke);
        }
    }
}
