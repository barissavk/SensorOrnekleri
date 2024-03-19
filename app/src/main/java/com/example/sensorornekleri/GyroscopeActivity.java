package com.example.sensorornekleri;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class GyroscopeActivity extends Activity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor gyroscopeSensor;
    private TextView xTextView, yTextView, zTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyroscope);

        // Sensör yöneticisi ve gyro sensörü başlatılıyor
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        // TextView referansları alınıyor
        xTextView = findViewById(R.id.xTextView);
        yTextView = findViewById(R.id.yTextView);
        zTextView = findViewById(R.id.zTextView);

        // Gyro sensörü mevcut değilse kullanıcıya uyarı gösterilir
        if (gyroscopeSensor == null) {
            xTextView.setText("Gyro sensörü mevcut değil.");
            yTextView.setText("");
            zTextView.setText("");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Gyro sensörüne kayıt olunuyor
        if (gyroscopeSensor != null) {
            sensorManager.registerListener(this, gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Uygulama duraklatıldığında sensör kaydı kaldırılıyor
        if (gyroscopeSensor != null) {
            sensorManager.unregisterListener(this);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Sensör verisi değiştiğinde çağrılır
        if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            // X, Y ve Z eksenlerine ait dönüş değerleri alınıyor
            float xValue = event.values[0];
            float yValue = event.values[1];
            float zValue = event.values[2];

            // Alınan değerler TextView'lere gösteriliyor
            xTextView.setText("X Ekseni: " + xValue + " rad/s");
            yTextView.setText("Y Ekseni: " + yValue + " rad/s");
            zTextView.setText("Z Ekseni: " + zValue + " rad/s");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Sensör doğruluğu değiştiğinde çağrılır (bu örnekte kullanılmıyor)
    }
}
