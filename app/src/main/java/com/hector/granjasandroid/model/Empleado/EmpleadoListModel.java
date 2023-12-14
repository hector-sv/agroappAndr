package com.hector.granjasandroid.model.Empleado;

import android.content.Context;
import android.util.Log;

import com.hector.granjasandroid.api.CampoAPI;
import com.hector.granjasandroid.api.CampoApiInterface;
import com.hector.granjasandroid.contract.Animal.AnimalListContract;
import com.hector.granjasandroid.contract.Empleado.EmpleadoListContract;
import com.hector.granjasandroid.domain.Animal;
import com.hector.granjasandroid.domain.Empleado;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmpleadoListModel implements EmpleadoListContract.Model {

    private Context context;

    public EmpleadoListModel(Context context) {
        this.context = context;
    }

    @Override
    public void loadAllEmpleados(OnLoadEmpleadoListener listener) {

        CampoApiInterface campoApi = CampoAPI.buildInstance();
        Call<List<Empleado>> callEmpleados = campoApi.getEmpleados();
        Log.d("Empleado", "llamada desde el Empleado model");
        callEmpleados.enqueue(new Callback<List<Empleado>>() {
            @Override
            public void onResponse(Call<List<Empleado>> call, Response<List<Empleado>> response) {
                Log.d("Empleados", "llamada desde el Empleado model OK");
                List<Empleado>empleados = response.body();
                listener.onLoadEmpleadosSuccess(empleados);
            }

            @Override
            public void onFailure(Call<List<Empleado>> call, Throwable t) {
                Log.d("Empleados", "llamada desde el Animales model KO");
                t.printStackTrace();
                String message = "Error al invocar la operación";
                listener.onLoadEmpleadosError(message);

            }
        });
    }
}

