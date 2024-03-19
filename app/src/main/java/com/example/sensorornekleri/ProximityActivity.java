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

public class ProximityActivity extends Activity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor proximitySensor;
    private TextView proximityTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity);

        proximityTextView = findViewById(R.id.proximityTextView);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        if (proximitySensor == null) {
            // Cihazda yakınlık sensörü bulunmuyorsa kullanıcıya bilgi verin veya uygun bir işlem yapın.
            proximityTextView.setText("Bu cihazda yakınlık sensörü bulunamadı.");
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            float proximityValue = event.values[0];

            if (proximityValue < proximitySensor.getMaximumRange()) {
                // Cihaz bir şeye yakın
                proximityTextView.setText("Cihaz yakında!");
            } else {
                // Cihaz bir şeye uzak
                proximityTextView.setText("Cihaz uzağında!");
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Bu metodun şu anda kullanımı gerekli değil, ancak zorunlu bir işlevdir.
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (proximitySensor != null) {
            sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (proximitySensor != null) {
            sensorManager.unregisterListener(this);
        }
    }
}