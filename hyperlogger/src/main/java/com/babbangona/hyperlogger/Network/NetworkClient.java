package com.babbangona.hyperlogger.Network;

import android.content.Context;

import com.babbangona.hyperlogger.Database.sharedprefs.SharedPrefs;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkClient {

    public static final String BASE_URL = "https://mobileapps.testenvironmentbg.com/hyperlogger_api/public/api/v1/";
    public Retrofit retrofit = null;
    private static NetworkClient mInstance;


    private NetworkClient(Context context){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(httpLoggingInterceptor);
        OkHttpClient okHttpClient = builder.build();

        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder().baseUrl(new SharedPrefs(context).getBaseUrl()).
                addConverterFactory(GsonConverterFactory.create(gson)).client(okHttpClient).build();


    }

    public static synchronized NetworkClient getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new NetworkClient(context);
        }
        return mInstance;
    }

    public RetrofitInterface getApi(){
        return retrofit.create(RetrofitInterface.class);
    }

}
