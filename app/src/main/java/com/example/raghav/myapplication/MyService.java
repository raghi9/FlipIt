package com.example.raghav.myapplication;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service{
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    MediaPlayer mediaPlayer;

    SensorEventListener mAccSensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            // More code goes here
            Log.d("timestamp" ,": "+ event.timestamp);
            final float alpha = (float) 0.8;

            // Isolate the force of gravity with the low-pass filter.
            float[] gravity = new float[3];
            gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
            gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
            gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];

            // Remove the gravity contribution with the high-pass filter.
            float[] linear_acceleration = new float[3];
            linear_acceleration[0] = event.values[0] - gravity[0];
            linear_acceleration[1] = event.values[1] - gravity[1];
            linear_acceleration[2] = event.values[2] - gravity[2];


            if (linear_acceleration[2]>30) {
//                Toast.makeText(MyService.this, ":"+linear_acceleration[2], Toast.LENGTH_SHORT).show();
                mediaPlayer.start();
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {
        }
    };


    @Override
    public void onCreate() {
        super.onCreate();
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        assert mSensorManager != null;
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(mAccSensorEventListener, mAccelerometer, 30000000, 30000000);
        mediaPlayer = MediaPlayer.create(this,R.raw.funny_sound);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        Toast.makeText(this, "Service started", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        mediaPlayer.stop();
        mSensorManager.unregisterListener(mAccSensorEventListener);
//        Toast.makeText(this, "Service stopped", Toast.LENGTH_SHORT).show();
    }






}
