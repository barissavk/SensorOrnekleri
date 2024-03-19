package com.example.sensorornekleri;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.os.Bundle;

public class TemperatureActivity extends Activity implements SensorEventListener  {

    private SensorManager sensorManager;
    private Sensor ambientTemperatureSensor;
    private TextView temperatureTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);

        temperatureTextView = findViewById(R.id.temperatureTextView);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        ambientTemperatureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);

        if (ambientTemperatureSensor == null) {
            // Cihazda çevresel sıcaklık sensörü bulunmuyorsa kullanıcıya bilgi verin veya uygun bir işlem yapın.
            temperatureTextView.setText("Bu cihazda çevresel sıcaklık sensörü bulunamadı.");
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
            float temperatureValue = event.values[0];

            temperatureTextView.setText("Sıcaklık: " + temperatureValue + " °C");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Bu metodun şu anda kullanımı gerekli değil, ancak zorunlu bir işlevdir.
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ambientTemperatureSensor != null) {
            sensorManager.registerListener(this, ambientTemperatureSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (ambientTemperatureSensor != null) {
            sensorManager.unregisterListener(this);
        }
    }
}