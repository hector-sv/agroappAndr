package com.hector.granjasandroid.model.Animal;

import android.content.Context;
import android.util.Log;

import com.hector.granjasandroid.api.CampoAPI;
import com.hector.granjasandroid.api.CampoApiInterface;
import com.hector.granjasandroid.contract.Animal.AnimalListContract;
import com.hector.granjasandroid.domain.Animal;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnimalListModel implements AnimalListContract.Model {

    private Context context;

    public AnimalListModel(Context context) {
        this.context = context;
    }

    @Override
    public void loadAllAnimales(OnLoadAnimalListener listener) {

        CampoApiInterface campoApi = CampoAPI.buildInstance();
        Call<List<Animal>> callAnimales = campoApi.getAnimales();
        Log.d("Animales", "llamada desde el Animales model");
        callAnimales.enqueue(new Callback<List<Animal>>() {
            @Override
            public void onResponse(Call<List<Animal>> call, Response<List<Animal>> response) {
                Log.d("Animales", "llamada desde el Animales model OK");
                List<Animal>animales = response.body();
                listener.onLoadAnimalesSuccess(animales);
            }

            @Override
            public void onFailure(Call<List<Animal>> call, Throwable t) {
                Log.d("Animales", "llamada desde el Animales model KO");
                t.printStackTrace();
                String message = "Error al invocar la operación";
                listener.onLoadAnimalesError(message);

            }
        });
    }
}

