package com.hector.granjasandroid.contract.Empleado;

import com.hector.granjasandroid.domain.Animal;
import com.hector.granjasandroid.domain.Empleado;

import java.util.List;

public interface EmpleadoListContract {
    interface Model {
        interface OnLoadEmpleadoListener {
            void onLoadEmpleadosSuccess(List<Empleado> empleados);

            void onLoadEmpleadosError(String message);
        }

        void loadAllEmpleados(OnLoadEmpleadoListener listener);
    }

    interface View {
        void showEmpleados(List<Empleado> empleados);

        void showMessage(String message);
    }

    interface Presenter {
        void loadAllEmpleados();
    }
}
