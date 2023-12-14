package com.hector.granjasandroid.model.Animal;

import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.hector.granjasandroid.api.CampoAPI;
import com.hector.granjasandroid.api.CampoApiInterface;
import com.hector.granjasandroid.contract.Animal.ModifyAnimalContract;
import com.hector.granjasandroid.domain.Animal;
import com.hector.granjasandroid.presenter.Animal.ModifyAnimalPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModifyAnimalModel implements ModifyAnimalContract.Model {
    private ModifyAnimalPresenter presenter;

    @Override
    public void modifyAnimal(long id, Animal animal, OnModifyAnimalListener listener) {

        try {
            CampoApiInterface campoApi = CampoAPI.buildInstance();
            Call<Animal> callAnimales = campoApi.modifyAnimal(id, animal);
            Log.d("Animal", "llamada desde el ModifyAnimalModel");
            callAnimales.enqueue(new Callback<Animal>() {
                @Override
                public void onResponse(Call<Animal> call, Response<Animal> response) {
                    Log.d("Animal", "llamada desde el ModifyAnimalModel OK");
                    listener.onModifySuccess(animal);
                }

                @Override
                public void onFailure(Call<Animal> call, Throwable t) {
                    Log.d("Animal", "llamada desde el DeleteAnimalModel KO");
                    t.printStackTrace();
                    String message = "Error al invocar la operación";
                    listener.onModifyError(message);
                }
            });
        } catch (SQLiteConstraintException sce) {
            sce.printStackTrace();
        }
    }
}