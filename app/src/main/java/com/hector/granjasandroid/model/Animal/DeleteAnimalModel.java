package com.hector.granjasandroid.model.Animal;

import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.hector.granjasandroid.api.CampoAPI;
import com.hector.granjasandroid.api.CampoApiInterface;
import com.hector.granjasandroid.contract.Animal.DeleteAnimalContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteAnimalModel implements DeleteAnimalContract.Model {

    @Override
    public void deleteAnimal(long animalId, OnDeleteAnimalListener listener) {
        try {
            CampoApiInterface campoApi = CampoAPI.buildInstance();
            Call<Void> callAnimales = campoApi.deleteAnimales(animalId);
            Log.d("Animal", "llamada desde el DeleAnimalModel");
            callAnimales.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Log.d("Animals", "llamada desde el DeleteAnimalModel OK");
                    listener.onDeleteSuccess();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.d("Animal", "llamada desde el DeleteAnimalModel KO");
                    t.printStackTrace();
                    String message = "Error al invocar la operación";
                    listener.onDeleteError(message);
                }
            });
        } catch (SQLiteConstraintException sce) {
            sce.printStackTrace();
        }
    }
}
