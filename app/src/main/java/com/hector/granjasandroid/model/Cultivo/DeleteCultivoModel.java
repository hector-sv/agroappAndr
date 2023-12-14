package com.hector.granjasandroid.model.Cultivo;

import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.hector.granjasandroid.api.CampoAPI;
import com.hector.granjasandroid.api.CampoApiInterface;
import com.hector.granjasandroid.contract.Cultivo.DeleteCultivoContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteCultivoModel implements DeleteCultivoContract.Model {

    @Override
    public void deleteCultivo(long cultivoId, OnDeleteCultivoListener listener) {
        try {
            CampoApiInterface campoApi = CampoAPI.buildInstance();
            Call<Void> callCultivos = campoApi.deleteCultivos(cultivoId);
            Log.d("Cultivos", "llamada desde el DeleCultivoModel");
            callCultivos.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Log.d("Cultivo", "llamada desde el DeleteCultivoModel OK");
                    listener.onDeleteSuccess();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.d("Cultivo", "llamada desde el DeleteCultivoModel KO");
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
