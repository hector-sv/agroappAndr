package com.hector.granjasandroid.model.Equipo;

import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.hector.granjasandroid.api.CampoAPI;
import com.hector.granjasandroid.api.CampoApiInterface;
import com.hector.granjasandroid.contract.Equipo.DeleteEquipoContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteEquipoModel implements DeleteEquipoContract.Model {

    @Override
    public void deleteEquipo(long equipoId, OnDeleteEquipoListener listener) {
        try {
            CampoApiInterface campoApi = CampoAPI.buildInstance();
            Call<Void> callEquipos = campoApi.deleteEquipos(equipoId);
            Log.d("Equipo", "llamada desde el Dele Equipo Model");
            callEquipos.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Log.d("Equipo", "llamada desde el DeleteEquipo Model OK");
                    listener.onDeleteSuccess();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.d("Equipo", "llamada desde el DeleteEquipoModel KO");
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
