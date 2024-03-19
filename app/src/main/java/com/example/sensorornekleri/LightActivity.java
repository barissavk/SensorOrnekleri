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

public class LightActivity extends Activity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor lightSensor;
    private TextView lightTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);

        // Sensör yöneticisi ve ışık sensörü başlatılıyor
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        // Işık değeri gösterecek TextView referansı alınıyor
        lightTextView = findViewById(R.id.lightTextView);

        // Işık sensörü mevcut değilse kullanıcıya uyarı gösterilir
        if (lightSensor == null) {
            lightTextView.setText("Işık sensörü mevcut değil.");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Işık sensörüne kayıt olunuyor
        if (lightSensor != null) {
            sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Uygulama duraklatıldığında sensör kaydı kaldırılıyor
        if (lightSensor != null) {
            sensorManager.unregisterListener(this);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Sensör verisi değiştiğinde çağrılır
        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            float lightValue = event.values[0];
            // Işık değeri TextView'de gösterilir
            lightTextView.setText("Işık Değeri: " + lightValue + " lux");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Sensör doğruluğu değiştiğinde çağrılır (bu örnekte kullanılmıyor)
    }
}
