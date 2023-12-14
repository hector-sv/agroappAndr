package com.hector.granjasandroid.api;

import static com.hector.granjasandroid.api.Constants.BASE_URL;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CampoAPI {
    public static CampoApiInterface buildInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(CampoApiInterface.class);
    }

}
