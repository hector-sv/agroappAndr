package com.hector.granjasandroid.model.Granja;

import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.hector.granjasandroid.api.CampoAPI;
import com.hector.granjasandroid.api.CampoApiInterface;
import com.hector.granjasandroid.contract.Granja.DeleteGranjaContract;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteGranjaModel implements DeleteGranjaContract.Model {

    @Override
    public void deleteGranja(long granjaId, OnDeleteGranjaListener listener) {
        try {
            CampoApiInterface campoApi = CampoAPI.buildInstance();
            Call<Void> callGranjas = campoApi.deleteGranjas(granjaId);
            Log.d("Granja", "llamada desde el DeleGranjaModel");
            callGranjas.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Log.d("Granja", "llamada desde el Delete Granja Model OK");
                    listener.onDeleteSuccess();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.d("Granja", "llamada desde el Delete Granja Model KO");
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
