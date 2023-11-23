package com.app1;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Injection {
    private static final Injection ourInstance = new Injection();
    private final MenuAPIs menuAPIs;

    public static Injection getInstance() {
        return ourInstance;
    }

    private Injection() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://theqksa.com/vbn/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().addInterceptor(interceptor).build())
                .build();

        menuAPIs = retrofit.create(MenuAPIs.class);

    }

    public MenuAPIs getMenuAPIs() {
        return menuAPIs;
    }
}
