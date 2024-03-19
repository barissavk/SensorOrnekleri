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

public class HumidityActivity extends Activity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor humiditySensor;
    private TextView humidityTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_humidity);

        // Sensör yöneticisi ve nem sensörü başlatılıyor
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        humiditySensor = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);

        // Nem değeri gösterecek TextView referansı alınıyor
        humidityTextView = findViewById(R.id.humidityTextView);

        // Nem sensörü mevcut değilse kullanıcıya uyarı gösterilir
        if (humiditySensor == null) {
            humidityTextView.setText("Nem sensörü mevcut değil.");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Nem sensörüne kayıt olunuyor
        if (humiditySensor != null) {
            sensorManager.registerListener(this, humiditySensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Uygulama duraklatıldığında sensör kaydı kaldırılıyor
        if (humiditySensor != null) {
            sensorManager.unregisterListener(this);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Sensör verisi değiştiğinde çağrılır
        if (event.sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY) {
            // Nem değeri alınıyor
            float humidityValue = event.values[0];

            // Alınan değer TextView'de gösteriliyor
            humidityTextView.setText("Nem Değeri: " + humidityValue + " %");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Sensör doğruluğu değiştiğinde çağrılır (bu örnekte kullanılmıyor)
    }
}
