package com.hector.granjasandroid.model.Cultivo;

import android.content.Context;
import android.util.Log;

import com.hector.granjasandroid.api.CampoAPI;
import com.hector.granjasandroid.api.CampoApiInterface;
import com.hector.granjasandroid.contract.Cultivo.CultivoListContract;
import com.hector.granjasandroid.domain.Cultivo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CultivoListModel implements CultivoListContract.Model {

    private Context context;

    public CultivoListModel(Context context) {
        this.context = context;
    }

    @Override
    public void loadAllCultivos(OnLoadCultivoListener listener) {

        CampoApiInterface campoApi = CampoAPI.buildInstance();
        Call<List<Cultivo>> callCultivos = campoApi.getCultivos();
        Log.d("Cultivo", "llamada desde Cultivo model");
        callCultivos.enqueue(new Callback<List<Cultivo>>() {
            @Override
            public void onResponse(Call<List<Cultivo>> call, Response<List<Cultivo>> response) {
                Log.d("Cultivo", "llamada desde el Cultivo model OK");
                List<Cultivo>cultivos = response.body();
                listener.onLoadCultivosSuccess(cultivos);
            }

            @Override
            public void onFailure(Call<List<Cultivo>> call, Throwable t) {
                Log.d("Cultivos", "llamada desde el Cultivos model KO");
                t.printStackTrace();
                String message = "Error al invocar la operación";
                listener.onLoadCultivosError(message);

            }
        });
    }
}

