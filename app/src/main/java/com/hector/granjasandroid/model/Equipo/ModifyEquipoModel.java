package com.hector.granjasandroid.model.Equipo;

import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.hector.granjasandroid.api.CampoAPI;
import com.hector.granjasandroid.api.CampoApiInterface;
import com.hector.granjasandroid.contract.Equipo.ModifyEquipoContract;
import com.hector.granjasandroid.domain.Equipo;
import com.hector.granjasandroid.presenter.Equipo.ModifyEquipoPresenter;
;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModifyEquipoModel implements ModifyEquipoContract.Model {
    private ModifyEquipoPresenter presenter;

    @Override
    public void modifyEquipo(long id, Equipo equipo, OnModifyEquipoListener listener) {

        try {
            CampoApiInterface campoApi = CampoAPI.buildInstance();
            Call<Equipo> callEquipos = campoApi.modifyEquipo(id, equipo);
            Log.d("Equipo", "llamada desde el Modify Equipo Model");
            callEquipos.enqueue(new Callback<Equipo>() {
                @Override
                public void onResponse(Call<Equipo> call, Response<Equipo> response) {
                    Log.d("Equipo", "llamada desde el Modify Equipo Model OK");
                    listener.onModifySuccess(equipo);
                }

                @Override
                public void onFailure(Call<Equipo> call, Throwable t) {
                    Log.d("Equipo", "llamada desde el Delete Equipo Model KO");
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