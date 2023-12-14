package com.hector.granjasandroid.model.Granja;

import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.hector.granjasandroid.api.CampoAPI;
import com.hector.granjasandroid.api.CampoApiInterface;
import com.hector.granjasandroid.contract.Granja.ModifyGranjaContract;
import com.hector.granjasandroid.domain.Granja;
import com.hector.granjasandroid.presenter.Granja.ModifyGranjaPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModifyGranjaModel implements ModifyGranjaContract.Model {
    private ModifyGranjaPresenter presenter;

    @Override
    public void modifyGranja(long id, Granja granja, OnModifyGranjaListener listener) {

        try {
            CampoApiInterface campoApi = CampoAPI.buildInstance();
            Call<Granja> callGranjas = campoApi.modifyGranja(id, granja);
            Log.d("Granja", "llamada desde el Modify Granja Model");
            callGranjas.enqueue(new Callback<Granja>() {
                @Override
                public void onResponse(Call<Granja> call, Response<Granja> response) {
                    Log.d("Granja", "llamada desde el Modify Granja Model OK");
                    listener.onModifySuccess(granja);
                }

                @Override
                public void onFailure(Call<Granja> call, Throwable t) {
                    Log.d("Granja", "llamada desde el Delete Granja Model KO");
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