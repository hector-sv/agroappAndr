package com.hector.granjasandroid.model.Granja;

import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.hector.granjasandroid.api.CampoAPI;
import com.hector.granjasandroid.api.CampoApiInterface;
import com.hector.granjasandroid.contract.Granja.AddGranjaContract;
import com.hector.granjasandroid.domain.Granja;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddGranjaModel implements AddGranjaContract.Model {

    @Override
    public void addGranja(Granja granja, OnRegisterGranjaListener listener) {
        try {
            CampoApiInterface campoApi = CampoAPI.buildInstance();
            Call<Granja> callGranjas = campoApi.addGranja(granja);
            Log.d("Granjas", "llamada desde el add Granja Model");
            callGranjas.enqueue(new Callback<Granja>() {
                @Override
                public void onResponse(Call<Granja> call, Response<Granja> response) {
                    Granja granja = response.body();
                    listener.onRegisterSuccess(granja);
                }

                @Override
                public void onFailure(Call<Granja> call, Throwable t) {
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

