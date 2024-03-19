package com.example.sensorornekleri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonAccelerometer = findViewById(R.id.buttonAccelerometer);
        Button buttonLight = findViewById(R.id.buttonLight);
        Button buttonGyroscope = findViewById(R.id.buttonGyroscope);
        Button buttonMagneticField = findViewById(R.id.buttonMagneticField);
        Button buttonPressure = findViewById(R.id.buttonPressure);
        Button buttonTemperature = findViewById(R.id.buttonTemperature);
        Button buttonProximity = findViewById(R.id.buttonProximity);
        Button buttonGravity = findViewById(R.id.buttonGravity);
        Button buttonHumidity = findViewById(R.id.buttonHumidity);

        buttonAccelerometer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AccelerometerActivity.class);
                startActivity(intent);
            }
        });

        buttonLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LightActivity.class);
                startActivity(intent);
            }
        });

        buttonGyroscope.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GyroscopeActivity.class);
                startActivity(intent);
            }
        });

        buttonMagneticField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MagneticFieldActivity.class);
                startActivity(intent);
            }
        });

        buttonPressure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PressureActivity.class);
                startActivity(intent);
            }
        });

        buttonTemperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TemperatureActivity.class);
                startActivity(intent);
            }
        });

        buttonProximity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProximityActivity.class);
                startActivity(intent);
            }
        });

        buttonGravity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GravityActivity.class);
                startActivity(intent);
            }
        });

        buttonHumidity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HumidityActivity.class);
                startActivity(intent);
            }
        });
    }
}