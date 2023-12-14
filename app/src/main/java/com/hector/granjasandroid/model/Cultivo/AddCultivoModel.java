package com.hector.granjasandroid.model.Cultivo;

import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.hector.granjasandroid.api.CampoAPI;
import com.hector.granjasandroid.api.CampoApiInterface;
import com.hector.granjasandroid.contract.Cultivo.AddCultivoContract;
import com.hector.granjasandroid.domain.Cultivo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddCultivoModel implements AddCultivoContract.Model {

    @Override
    public void addCultivo(Cultivo cultivo, OnRegisterCultivoListener listener) {
        try {
            CampoApiInterface campoApi = CampoAPI.buildInstance();
            Call<Cultivo> callCultivos = campoApi.addCultivo(cultivo);
            Log.d("Cultivo", "llamada desde el addCultivoModel");
            callCultivos.enqueue(new Callback<Cultivo>() {
                @Override
                public void onResponse(Call<Cultivo> call, Response<Cultivo> response) {
                    Cultivo cultivo = response.body();
                    listener.onRegisterSuccess(cultivo);
                }

                @Override
                public void onFailure(Call<Cultivo> call, Throwable t) {
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

