package com.example.lance.navigationbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SafeSwipe extends AppCompatActivity implements SurfaceHolder.Callback {

    private static final String CAMERA_DIR = "/dcim/";

    //a variable to store a reference to the Surface View at the main.admin file
    private SurfaceView sv;

    //a bitmap to display the captured image
    private Bitmap bmp;

    /**Camera variables**/

    //a surface holder
    private SurfaceHolder sHolder;

    //a variable to control the camera
    private Camera mCamera;
    //the camera parameters
    private Camera.Parameters parameters;
    private LocationFragment mLocationFragment;
    private String cameraType;

    private String root = Environment.getExternalStorageDirectory().getAbsolutePath();

    private String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmm")
            .format(new Date());

    private String fileName = "IMG_" + timeStamp + ".jpg";

    public String picURL;

    AmazonS3 s3;
    TransferUtility transferUtility;
    File fileToUpload = new File(root + CAMERA_DIR + "SafeSwipe/" + fileName);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_swipe);

        //get the Surface View at the main.admin file
        sv = (SurfaceView) this.findViewById(R.id.surfaceView_safeSwipe);

        //Get a surface
        sHolder = sv.getHolder();

        //add the callback interface methods defined below as the Surface View callbacks
        sHolder.addCallback(this);

        //tells Android that this surface will have its data constantly replaced
        sHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        saveToAmazon();

    }

    public void saveToAmazon(){
        credentialsProvider();
        setTransferUtility();
    }

    public void credentialsProvider(){

        // Initialize the Amazon Cognito credentials provider
        CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
                getApplicationContext(),
                "ap-southeast-2:f4efa313-eba9-4946-a471-c4607aaf712b", // Identity Pool ID
                Regions.AP_SOUTHEAST_2 // Region
                //us-west-2:0bf16a4b-ba45-4aa6-a435-829cf3ba02c4 // kay pangan
                //Regions.US_WEST_2 // kay pangan
        );

        setAmazonS3Client(credentialsProvider);
    }

    public void setAmazonS3Client(CognitoCachingCredentialsProvider credentialsProvider){

        // Create an S3 client
        s3 = new AmazonS3Client(credentialsProvider);

        // Set the region of your S3 bucket
        s3.setRegion(Region.getRegion(Regions.AP_SOUTHEAST_1));
        //Regions.US_WEST_2 // kay pangan
    }

    public void setTransferUtility(){

        transferUtility = new TransferUtility(s3, getApplicationContext());
    }

    @Override
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3)
    {


        //get camera parameters
        parameters = mCamera.getParameters();

        //set camera parameters
        mCamera.setParameters(parameters);
        mCamera.startPreview();

        //sets what code should be executed after the picture is taken
        Camera.PictureCallback mCall = new Camera.PictureCallback()
        {
            @Override
            public void onPictureTaken(byte[] data, Camera camera)
            {
                //decode the data obtained by the camera into a Bitmap
                bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                //set the iv_image
//                iv_image.setImageBitmap(bmp);
                saveImage(bmp);
                picURL = "http://s3-ap-southeast-1.amazonaws.com/safeswipeuploads/" + fileName;
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("picURL", picURL);
                editor.commit();
                mLocationFragment = new LocationFragment();
                getSupportFragmentManager().beginTransaction().add(
                        R.id.fragment_safeSwipe, mLocationFragment).commit();
                uploadFile();

//                if(camera!=null){
//                    camera.stopPreview();
//                    camera.setPreviewCallback(null);
//
//                    camera.release();
//                    camera = null;
//                }
            }

        };

        mCamera.takePicture(null, null, mCall);

//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);

    }

    public void uploadFile(){

        TransferObserver transferObserver = transferUtility.upload(
                "safeswipeuploads",     /* The bucket to upload to */
                fileName,    /* The key for the uploaded object */
                fileToUpload       /* The file where the data to upload exists */
        );

        /*picURL = "http://s3-ap-southeast-1.amazonaws.com/safeswipeuploads/" + fileName;
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("picURL", picURL);
        editor.commit();*/

        transferObserverListener(transferObserver);
    }

    public void transferObserverListener(TransferObserver transferObserver){

        transferObserver.setTransferListener(new TransferListener(){

            @Override
            public void onStateChanged(int id, TransferState state) {
                Log.e("statechange", state+"");
            }

            @Override
            public void onProgressChanged(int id, long bytesCurrent, long bytesTotal) {
                int percentage = (int) (bytesCurrent/bytesTotal * 100);
                Log.e("percentage",percentage +"");
            }

            @Override
            public void onError(int id, Exception ex) {
                Log.e("error","error");
            }

        });
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        // The Surface has been created, acquire the camera and tell it where
        // to draw the preview.
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if (preferences != null) {
            cameraType = preferences.getString("cameraType", "");
        }

        if (cameraType.equals("Front")) {
            mCamera = Camera.open(1);
        }
        else {
            mCamera = Camera.open(0);
        }

        try {
            mCamera.setPreviewDisplay(holder);

        } catch (IOException exception) {
            mCamera.release();
            mCamera = null;
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
        //stop the preview
        mCamera.stopPreview();
        //release the camera
        mCamera.release();
        //unbind the camera from this object
        mCamera = null;
    }

    public void saveImage(Bitmap finalbitmap)
    {

        File myDir = new File (root + CAMERA_DIR + "SafeSwipe");
        myDir.mkdirs();

        File file = new File(myDir, fileName);
        if (file.exists()) file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalbitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
            startActivityForResult(new Intent(this, LockScreenActivity .class),1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
