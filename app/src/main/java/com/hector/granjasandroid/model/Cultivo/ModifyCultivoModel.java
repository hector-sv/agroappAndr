package com.hector.granjasandroid.model.Cultivo;

import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.hector.granjasandroid.api.CampoAPI;
import com.hector.granjasandroid.api.CampoApiInterface;
import com.hector.granjasandroid.contract.Animal.ModifyAnimalContract;
import com.hector.granjasandroid.contract.Cultivo.ModifyCultivoContract;
import com.hector.granjasandroid.domain.Animal;
import com.hector.granjasandroid.domain.Cultivo;
import com.hector.granjasandroid.presenter.Animal.ModifyAnimalPresenter;
import com.hector.granjasandroid.presenter.Cultivo.ModifyCultivoPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModifyCultivoModel implements ModifyCultivoContract.Model {
    private ModifyCultivoPresenter presenter;

    @Override
    public void modifyCultivo(long id, Cultivo cultivo, OnModifyCultivoListener listener) {

        try {
            CampoApiInterface campoApi = CampoAPI.buildInstance();
            Call<Cultivo> callCultivos = campoApi.modifyCultivo(id, cultivo);
            Log.d("Cultivo", "llamada desde el ModifyCultivoModel");
            callCultivos.enqueue(new Callback<Cultivo>() {
                @Override
                public void onResponse(Call<Cultivo> call, Response<Cultivo> response) {
                    Log.d("Cultivo", "llamada desde el ModifyCultivoModel OK");
                    listener.onModifySuccess(cultivo);
                }

                @Override
                public void onFailure(Call<Cultivo> call, Throwable t) {
                    Log.d("Cultivo", "llamada desde el DeleteCultivoModel KO");
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