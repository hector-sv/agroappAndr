package com.hector.granjasandroid.model.Equipo;

import android.content.Context;
import android.util.Log;

import com.hector.granjasandroid.api.CampoAPI;
import com.hector.granjasandroid.api.CampoApiInterface;
import com.hector.granjasandroid.contract.Equipo.EquipoListContract;
import com.hector.granjasandroid.domain.Equipo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EquipoListModel implements EquipoListContract.Model {

    private Context context;

    public EquipoListModel(Context context) {
        this.context = context;
    }

    @Override
    public void loadAllEquipos(OnLoadEquipoListener listener) {

        CampoApiInterface campoApi = CampoAPI.buildInstance();
        Call<List<Equipo>> callEquipos = campoApi.getEquipos();
        Log.d("Equipo", "llamada desde el Equipo model");
        callEquipos.enqueue(new Callback<List<Equipo>>() {
            @Override
            public void onResponse(Call<List<Equipo>> call, Response<List<Equipo>> response) {
                Log.d("Equipo", "llamada desde el Equipo model OK");
                List<Equipo>equipos = response.body();
                listener.onLoadEquiposSuccess(equipos);
            }

            @Override
            public void onFailure(Call<List<Equipo>> call, Throwable t) {
                Log.d("Equipo", "llamada desde el Equipo model KO");
                t.printStackTrace();
                String message = "Error al invocar la operación";
                listener.onLoadEquiposError(message);

            }
        });
    }
}

