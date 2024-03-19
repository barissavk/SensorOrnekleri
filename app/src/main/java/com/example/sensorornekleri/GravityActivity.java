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

public class GravityActivity extends Activity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor gravitySensor;
    private TextView gravityTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gravity);

        // Sensör yöneticisi ve yerçekimi sensörü başlatılıyor
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        gravitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);

        // Yerçekimi değeri gösterecek TextView referansı alınıyor
        gravityTextView = findViewById(R.id.gravityTextView);

        // Yerçekimi sensörü mevcut değilse kullanıcıya uyarı gösterilir
        if (gravitySensor == null) {
            gravityTextView.setText("Yerçekimi sensörü mevcut değil.");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Yerçekimi sensörüne kayıt olunuyor
        if (gravitySensor != null) {
            sensorManager.registerListener(this, gravitySensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Uygulama duraklatıldığında sensör kaydı kaldırılıyor
        if (gravitySensor != null) {
            sensorManager.unregisterListener(this);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Sensör verisi değiştiğinde çağrılır
        if (event.sensor.getType() == Sensor.TYPE_GRAVITY) {
            // Yerçekimi değeri alınıyor
            float gravityValue = event.values[0];

            // Alınan değer TextView'de gösteriliyor
            gravityTextView.setText("Yerçekimi Değeri: " + gravityValue + " m/s²");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Sensör doğruluğu değiştiğinde çağrılır (bu örnekte kullanılmıyor)
    }
}
