package com.hector.granjasandroid.model.Empleado;

import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.hector.granjasandroid.api.CampoAPI;
import com.hector.granjasandroid.api.CampoApiInterface;
import com.hector.granjasandroid.contract.Empleado.DeleteEmpleadoContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteEmpleadoModel implements DeleteEmpleadoContract.Model {

    @Override
    public void deleteEmpleado(long empleadoId, OnDeleteEmpleadoListener listener) {
        try {
            CampoApiInterface campoApi = CampoAPI.buildInstance();
            Call<Void> callEmpleados = campoApi.deleteEmpleados(empleadoId);
            Log.d("Empleado", "llamada desde el Dele Empleado Model");
            callEmpleados.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Log.d("Empleados", "llamada desde el Delete Empleado Model OK");
                    listener.onDeleteSuccess();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.d("Empleado", "llamada desde el Delete Empleado Model KO");
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
