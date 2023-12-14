package com.hector.granjasandroid.model.Animal;

import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.hector.granjasandroid.api.CampoAPI;
import com.hector.granjasandroid.api.CampoApiInterface;
import com.hector.granjasandroid.contract.Animal.AddAnimalContract;
import com.hector.granjasandroid.domain.Animal;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddAnimalModel implements AddAnimalContract.Model {

    @Override
    public void addAnimal(Animal animal, OnRegisterAnimalListener listener) {
        try {

            CampoApiInterface campoApi = CampoAPI.buildInstance();
            Call<Animal> callAnimales = campoApi.addAnimal(animal);
            Log.d("animales", "llamada desde el addAnimalModel");
            callAnimales.enqueue(new Callback<Animal>() {
                @Override
                public void onResponse(Call<Animal> call, Response<Animal> response) {
                    Animal animal = response.body();
                    listener.onRegisterSuccess(animal);
                }

                @Override
                public void onFailure(Call<Animal> call, Throwable t) {
                    t.printStackTrace();
                    String message = "Error al invocar la operación";
                    listener.onRegisterError(message);
                }
            });
        } catch (SQLiteConstraintException sce) {
            sce.printStackTrace();
        }
    }
}

