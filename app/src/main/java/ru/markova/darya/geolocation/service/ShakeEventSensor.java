package ru.markova.darya.geolocation.service;


import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import ru.markova.darya.geolocation.MainActivity;

/**
 * регистратор события тряски
 */
public class ShakeEventSensor implements SensorEventListener{
    private  float[] accellrations;
    private OnShakeListener mShakeListener;
    private long lastTime, currentTime;
    private long TIME_INTERVAL = 10; //изменения ускорений фикисируются каждый 50 мс

    public ShakeEventSensor(){
        accellrations = new float[3];
        lastTime = System.currentTimeMillis();
    }

    public interface OnShakeListener{

        public void onShake();
    }

    public  float[] getAccellrations() {
        return accellrations;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        accellrations[0] = sensorEvent.values[0];
        accellrations[1] = sensorEvent.values[1];
        accellrations[2] = sensorEvent.values[2];
        currentTime = System.currentTimeMillis();
        if (mShakeListener != null && (currentTime - lastTime) > TIME_INTERVAL) {
            lastTime = currentTime;
            mShakeListener.onShake();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
    //установка прослушивателя
    public void setOnShakeListener(OnShakeListener listener) {
        mShakeListener = listener;
    }
}
