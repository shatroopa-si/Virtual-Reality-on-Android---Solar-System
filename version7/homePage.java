package com.example.android.version7;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class homePage extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = "VR_ON_ANDROID";    //label for logs


    RelativeLayout completePackage;

    // Variables
    LinearLayout vr_monocular;                  // Layout For Activity
    GLSurfaceView screen;                       // Rendering Screen for Open GL ES 1.0
    Button toggle;                              // To toggle Between VR and Full Screen Mode
    Button info;


    // Sensor Variables
    private SensorManager mSensorManager;
    public static float[] values;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        RelativeLayout.LayoutParams params =
                new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        RelativeLayout.LayoutParams tButtonParams =
                new RelativeLayout.LayoutParams(90, 90);
        tButtonParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        tButtonParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

        RelativeLayout.LayoutParams iButtonParams =
                new RelativeLayout.LayoutParams(90, 90);
        iButtonParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        iButtonParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);

        completePackage = new RelativeLayout(this);
        completePackage.setId(R.id.fullPackage);
        completePackage.setLayoutParams(params);


        //Monocular Vision------------------
        vr_monocular = new LinearLayout(this);
        vr_monocular.setLayoutParams(params);
        vr_monocular.setOrientation(LinearLayout.HORIZONTAL);
        vr_monocular.setId(R.id.monocularVision);
        vr_monocular.setVisibility(View.VISIBLE);

        screen = new GLSurfaceView(this);
        screen.setLayoutParams(params);
        screen.setId(R.id.fullView);
        screen.setBackgroundResource(R.drawable.universesky);
        screen.setZOrderOnTop(true);
        screen.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        screen.getHolder().setFormat(PixelFormat.RGBA_8888);
        SpaceRenderer.binocular = false;
        screen.setRenderer(new SpaceRenderer(this));

        vr_monocular.addView(screen);
        //----------------------------------


        //Toggle button---------------------
        toggle = new Button(this);
        toggle.setId(R.id.toggle);
        toggle.setLayoutParams(tButtonParams);
        toggle.setBackgroundResource(R.drawable.cardboard);

        toggle.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (SpaceRenderer.binocular) {
                            SpaceRenderer.binocular = false;
                            toggle.setBackgroundResource(R.drawable.cardboard);
                        } else {
                            SpaceRenderer.binocular = true;
                            toggle.setBackgroundResource(R.drawable.fullscreen);

                        }
                    }
                });
        //----------------------------------

        //Info button---------
        info = new Button(this);
        info.setLayoutParams(iButtonParams);
        info.setId(R.id.info);
        info.setBackgroundResource(R.drawable.info);
        info.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent infoIntent = new Intent(homePage.this, info.class);
                        homePage.this.startActivity(infoIntent);
                        homePage.this.finish();
                    }
                });

        completePackage.addView(vr_monocular);
        completePackage.addView(toggle);
        completePackage.addView(info);

        // Sensor Initialization-------------------------------------
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        setContentView(completePackage);
    }

    @Override
    protected void onPause() {
        super.onPause();

        screen.onPause();
        // Unregister the Sensor Listener on Pause to Save Battery
        mSensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        screen.onResume();
        // Re-register Listener on Resume
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_GAME_ROTATION_VECTOR),
                SensorManager.SENSOR_DELAY_GAME * 20);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        final int type = sensorEvent.sensor.getType();
        if (type == Sensor.TYPE_GAME_ROTATION_VECTOR) {
            values = sensorEvent.values.clone();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


}
