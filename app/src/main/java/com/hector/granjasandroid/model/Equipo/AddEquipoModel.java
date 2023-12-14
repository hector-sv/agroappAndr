package com.hector.granjasandroid.model.Equipo;

import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.hector.granjasandroid.api.CampoAPI;
import com.hector.granjasandroid.api.CampoApiInterface;
import com.hector.granjasandroid.contract.Equipo.AddEquipoContract;
import com.hector.granjasandroid.domain.Equipo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddEquipoModel implements AddEquipoContract.Model {

    @Override
    public void addEquipo(Equipo equipo, OnRegisterEquipoListener listener) {
        try {
            CampoApiInterface campoApi = CampoAPI.buildInstance();
            Call<Equipo> callEquipos = campoApi.addEquipo(equipo);
            Log.d("Equipo", "llamada desde el add Equipo Model");
            callEquipos.enqueue(new Callback<Equipo>() {
                @Override
                public void onResponse(Call<Equipo> call, Response<Equipo> response) {
                    Equipo equipo = response.body();
                    listener.onRegisterSuccess(equipo);
                }

                @Override
                public void onFailure(Call<Equipo> call, Throwable t) {
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

