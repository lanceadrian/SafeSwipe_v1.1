package com.example.lance.navigationbar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class SafeSwipeVid extends AppCompatActivity implements SurfaceHolder.Callback {

    public static SurfaceView sView;
    public static SurfaceHolder sHolder;
    private LocationFragment mLocationFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_swipe);
        sView = (SurfaceView) this.findViewById(R.id.surfaceView_safeSwipe);
        sHolder = sView.getHolder();
        sHolder.addCallback(this);
        sHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        mLocationFragment = new LocationFragment();
        getSupportFragmentManager().beginTransaction().add(
                R.id.fragment_safeSwipe, mLocationFragment).commit();

    }

    @Override
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3)
    {
        Intent intent = new Intent(SafeSwipeVid.this, CameraService.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startService(intent);
        finish();

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
    }

}
