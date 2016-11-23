package ru.markova.darya.geolocation.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.markova.darya.geolocation.service.RetrofitDataSendService;

/**
 * Created by darya on 19.11.16.
 */
public class RetrofitBuilder {

    private RetrofitBuilder(){}

    private static Gson gson;

    private static final String URL = "https://vps3.vistar.su";

    private static Retrofit retrofit;

    private static RetrofitDataSendService dataSendService;

    public static RetrofitDataSendService getDataSendService(){
        if (dataSendService == null){
            gson = new GsonBuilder().create();
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(URL)
                    .build();
            dataSendService = retrofit.create(RetrofitDataSendService.class);
        }
        return dataSendService;
    }
}