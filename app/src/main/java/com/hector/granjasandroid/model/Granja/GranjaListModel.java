package com.hector.granjasandroid.model.Granja;

import android.content.Context;
import android.util.Log;

import com.hector.granjasandroid.api.CampoAPI;
import com.hector.granjasandroid.api.CampoApiInterface;
import com.hector.granjasandroid.contract.Granja.GranjaListContract;
import com.hector.granjasandroid.domain.Granja;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GranjaListModel implements GranjaListContract.Model {

    private Context context;


    @Override
    public void loadAllGranjas(OnLoadGranjaListener listener) {

        CampoApiInterface campoApi = CampoAPI.buildInstance();
        Call<List<Granja>> callGranjas = campoApi.getGranjas();

        Log.d("Granja", "llamada desde el Granja model");
        callGranjas.enqueue(new Callback<List<Granja>>() {
            @Override
            public void onResponse(Call<List<Granja>> call, Response<List<Granja>> response) {
                Log.d("Granjas", "llamada desde el Granjas model OK");
                List<Granja>granjas = response.body();
                listener.onLoadGranjasSuccess(granjas);
            }

            @Override
            public void onFailure(Call<List<Granja>> call, Throwable t) {
                Log.d("Granja", "llamada desde el Granja model KO");
                t.printStackTrace();
                String message = "Error al invocar la operación";
                listener.onLoadGranjasError(message);

            }
        });
    }
}

