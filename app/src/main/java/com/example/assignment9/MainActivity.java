package com.example.assignment9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * You will be making an app for a selfie a day. The idea is quite simple: When the app loads up, it should
 * display the set of images you have stored in firebase storage. If an image is tapped, it should
 * show the image in full screen. If you shake the app, it should open another activity which should open
 * the selfie camera and you should be able to take an image. If the user takes an image, it should be
 * uploaded to your firebase storage and you should also return to the previous activity.
 *
 * You do not need any authentication in this app.
 *
 * Restrictions:
 * You need to use Firebase storage to store images.
 * You need to use CameraX library.
 * You need to use Glide to display images.
 * There should be no toolbar in the app.
 * You can design your own views.
 */

// what
public class MainActivity extends AppCompatActivity implements SensorEventListener {
    FirebaseUser user;
    FirebaseAuth fAuth;
    Query query;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseFirestore fStore;
    SensorManager sensorManager;
    private long lastUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
        user = fAuth.getCurrentUser();
        firebaseDatabase=FirebaseDatabase.getInstance();
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        lastUpdate = System.currentTimeMillis();

    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            getAccelerometer(event);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    private void getAccelerometer(SensorEvent event) {
        float[] values = event.values;
        // Movement
        float x = values[0];
        float y = values[1];
        float z = values[2];

        float accelationSquareRoot = (x * x + y * y + z * z)
                / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);
        long actualTime = event.timestamp;
        if (accelationSquareRoot >= 2) //
        {
            if (actualTime - lastUpdate < 200) {
                return;
            }
            lastUpdate = actualTime;
            Toast.makeText(this, "Device was shuffed", Toast.LENGTH_SHORT)
                    .show();
            startActivity(new Intent(getApplicationContext(), CameraActivity.class));

        }
    }
}