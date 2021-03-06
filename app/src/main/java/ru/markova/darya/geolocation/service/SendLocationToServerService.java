package ru.markova.darya.geolocation.service;


import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.markova.darya.geolocation.MainActivity;
import ru.markova.darya.geolocation.config.RetrofitBuilder;
import ru.markova.darya.geolocation.dto.LocationDTO;
import ru.markova.darya.geolocation.dto.ResponseEntityDTO;
import ru.markova.darya.geolocation.entity.GeoTableEntity;

/*public class SendLocationToServerService extends Service{

    final String LOG_TAG = "SendDataFromDBService";
    private  final static  Long CHECK_INTERVAL = 10 * 1000L; //интервал отправки данных о местоположении

    private Handler checkAndSendHandler = null;
    //сервис для отправки запросов на сервер
    private RetrofitDataSendService dataSendService;
    //сервис для работы с локальной базой данных
    private LocalStorageService localStorageService;

    private Intent intent;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate(){
        super.onCreate();
        intent = new Intent(MainActivity.BROADCAST_ACTION);
        localStorageService = new LocalStorageService(this);
        checkAndSendHandler = new Handler();
        dataSendService = RetrofitBuilder.getDataSendService();
    }

    public void onDestroy(){
        super.onDestroy();
        if (checkAndSendHandler != null) {
            checkAndSendHandler.removeCallbacksAndMessages(null);
            checkAndSendHandler.postDelayed(dataSendRunnable, CHECK_INTERVAL);
        }
        localStorageService.destroy();
    }


    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Служба отправки координат запущена", Toast.LENGTH_SHORT).show();
        checkAndSendHandler.postDelayed(dataSendRunnable, CHECK_INTERVAL);
        return super.onStartCommand(intent, flags, startId);
    }


    private Runnable dataSendRunnable = new Runnable() {
        @Override
        public void run() {
            checkAndSendHandler.removeCallbacksAndMessages(null);
            final Date currentDate = DateTimeService.getCurrentDateAndTime();
            final List<GeoTableEntity> locations = localStorageService.getSavedLocations(currentDate);
            //извлекли из базы данных
            final List<LocationDTO> locationDTOs = new ArrayList<>();

            for(int i=0; i<locations.size();i++){
                locationDTOs.add(new LocationDTO(locations.get(i)));
            }
            Call<ResponseEntityDTO> call = dataSendService.sendLocations(locationDTOs);

            //отправка координат на сервер
            call.enqueue(new Callback<ResponseEntityDTO>() {
                @Override
                public void onResponse(Call<ResponseEntityDTO> call, Response<ResponseEntityDTO> response) {
                    //успешная отправка
                    Log.d(LOG_TAG, "SENDING LOCATIONS SUCCESS...");
                    //удаляем уже отправленные данные
                    localStorageService.deleteLocations(currentDate);
                    intent.putExtra(MainActivity.STATUS_SENDING_PARAM, "locations success:" + DateTimeService.getCurrentDateAndTimeString());
                    sendBroadcast(intent);
                    checkAndSendHandler.postDelayed(dataSendRunnable, CHECK_INTERVAL);
                }
                @Override
                public void onFailure(Call<ResponseEntityDTO> call, Throwable t) {
                    //неуспешная отправка
                    Log.d(LOG_TAG, "SENDING LOCATIONS FAILURE...");
                    intent.putExtra(MainActivity.STATUS_SENDING_PARAM, "locations fail:" + DateTimeService.getCurrentDateAndTimeString());
                    sendBroadcast(intent);
                    checkAndSendHandler.postDelayed(dataSendRunnable, CHECK_INTERVAL);
                }
            });
        }
    };
}*/

        /* продолжение метода отправки ускорений
        final List<AccelerationDTO> accelerationDTOs = new ArrayList<>();
        for(int i=0; i<accelerations.size();i++){
        accelerationDTOs.add(new AccelerationDTO(accelerations.get(i)));
        }
        Call<ResponseEntityDTO> call = dataSendService.sendAccelerations(accelerationDTOs);
        //отправка ускорений на сервер осуществляться не будет
        call.enqueue(new Callback<ResponseEntityDTO>() {
        @Override
        public void onResponse(Call<ResponseEntityDTO> call, Response<ResponseEntityDTO> response) {
        //успешная отправка
        Log.d(LOG_TAG, "SENDING ACCELERATIONS SUCCESS...");
        //удаляем отправленные данные из локальной базы данных
        localStorageService.deleteAccelerations(currentDate);
        intent.putExtra(MainActivity.STATUS_SENDING_PARAM, "accelerations success:" + DateTimeService.getCurrentDateAndTimeString());
        sendBroadcast(intent);
        checkAndSendHandler.postDelayed(dataSendRunnable, CHECK_INTERVAL);
        }
        @Override
        public void onFailure(Call<ResponseEntityDTO> call, Throwable t) {
        //неуспешная отправка
        Log.d(LOG_TAG, "SENDING ACCELERATIONS FAILURE....");
        intent.putExtra(MainActivity.STATUS_SENDING_PARAM, "accelerations fail:" + DateTimeService.getCurrentDateAndTimeString());
        sendBroadcast(intent);
        checkAndSendHandler.postDelayed(dataSendRunnable, CHECK_INTERVAL);
        }
        });
            //метод, который выполняет отправку служебной информации
    private void sendUsefulObjects(){
        final Date currentDate = DateTimeService.getCurrentDateAndTime();
        final List<InfoTableEntity> infoObjects =
                localStorageService.getSavedInfoObjects(currentDate);
        final List<InfoDTO> infoDTOs = new ArrayList<>();
        for(int i=0; i < infoObjects.size();i++){
            infoDTOs.add(new InfoDTO(infoObjects.get(i)));
        }
        //отправка служебной информации
        Call<ResponseEntityDTO> call = RetrofitBuilder.getDataSendService().sendInfoObjects(infoDTOs);
        call.enqueue(new Callback<ResponseEntityDTO>() {
            @Override
            public void onResponse(Call<ResponseEntityDTO> call, Response<ResponseEntityDTO> response) {
                Log.d(LOG_TAG, "INFO SUCCESS...");
                localStorageService.deleteInfoObjects(currentDate);
                intent.putExtra(MainActivity.STATUS_SENDING_PARAM, "success saving");
                sendBroadcast(intent);
            }

            @Override
            public void onFailure(Call<ResponseEntityDTO> call, Throwable t) {
                Log.d(LOG_TAG, "INFO FAILURE....");
                intent.putExtra(MainActivity.STATUS_SENDING_PARAM, "error saving");
                sendBroadcast(intent);
            }
        });
    }
        */
