package com.hector.granjasandroid.model.Empleado;

import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.hector.granjasandroid.api.CampoAPI;
import com.hector.granjasandroid.api.CampoApiInterface;
import com.hector.granjasandroid.contract.Animal.ModifyAnimalContract;
import com.hector.granjasandroid.contract.Empleado.ModifyEmpleadoContract;
import com.hector.granjasandroid.domain.Animal;
import com.hector.granjasandroid.domain.Empleado;
import com.hector.granjasandroid.presenter.Animal.ModifyAnimalPresenter;
import com.hector.granjasandroid.presenter.Empleado.ModifyEmpleadoPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModifyEmpleadoModel implements ModifyEmpleadoContract.Model {
    private ModifyEmpleadoPresenter presenter;

    @Override
    public void modifyEmpleado(long id, Empleado empleado, OnModifyEmpleadoListener listener) {

        try {
            CampoApiInterface campoApi = CampoAPI.buildInstance();
            Call<Empleado> callEmpleados = campoApi.modifyEmpleado(id, empleado);
            Log.d("Empleado", "llamada desde el Modify Empleado Model");
            callEmpleados.enqueue(new Callback<Empleado>() {
                @Override
                public void onResponse(Call<Empleado> call, Response<Empleado> response) {
                    Log.d("Empleado", "llamada desde el ModifyAnimalModel OK");
                    listener.onModifySuccess(empleado);
                }

                @Override
                public void onFailure(Call<Empleado> call, Throwable t) {
                    Log.d("Empleado", "llamada desde el DeleteAnimalModel KO");
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