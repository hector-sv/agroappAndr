package com.hector.granjasandroid.model.Empleado;

import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.hector.granjasandroid.api.CampoAPI;
import com.hector.granjasandroid.api.CampoApiInterface;
import com.hector.granjasandroid.contract.Empleado.AddEmpleadoContract;
import com.hector.granjasandroid.domain.Empleado;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddEmpleadoModel implements AddEmpleadoContract.Model {

    @Override
    public void addEmpleado(Empleado empleado, OnRegisterEmpleadoListener listener) {
        try {

            CampoApiInterface campoApi = CampoAPI.buildInstance();
            Call<Empleado> callEmpleados = campoApi.addEmpleado(empleado);
            Log.d("empleados", "llamada desde el addEmpleadoModel");
            callEmpleados.enqueue(new Callback<Empleado>() {
                @Override
                public void onResponse(Call<Empleado> call, Response<Empleado> response) {
                    Empleado empleado = response.body();
                    listener.onRegisterSuccess(empleado);
                }

                @Override
                public void onFailure(Call<Empleado> call, Throwable t) {
                    t.printStackTrace();
                    String message = "Error al invocar la operación Empleado";
                    listener.onRegisterError(message);
                }
            });
        } catch (SQLiteConstraintException sce) {
            sce.printStackTrace();
        }
    }
}

