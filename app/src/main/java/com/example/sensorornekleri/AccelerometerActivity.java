package com.example.sensorornekleri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AccelerometerActivity extends Activity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometerSensor;
    private TextView xTextView, yTextView, zTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);


        // Sensör yöneticisi ve ivmeölçer sensörü başlatılıyor
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // TextView referansları alınıyor
        xTextView = findViewById(R.id.xTextView);
        yTextView = findViewById(R.id.yTextView);
        zTextView = findViewById(R.id.zTextView);

        // Ivmeölçer sensörü mevcut değilse kullanıcıya uyarı gösterilir
        if (accelerometerSensor == null) {
            xTextView.setText("Ivmeölçer sensörü mevcut değil.");
            yTextView.setText("");
            zTextView.setText("");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Ivmeölçer sensörüne kayıt olunuyor
        if (accelerometerSensor != null) {
            sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Uygulama duraklatıldığında sensör kaydı kaldırılıyor
        if (accelerometerSensor != null) {
            sensorManager.unregisterListener(this);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Sensör verisi değiştiğinde çağrılır
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            // X, Y ve Z eksenlerine ait ivme değerleri alınıyor
            float xValue = event.values[0];
            float yValue = event.values[1];
            float zValue = event.values[2];

            // Alınan değerler TextView'lere gösteriliyor
            xTextView.setText("X Ekseni: " + xValue);
            yTextView.setText("Y Ekseni: " + yValue);
            zTextView.setText("Z Ekseni: " + zValue);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Sensör doğruluğu değiştiğinde çağrılır (bu örnekte kullanılmıyor)
    }
}
