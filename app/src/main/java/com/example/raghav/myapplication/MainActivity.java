package com.example.raghav.myapplication;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startMyService(View view) {
        Intent intent = new Intent(this,MyService.class);
        startService(intent);
    }

    public  void stopMyService(View view){
//        Toast.makeText(this, "stop command", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,MyService.class);
        stopService(intent);
    }
}
